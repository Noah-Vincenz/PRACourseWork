/**
 *This class serves the purpose of showing favourite sharks based on the user. 
 * @author Noah Vincenz Noeh
 * @author Jamie Izak Slome
 * @author Rob Hanns
 * @author Mikhail Summer
 */

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.awt.BorderLayout;
import javax.swing.JButton;
import api.jaws.Jaws;
import api.jaws.Location;
import api.jaws.Ping;
import api.jaws.Shark;
import java.awt.Font;

public class FavouritesFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton jbShark;
	private Favourites favouritesClass;
	private JPanel sharksPanel;
	private List<Shark> favSharks;
	private Jaws jaws;
	private Map<Double, String> sharks;
	
	public FavouritesFrame(Favourites favouritesClassGiven) throws IOException {
		//Title of the frame...
		super("Favourites");
		setIconImage(Toolkit.getDefaultToolkit().getImage("sharkFin.png"));
		//Setting the fields...
		favouritesClass = favouritesClassGiven;
		jaws = new Jaws("LUCQaxwU6jtU08hK" , "OdQq9IA2Yj5d2MpH");
		readFile();
		getContentPane().setLayout(new BorderLayout());	
		sharksPanel = new JPanel(new GridLayout(favouritesClass.getFavourites().size(), 1));	
		JLabel lblYourFavouriteSharks = new JLabel("Your favourite sharks are this far away from you right now:");
		lblYourFavouriteSharks.setFont(new Font("Gujarati Sangam MN", Font.BOLD, 13));
		lblYourFavouriteSharks.setForeground(Color.BLUE);
		lblYourFavouriteSharks.setBounds(10, 11, 414, 14);		
		getContentPane().add(lblYourFavouriteSharks, BorderLayout.NORTH);
		getContentPane().add(sharksPanel, BorderLayout.CENTER);
		sharks = new HashMap<Double, String>();
		for(String sharkName : favouritesClass.getFavourites()) {
			sharks.put((double) distFrom(jaws.getLastLocation(sharkName)), sharkName); 
		}
		sortFavourites();

		for(String name : favouritesClass.getFavourites()) {
			setForeground(Color.BLUE);
			setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 14));
			//Need to have distance instead...
			jbShark = new JButton(name +": "+ distFrom(jaws.getLastLocation(name))+" Km.");
			jbShark.setForeground(Color.BLUE);
			jbShark.setFont(new Font("Gulim", Font.PLAIN, 14));
			jbShark.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					SearchFrame sf = null;
					//adjust so that no duplicates
					try {
						sf = new SearchFrame(favouritesClass);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					ArrayList<Ping> p1 = new ArrayList<Ping>(); 
					ArrayList<Ping> p2 = new ArrayList<Ping>(); 
					ArrayList<Ping> p3 = new ArrayList<Ping>();
					p1 = jaws.past24Hours();
					p2 = jaws.pastWeek();
					p3 = jaws.pastMonth();
					for(Ping p : p1) {
						p3.add(p);
					}
					for(Ping p : p2) {
						p3.add(p);
					}
					ArrayList<Ping> uniqueSharkPing = new ArrayList<Ping>();
					for(Ping p : p3) {
						if (p.getName().equals(name)) {
							uniqueSharkPing.add(p);
						}
					}
					Collections.sort(uniqueSharkPing, new Comparator<Ping>() {

						@Override
						public int compare(Ping p1, Ping p2) {
							Date date1 = null;
							try {
								date1 = convertIntoDate(p1);
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
							Date date2 = null;
							try {
								date2 = convertIntoDate(p2);
							} catch (ParseException e) {
								e.printStackTrace();
							}
							return date2.compareTo(date1);
						}
					});
					try {
						sf.buildSharkFound(uniqueSharkPing.get(0));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

			});
			sharksPanel.add(jbShark);
		}
		JButton btnShowFavouritesMap = new JButton("Show Favourites Map");
		btnShowFavouritesMap.setForeground(Color.BLUE);
		btnShowFavouritesMap.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 13));
		getContentPane().add(btnShowFavouritesMap, BorderLayout.SOUTH);
		setVisible(true);
		this.setMinimumSize(new Dimension(450,300));
		btnShowFavouritesMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FavouritesMapFrame favouritesmap = new FavouritesMapFrame();
				favouritesmap.setVisible(true);
			}
		});
	}
	
	private float distFrom(Location lastLocation) {
		
		double lat1 = lastLocation.getLatitude();
		double lng1 = lastLocation.getLongitude();
		float kingsLat = (float) 51.511372;
		float kingsLot = (float) -0.115982;
		double earthRadius = 6371000; //meters
		double dLat = Math.toRadians(lat1-kingsLat);
		double dLng = Math.toRadians(lng1-kingsLot);
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
				Math.cos(Math.toRadians(kingsLat)) * Math.cos(Math.toRadians(lat1)) *
				Math.sin(dLng/2) * Math.sin(dLng/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		float dist = (float) (earthRadius * c);
		float distKm = dist/1000;
		return distKm;
	}

	public Date convertIntoDate(Ping p1) throws ParseException{
		setForeground(Color.BLUE);
		setFont(new Font("Gulim", Font.PLAIN, 14));
		String dateWithoutName = null;
		String ping = p1.toString();
		String[] date = ping.split(" ");
		if(date.length == 3) {
			dateWithoutName = date[1]+" "+date[2];
		}
		else if(date.length == 4) {
			dateWithoutName = date[2]+" "+date[3];
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date finalDate = format.parse(dateWithoutName);
		return finalDate;
	}	
	
	public void sortByDist(List<String> names)
	{
		names = favouritesClass.getFavourites();
		for (int i =0; i < names.size();i++)
		{
			for(int j = 0 ;i < names.size();j++ )
			{
				float dist = distFrom(jaws.getLastLocation(names.get(i)));
				float dist2 = distFrom(jaws.getLastLocation(names.get(j)));
				
			}
		}
	}

	public void addFavShark(Shark s)
	{
		setForeground(Color.BLUE);
		setFont(new Font("Gulim", Font.PLAIN, 14));
		favSharks.add(s);
	}
	
	public void readFile() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("file.txt"));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String everything = sb.toString();
			String[] favouritesArray = everything.split("\\r?\\n");
			favouritesClass.removeAll();
			for(String s1: favouritesArray) {
				favouritesClass.addToFavourites(s1);	
			}

		} finally {
			br.close();
		}

	}
	
	public void sortFavourites() {
		List<Double> Keys = new ArrayList<Double>(sharks.keySet());
		Collections.sort(Keys);
		List<String> favourites = new ArrayList<String>();
		for(int i=0; i<Keys.size(); i++) {
			favourites.add(sharks.get(Keys.get(i)));
		}
		favouritesClass.removeAll();
		for(String sharkName : favourites) {
			favouritesClass.addToFavourites(sharkName);
		}
	}	

	public void setTitle(String a){
		this.setTitle(a);
	}
}
