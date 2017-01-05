/**
 * This method represents the login window the user will use to access their profile.
 * * @author Noah Vincenz Noeh
 * @author Jamie Izak Slome
 * @author Rob Hanns
 * @author Mikhail Summer
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Font;

public class UserLoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField usernameFld;
	private JPasswordField passwordFld;
	
	private boolean userExists;
	private boolean passwordMatch;
	public UserLoginFrame() {
		
		super("Log-in");
		setResizable(false);
		setTitle("Sign In");
		
		
		getContentPane().setLayout(null);
		this.setSize(new Dimension(480, 221));
		this.setPreferredSize(new Dimension(480,250));
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(Color.BLUE);
		lblUsername.setFont(new Font("Gujarati Sangam MN", Font.BOLD, 13));
		lblUsername.setBounds(10, 21, 325, 14);
		getContentPane().add(lblUsername);
		
		usernameFld = new JTextField();
		usernameFld.setBackground(Color.WHITE);
		usernameFld.setToolTipText("Enter username...");
		usernameFld.setBounds(10, 46, 444, 20);
		getContentPane().add(usernameFld);
		usernameFld.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.BLUE);
		lblPassword.setFont(new Font("Gujarati Sangam MN", Font.BOLD, 13));
		lblPassword.setBounds(10, 77, 414, 14);
		getContentPane().add(lblPassword);
		
		passwordFld = new JPasswordField();
		passwordFld.setToolTipText("Enter password...");
		passwordFld.setBounds(10, 102, 444, 20);
		getContentPane().add(passwordFld);
		passwordFld.setColumns(10);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setForeground(Color.BLUE);
		btnLogIn.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 13));
		btnLogIn.setBackground(Color.WHITE);
		btnLogIn.setBounds(10, 147, 190, 23);
		getContentPane().add(btnLogIn);
		
		btnLogIn.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				
					if (authentication() == true)
					{
						
						
						
					}
					}
				});
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.BLUE);
		btnCancel.setFont(new Font("Gujarati Sangam MN", Font.PLAIN, 13));
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBounds(264, 147, 190, 23);
		getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				
				dispose();	
					}
				});
		
	}
	/**
	 * method to test the credentials given for username and password.
	 * uses the testLogin() method to check
	 * @return boolean 
	 */
	public boolean authentication()
	{
		try {
			testLogin();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		if(userExists == true && passwordMatch == true )
		{
			return true;
			
		}
		else
			return false;
	}
	/**
	 * tests both the username and password against txt file 
	 * @throws IOException
	 */
	public void testLogin() throws IOException  // method to test username 
	{
		userExists = false;
		passwordMatch = false;
		
		 FileReader in = new FileReader("ProfileData.txt");
		    BufferedReader br = new BufferedReader(in);

		    String line;
		    while ((line = br.readLine()) != null) {
		    	String[] str_array = line.split(":");
		    	String username = str_array[0]; 
				String password = str_array[1];
		    	if (username.equals(usernameFld.getText()))
		    	{
		    		userExists = true;
		    		
		    		String givenPassword = new String(passwordFld.getPassword());
		    		if(password.equals(givenPassword));
		    		{
		    			passwordMatch = true;
		    		}
		    		
		    }
		   
		    
		    }
		  in.close();
	}
}

	

