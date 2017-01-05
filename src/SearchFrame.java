/**
 * This class represents the frame that can be used to search for existing sharks. 
 * It also serves the purpose of displaying results for a favourite shark or searched shark.
 * 
 * @author Noah Vincenz Noeh
 * @author Jamie Izak Slome
 * @author Rob Hanns
 * @author Mikhail Summer
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import api.jaws.Jaws;
import api.jaws.Ping;
import api.jaws.Shark;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.border.MatteBorder;

public class SearchFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> tr;
	private JComboBox<String> gender;
	private JComboBox<String> sol;
	private JComboBox<String> tl;
	private JLabel jlSharkTracker;
	private JLabel jlTrackingRange;
	private JLabel jlGender;
	private JLabel jlStageOfLife;
	private JLabel jlTagLocation;
	private JButton search;
	private JPanel optionPane;	
	private JPanel searchResultsPane;
	private JLabel jlName;
	private JLabel jlGender2;
	private JLabel jlStageOfLife2;
	private JTextArea jtaDescription;
	private JLabel jlSpecies;
	private JLabel jlLength;
	private JLabel jlWeight;
	private JLabel jlNameIs;
	private JLabel jlGender2Is;
	private JLabel jlStageOfLife2Is;
	private JLabel jlSpeciesIs;
	private JLabel jlLengthIs;
	private JLabel jlWeightIs;
	private JLabel jlDescription;
	private JButton jbFollow;
	private JPanel sharkFound;
	private JPanel sharkDetails;
	private JPanel jpDescription;
	private JPanel sharkDetailsBottom;
	private JLabel lastPing;
	private JLabel jlAcknowledgement;
	private Jaws jaws;
	private JScrollPane scrollPane;
	private ArrayList<Shark> sharksFound; 
	private WriteFile data;
	private JMenuBar menuBar;
	private JMenu mnExtras;
	private JMenuItem mntmSharkOfThe;
	private Favourites favouritesClass;

	public SearchFrame(Favourites favouritesClassGiven) throws IOException {
		super("Search");
		setIconImage(Toolkit.getDefaultToolkit().getImage("sharkFin.png"));
		favouritesClass = favouritesClassGiven;
		jaws = new Jaws("LUCQaxwU6jtU08hK" , "OdQq9IA2Yj5d2MpH");
		sharksFound = new ArrayList<Shark>();
		data = new WriteFile("file.txt" , true );
		readFile();
		
		
		getContentPane().setLayout(new BorderLayout());		
		menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLUE);
		menuBar.setFont(new Font("Gujarati Sangam MN", Font.BOLD, 14));
		setJMenuBar(menuBar);		
		mnExtras = new JMenu("Extras");
		menuBar.add(mnExtras);		 
		mntmSharkOfThe = new JMenuItem("Shark Of The Day");
		mntmSharkOfThe.setForeground(Color.BLUE);
		mntmSharkOfThe.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 14));
		mnExtras.add(mntmSharkOfThe);
		mntmSharkOfThe.addActionListener(new ActionListener() {	
			
			@Override
			public void actionPerformed(ActionEvent e) {			
					try {
						SharkOfTheDayFrame sotd = new SharkOfTheDayFrame();
						sotd.setVisible(true);
					} catch (IOException e1) {
						e1.printStackTrace();
					}								
			}
		});	
		buildOptionPane();	
	}
	/**
	 * method to build the search area on the left side of the frame
	 */
	public void buildOptionPane() {
		jlSharkTracker = new JLabel("Shark Tracker");
		jlSharkTracker.setAlignmentX(Component.CENTER_ALIGNMENT);
		jlSharkTracker.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 18));
		jlSharkTracker.setForeground(Color.BLUE);
		jlSharkTracker.setHorizontalAlignment(SwingConstants.CENTER);
		jlTrackingRange = new JLabel("Tracking Range");
		jlTrackingRange.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		jlTrackingRange.setAlignmentX(Component.CENTER_ALIGNMENT);
		jlTrackingRange.setForeground(Color.BLUE);
		jlTrackingRange.setFont(new Font("Gujarati Sangam MN", Font.BOLD, 13));
		jlTrackingRange.setHorizontalAlignment(SwingConstants.CENTER);
		jlGender = new JLabel("Gender");
		jlGender.setHorizontalAlignment(SwingConstants.CENTER);
		jlGender.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		jlGender.setAlignmentX(Component.CENTER_ALIGNMENT);
		jlGender.setForeground(Color.BLUE);
		jlGender.setFont(new Font("Gujarati Sangam MN", Font.BOLD, 13));
		jlStageOfLife = new JLabel("Stage of Life");
		jlStageOfLife.setHorizontalAlignment(SwingConstants.CENTER);
		jlStageOfLife.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		jlStageOfLife.setAlignmentX(Component.CENTER_ALIGNMENT);
		jlStageOfLife.setFont(new Font("Gujarati Sangam MN", Font.BOLD, 13));
		jlStageOfLife.setForeground(Color.BLUE);
		jlTagLocation = new JLabel("Tag Location");
		jlTagLocation.setHorizontalAlignment(SwingConstants.CENTER);
		jlTagLocation.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		jlTagLocation.setAlignmentX(Component.CENTER_ALIGNMENT);
		jlTagLocation.setFont(new Font("Gujarati Sangam MN", Font.BOLD, 13));
		jlTagLocation.setForeground(Color.BLUE);
		search = new JButton("Search");
		search.setAlignmentX(Component.CENTER_ALIGNMENT);
		search.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 13));
		search.setForeground(Color.BLUE);		
		tr = new JComboBox<String>();
		tr.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 13));
		tr.setForeground(Color.BLUE);
		String [] trArray = {"Last 24 hours", "Last week", "Last month"};
		for(String s : trArray) {
			tr.addItem(s);;
		}
		gender = new JComboBox<String>();
		gender.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 13));
		gender.setForeground(Color.BLUE);
		String [] genderArray = {"All","Female", "Male"};
		for(String s : genderArray) {
			gender.addItem(s);;
		}
		sol = new JComboBox<String>();
		sol.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 13));
		sol.setForeground(Color.BLUE);
		String[] solArray = {"All","Mature", "Immature", "Undetermined"};
		for(String s : solArray) {
			sol.addItem(s);
		}
		tl = new JComboBox<String>();
		tl.setForeground(Color.BLUE);
		tl.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 13));
		ArrayList<String> tlArray = jaws.getTagLocations();
		tl.addItem("All");
		for(String s : tlArray) {
			tl.addItem(s);
		}		
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					sharkBuilder();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		    }
		});			
		optionPane = new JPanel();
		optionPane.setForeground(Color.BLUE);
		optionPane.setFont(new Font("Gulim", Font.PLAIN, 14));
		optionPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) null));
		optionPane.setBackground(Color.WHITE);
		optionPane.setLayout(new BoxLayout(optionPane, BoxLayout.Y_AXIS));
		optionPane.add(jlSharkTracker);
		optionPane.add(jlTrackingRange);
		optionPane.add(tr);
		optionPane.add(jlGender);
		optionPane.add(gender);
		optionPane.add(jlStageOfLife);
		optionPane.add(sol);
		optionPane.add(jlTagLocation);
		optionPane.add(tl);
		optionPane.add(search);	
		String s = "sharkLogo.png";		
		ImageIcon image = new ImageIcon(new ImageIcon(s).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		JLabel imgLabel = new JLabel("", image ,SwingConstants.CENTER);
		imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		optionPane.add(imgLabel);	
		optionPane.setVisible(true);
		searchResultsPane = new JPanel(new GridLayout());
		searchResultsPane.setLayout(new BoxLayout(searchResultsPane,BoxLayout.Y_AXIS));
		getContentPane().add(optionPane, BorderLayout.WEST);
		searchResultsPane.setVisible(true);
		scrollPane = new JScrollPane(searchResultsPane);
		getContentPane().add(scrollPane,BorderLayout.CENTER);	
		jlAcknowledgement = new JLabel(jaws.getAcknowledgement());
		jlAcknowledgement.setForeground(Color.BLUE);
		jlAcknowledgement.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 13));
		getContentPane().add(jlAcknowledgement, BorderLayout.SOUTH);		
		pack();
		setSize(1200,600);
		setVisible(true);
	}
	/**
	 * method to get the list of sharks found from user search, and calls the method 
	 * to create the data for the sharks that are found.
	 * @throws IOException
	 */
	public void sharkBuilder() throws IOException {
		
		searchResultsPane.removeAll();		
		ArrayList<Ping> names = new ArrayList<Ping>(); 
		JLabel noResults = new JLabel("No results");	
		String pingDate = (String) tr.getSelectedItem();
		switch (pingDate) {	
			case "Last 24 hours": names = jaws.past24Hours();
			
			break;
			
			case "Last week" : names = jaws.pastWeek();
	
			break;
			
			case "Last month" : names = jaws.pastMonth();
			
			break;
					
		}	
		Collections.sort(names, new Comparator<Ping>() {		                
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
		//method 1 to get rid of duplicate shark entries - does not work
		ArrayList<Integer> indicesToDelete = new ArrayList<Integer>();
		for(int i=0; i<names.size(); i++) {
			for(int j=1; j<names.size(); j++) {
				if(jaws.getShark(names.get(i).getName()).equals(jaws.getShark(names.get(j).getName()))) {
					indicesToDelete.add(j);
				}
			}
		}
		for(Integer i : indicesToDelete) {
			names.remove(i);
		}

		for(Ping p : names) {
			Shark s = jaws.getShark(p.getName());
			if(	    ( s.getGender().equals(gender.getSelectedItem()) || gender.getSelectedItem().equals("All") ) &&
					( s.getStageOfLife().equals(sol.getSelectedItem()) || sol.getSelectedItem().equals("All") ) &&
					( s.getTagLocation().equals(tl.getSelectedItem()) || tl.getSelectedItem().equals("All") ) &&
					( sharkAlreadyFound(s) == false ) ) /*second method to get rid of duplicates*/  {
				sharksFound.add(s);
				buildSharkFound(p);
			}
		}
		searchResultsPane.revalidate(); 	
	}
	
	//2nd method to get rid of duplicates -- not working
	public boolean sharkAlreadyFound(Shark s) {
		boolean b = false;
		for (Shark s1 : sharksFound) {
			if(s.equals(s1)) {
				b = true;
			}
		}
		return b;
	}
	/**
	 * Method to convert the into the desired date
	 * @param Ping p1 - Shark ping 
	 * @return Date- (yyyy-MM-dd HH:mm:ss)
	 * @throws ParseException
	 */
	public Date convertIntoDate(Ping p1) throws ParseException{
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
	/**
	 * Method to construct and add the found sharks data into the results panel in this window 
	 * @param Ping p - ping from shark
	 * @throws IOException
	 */
	public void buildSharkFound(Ping p) throws IOException {
		Shark s = jaws.getShark(p.getName());
		sharkFound = new JPanel(new BorderLayout());
		sharkDetails = new JPanel(new GridLayout(6, 2));
		sharkFound.add(sharkDetails, BorderLayout.NORTH);	
		jlName = new JLabel("Name:");
		jlName.setForeground(Color.BLUE);
		jlName.setFont(new Font("Gujarati Sangam MN", Font.BOLD, 13));	
		jlGender2 = new JLabel("Gender:");
		jlGender2.setForeground(Color.BLUE);
		jlGender2.setFont(new Font("Gujarati Sangam MN", Font.BOLD, 13));	
		jlStageOfLife2 = new JLabel("Stage of Life:");
		jlStageOfLife2.setForeground(Color.BLUE);
		jlStageOfLife2.setFont(new Font("Gujarati Sangam MN", Font.BOLD, 13));	
		jlSpecies = new JLabel("Species:");
		jlSpecies.setForeground(Color.BLUE);
		jlSpecies.setFont(new Font("Gujarati Sangam MN", Font.BOLD, 13));	
		jlLength = new JLabel("Length:");
		jlLength.setForeground(Color.BLUE);
		jlLength.setFont(new Font("Gujarati Sangam MN", Font.BOLD, 13));	
		jlWeight = new JLabel("Weight:");
		jlWeight.setForeground(Color.BLUE);
		jlWeight.setFont(new Font("Gujarati Sangam MN", Font.BOLD, 13));	
		jlDescription = new JLabel("Description:");
		jlDescription.setForeground(Color.BLUE);
		jlDescription.setFont(new Font("Gujarati Sangam MN", Font.BOLD, 13));	
		jlNameIs = new JLabel(s.getName());
		jlNameIs.setForeground(Color.BLUE);
		jlNameIs.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 13));	
		jlGender2Is = new JLabel(s.getGender());
		jlGender2Is.setForeground(Color.BLUE);
		jlGender2Is.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 13));	
		jlStageOfLife2Is = new JLabel(s.getStageOfLife());
		jlStageOfLife2Is.setForeground(Color.BLUE);
		jlStageOfLife2Is.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 13));	
		jlSpeciesIs = new JLabel(s.getSpecies());
		jlSpeciesIs.setForeground(Color.BLUE);
		jlSpeciesIs.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 13));	
		jlLengthIs = new JLabel(s.getLength());
		jlLengthIs.setForeground(Color.BLUE);
		jlLengthIs.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 13));	
		jlWeightIs = new JLabel(s.getWeight());
		jlWeightIs.setForeground(Color.BLUE);
		jlWeightIs.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 13));	
		jtaDescription = new JTextArea(s.getDescription());
		jtaDescription.setForeground(Color.BLUE);
		jtaDescription.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 13));
		jtaDescription.setLineWrap(true);
	    jtaDescription.setWrapStyleWord(true);
	    jtaDescription.setOpaque(false);
	    jtaDescription.setEditable(false);	
		jpDescription = new JPanel(new BorderLayout());
		jpDescription.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0)); 
		jpDescription.add(jtaDescription, BorderLayout.CENTER);	
		sharkDetailsBottom = new JPanel(new BorderLayout());
		sharkFound.add(sharkDetailsBottom, BorderLayout.SOUTH);
		jbFollow = new JButton("Follow");
		jbFollow.setForeground(Color.BLUE);
		jbFollow.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 14));	
		jbFollow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {			
				JButton pressedButton = (JButton)e.getSource();
				pressedButton.setForeground(Color.BLUE);
				pressedButton.setFont(new Font("Gujarati Sangam MN", Font.BOLD, 13));
				if(pressedButton.getText().equals("Follow")) {
					pressedButton.setText("Following");
					favouritesClass.addToFavourites(s.getName());
					try {
						data.writeToFile(s.getName());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else {
					pressedButton.setText("Follow");
					favouritesClass.removeFromFavourites(s.getName());
					data.clear("file.txt");
					for(String s : favouritesClass.getFavourites()) {
						try {
							data.writeToFile(s);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
		    }
		});	
		//setting the jButtons of followed sharks to following
		for(String sharkName : favouritesClass.getFavourites()) {
			if (s.getName().equals(sharkName)) {
				jbFollow.setText("Following");
			}
		}
		sharkDetailsBottom.add(jbFollow, BorderLayout.EAST);		
		lastPing = new JLabel("Last ping: "+p.getTime());
		lastPing.setForeground(Color.BLUE);
		lastPing.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 13));
		sharkDetailsBottom.add(lastPing, BorderLayout.WEST);		
		searchResultsPane.add(sharkFound);		
		sharkDetails.add(jlName);
		sharkDetails.add(jlNameIs);	
		sharkDetails.add(jlGender2);
		sharkDetails.add(jlGender2Is);	
		sharkDetails.add(jlStageOfLife2);
		sharkDetails.add(jlStageOfLife2Is);	
		sharkDetails.add(jlSpecies);
		sharkDetails.add(jlSpeciesIs);	
		sharkDetails.add(jlLength);
		sharkDetails.add(jlLengthIs);
		sharkDetails.add(jlWeight);
		sharkDetails.add(jlWeightIs);
		sharkFound.add(jpDescription, BorderLayout.CENTER);
		searchResultsPane.revalidate(); 
	}
	/**
	 * method that reads through file.txt to get and add the names of sharks followed by
	 * the user to the favouritesClass.
	 * @throws IOException
	 */
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
}
