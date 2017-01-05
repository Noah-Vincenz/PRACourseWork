import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Toolkit;
import api.jaws.Jaws;
import java.awt.Color;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SharkOfTheDayFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private WriteFile data;
	private String sharkOfDay;
	private Random rnd;
	private Date date1;
	private Date date2;
	private JPanel panel;
	private JLabel lblNameOfShark;
	private JLabel name;
	private JLabel lblSharkOfThe;
	private JLabel video; 
	private Jaws jaws;
	
	public SharkOfTheDayFrame() throws IOException {
		
		super("SharkOfTheDay");
		setTitle("Shark Of The Day");
		setResizable(false);
		jaws = new Jaws("LUCQaxwU6jtU08hK" , "OdQq9IA2Yj5d2MpH");
		setIconImage(Toolkit.getDefaultToolkit().getImage("sharkFin.png"));
		panel = new JPanel(new GridLayout(4, 1));		
		lblNameOfShark = new JLabel("Name of Shark:");
		lblSharkOfThe = new JLabel("Shark Of The Day Video:");
		
	    date2 = new Date();
		data = new WriteFile("SharkOfTheDay.txt" , true );
		readFile();
		long duration  = date2.getTime() - date1.getTime();
		long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
		if(diffInHours >= 24) {
			run();
		}
		lblNameOfShark.setForeground(Color.BLUE);
		lblNameOfShark.setFont(new Font("Gujarati Sangam MN", Font.BOLD, 13));
		lblNameOfShark.setBounds(10, 11, 137, 14);
		panel.add(lblNameOfShark);
		name = new JLabel(sharkOfDay);
		name.setBackground(Color.WHITE);
		name.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 14));
		name.setForeground(Color.BLUE);
		panel.add(name);
		
		lblSharkOfThe.setForeground(Color.BLUE);
		lblSharkOfThe.setFont(new Font("Gujarati Sangam MN", Font.BOLD, 13));
		lblSharkOfThe.setBounds(10, 70, 414, 14);
		panel.add(lblSharkOfThe);
		video = new JLabel(jaws.getVideo(sharkOfDay));
		video.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 14));
		video.setForeground(Color.BLUE);
		panel.add(video);
		getContentPane().add(panel);
		pack();
		setSize(264,134);
		setVisible(true);	
	}
	
	public void run() throws IOException {
		data.clear("SharkOfTheDay.txt");
		rnd = new Random();
		int randomIndex = rnd.nextInt(jaws.getSharkNames().size());
		String sharkName = jaws.getSharkNames().get(randomIndex);
		try {
			data.writeToFile(date2.toString());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			data.writeToFile(sharkName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		readFile();	
	}
	
	public void readFile() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("SharkOfTheDay.txt"));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String everything = sb.toString();
		    Object[] everythingArray = everything.split("\\r?\\n");
		    String expectedPattern = "yyyy-MM-dd hh:mm:ss";
		    SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
		    try
		    {
		      date1 = formatter.parse((String) everythingArray[0]);
		    }
		    catch (ParseException e)
		    {
		      e.printStackTrace();
		    }
		    sharkOfDay = (String) everythingArray[1];
		} finally {
		    br.close();
		}
	}
}
