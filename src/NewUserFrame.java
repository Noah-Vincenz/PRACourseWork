/**
 * This class creates a new window to create a new user ID and password.
 * @author Noah Vincenz Noeh
 * @author Jamie Izak Slome
 * @author Rob Hanns
 * @author Mikhail Summer
 */

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class NewUserFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField txtEnterNameHere;
	private JPasswordField passwordField;
	private WriteFile profileData;
	private boolean validName = true;
	
	public NewUserFrame() {
		
		super("New User Sign-Up");
		setResizable(false);
		setTitle("New User");
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("sharkFin.png"));
		this.setSize(new Dimension(480, 221));
		this.setPreferredSize(new Dimension(480,250));
		

		try {
			profileData = new WriteFile("ProfileData.txt",true);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		getContentPane().setLayout(null);
		
		JLabel lblPleaseEnterYour = new JLabel("Please enter your name:");
		lblPleaseEnterYour.setBounds(10, 21, 325, 14);
		lblPleaseEnterYour.setForeground(Color.BLUE);
		lblPleaseEnterYour.setFont(new Font("Gujarati Sangam MN", Font.BOLD, 13));
		getContentPane().add(lblPleaseEnterYour);
		
		txtEnterNameHere = new JTextField();
		txtEnterNameHere.setBounds(10, 46, 444, 20);
		txtEnterNameHere.setToolTipText("New Username...");
		getContentPane().add(txtEnterNameHere);
		txtEnterNameHere.setColumns(10);
		
		JLabel lblPleaseCreateA = new JLabel("Please enter your new password:");
		lblPleaseCreateA.setBounds(10, 77, 414, 14);
		lblPleaseCreateA.setForeground(Color.BLUE);
		lblPleaseCreateA.setFont(new Font("Gujarati Sangam MN", Font.BOLD, 13));
		getContentPane().add(lblPleaseCreateA);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 102, 444, 20);
		passwordField.setToolTipText("New Password...");
		getContentPane().add(passwordField);
		
		JButton btnOk = new JButton("Sign Up");
		btnOk.setBounds(10, 147, 190, 23);
		btnOk.setForeground(Color.BLUE);
		btnOk.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 13));
		btnOk.setBackground(Color.WHITE);
		getContentPane().add(btnOk);
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(264, 147, 190, 23);
		btnCancel.setForeground(Color.BLUE);
		btnCancel.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 13));
		btnCancel.setBackground(Color.WHITE);
		getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
					}
				});
		
		
		btnOk.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
			
					try {
						checkName();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	
				if(validName == true){
					Favourites userFav = new Favourites();
					try {
						UserProfile user = new UserProfile(txtEnterNameHere.getText(),passwordField.getPassword(),userFav);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				
						String usrName = txtEnterNameHere.getText();
						String password = new String(passwordField.getPassword());
						try {
							profileData.writeToFile(usrName+":"+password);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					
				}
			
		}
	});
		
	

}
	/**
	 * method to check the desired username isn't in use 
	 * @return boolean
	 * @throws IOException
	 */
	public boolean checkName() throws IOException  // checks if given user name is in txt file 
	{

	    FileReader in = new FileReader("ProfileData.txt");
	    BufferedReader br = new BufferedReader(in);

	    String line;
	    while ((line = br.readLine()) != null) {

	    	if (line.equals(txtEnterNameHere.getText()))
	    	{
	    		validName = false;
	    	}
	    	else
	    		validName = true;
	    }
	    in.close();
	    return validName;
	    
	}

}
