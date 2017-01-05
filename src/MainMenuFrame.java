/**
 * This class represents the main menu which the user will first interact with.
 * Clicking one of the buttons will direct you to a new window, 
 * each window has a different function.
 * 
 * @author Noah Vincenz Noeh
 * @author Jamie Izak Slome
 * @author Rob Hanns
 * @author Mikhail Summer
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;

public class MainMenuFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private StatisticsFrame statisticsFrame;
	private SearchFrame searchFrame;
	private Favourites favouritesClass;
	
	private static Map<String,UserProfile> profiles;
	
	public MainMenuFrame() {
		
		super("Amnity Police");
		setBackground(Color.WHITE);
		setFont(new Font("Gulim", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("sharkFin.png"));
		
		
		favouritesClass = new Favourites();
		profiles = new HashMap<String,UserProfile>();
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLUE);
		menuBar.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 14));
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);
		
		JMenu userMenu = new JMenu("Users");
		userMenu.setHorizontalAlignment(SwingConstants.LEFT);
		userMenu.setForeground(Color.BLUE);
		userMenu.setFont(new Font("Gujarati Sangam MN", Font.BOLD, 14));
		menuBar.add(userMenu);
		
		JMenuItem userMenuItem0 = new JMenuItem("New User");
		userMenuItem0.setForeground(Color.BLUE);
		userMenuItem0.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 14));
		userMenu.add(userMenuItem0);
		
		JPanel mainMenuButtons = new JPanel(new GridLayout(3,0));

		userMenuItem0.addActionListener(new ActionListener () {
		public void actionPerformed(ActionEvent e) {
				
		NewUserFrame newuser = new NewUserFrame();
		newuser.setVisible(true);	
			}
		});
		
		
		JMenuItem mntmUserLogin = new JMenuItem("User Login");
		mntmUserLogin.setHorizontalAlignment(SwingConstants.CENTER);
		mntmUserLogin.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 14));
		mntmUserLogin.setForeground(Color.BLUE);
		userMenu.add(mntmUserLogin);
		
		mntmUserLogin.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
				
		UserLoginFrame login = new UserLoginFrame();
		login.setVisible(true);
				}
		});
		
		
		JButton mainMenuSearch = new JButton("Search");
		mainMenuSearch.setToolTipText("Search Sharks...");
		mainMenuSearch.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 13));
		mainMenuSearch.setForeground(Color.BLUE);
		mainMenuSearch.setBackground(Color.WHITE);
		mainMenuButtons.add(mainMenuSearch);
		
		mainMenuSearch.addActionListener(new ActionListener () {
		
		public void actionPerformed(ActionEvent e) {
				
					try {
						searchFrame = new SearchFrame(favouritesClass);
				} 		catch (IOException e1) {
						e1.printStackTrace();
				}				
			}	
		});
		
		
		JButton mainMenuFavourites = new JButton("Favourites");
		mainMenuFavourites.setToolTipText("View Favourite Sharks...");
		mainMenuFavourites.setBackground(Color.WHITE);
		mainMenuFavourites.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 13));
		mainMenuFavourites.setForeground(Color.BLUE);
		mainMenuButtons.add(mainMenuFavourites);
		
		mainMenuFavourites.addActionListener(new ActionListener() {
			
		public void actionPerformed(ActionEvent e) {
				
					try {
						new FavouritesFrame(favouritesClass);
					} 	catch (IOException e1) {
						e1.printStackTrace();
					}					
			}
		});
		

		
		JButton jbStatistics = new JButton("Statistics");
		jbStatistics.setToolTipText("View Shark Statistics...");
		jbStatistics.setBackground(Color.WHITE);
		jbStatistics.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 13));
		jbStatistics.setForeground(Color.BLUE);
		mainMenuButtons.add(jbStatistics);
		
		jbStatistics.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
					statisticsFrame = new StatisticsFrame();
					
					statisticsFrame.setVisible(true);
				
			}
		});
		
		setVisible(true);
	
		JPanel sharkLogoPanel = new JPanel(new BorderLayout());
		sharkLogoPanel.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(sharkLogoPanel);
		sharkLogoPanel.add(mainMenuButtons, BorderLayout.SOUTH);
		
		String s = "sharkLogo.png";
		ImageIcon image = new ImageIcon(s);
		JLabel imgLabel = new JLabel("", image ,JLabel.CENTER);
		sharkLogoPanel.add(imgLabel,BorderLayout.NORTH);
	}
	
	public SearchFrame getSearchFrame() {
		return searchFrame;
	}
	
	public static void addUser(String name, UserProfile a )
	{
		profiles.put(name,a);
	}
	
	public Map<String,UserProfile> getProfiles()
	{
		return profiles;
	}

}
