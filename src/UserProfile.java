/**
 * This class represents a users profile, storing the username, password and favourite shark list.
 * * @author Noah Vincenz Noeh
 * @author Jamie Izak Slome
 * @author Rob Hanns
 * @author Mikhail Summer
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import api.jaws.Jaws;

public class UserProfile {
	
	private String userName;
	private char[] passsword;//push
	private Favourites fav;
	private List <String> favSharks;
	
	public UserProfile (String name, char[] password, Favourites givenFav) throws IOException{
		
		this.userName=name;
		this.passsword=password;
		this.fav = givenFav;
		FavouritesFrame userFav = new FavouritesFrame(fav);
		//userFav.setTitle(userName+"'s"+ "Favourite Sharks");
	}


	public char[] getPasssword() {
		return passsword;
	}
	
	/**
	 * gets the users selected favourite sharks from the txt file 
	 * @throws IOException
	 */
	public void getFavSharks() throws IOException
	{
		
		Jaws jaws = new Jaws("LUCQaxwU6jtU08hK" , "OdQq9IA2Yj5d2MpH");
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
			fav.removeAll();
			for(String s1: favouritesArray) {
				fav.addToFavourites(s1);	
			}

		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	}
	
	public List<String> getUserSharks()
	{
		return favSharks;
	}
	
	
	
}
		
	

	
	


