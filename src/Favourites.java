/**
 * 
 * This class stores all the favourite sharks attributed to a specific user.
 * 
 * @author Noah-Vincenz Nöh
 * @author Jamie Izak Slome
 * @author Rob Hanns
 * @author Mikhail Summer
 * 
 */
import java.util.ArrayList;

public class Favourites {
	
	
	private ArrayList<String> favourites;
	
	public Favourites() {
		
		//Sets the field to be stored inside the constructor.
		favourites = new ArrayList<String>();
	}

	/**
	 * 
	 * This method returns the favourite sharks that are stored.
	 * 
	 * @return favourites
	 */
	public ArrayList<String> getFavourites() {
		return favourites;
	}
	/**
	 * This method accepts a String (s) that will then be added to an ArrayList
	 * of Strings (model for Sharks). 
	 * 
	 * @param s - shark that will be added to favourites.
	 */
	public void addToFavourites(String s) {
		
		//Adding the String to our ArrayList.
		favourites.add(s);
	}
	
	/**
	 * This method accepts a String (s) that will be removed from an ArrayList
	 * of Strings (model for Sharks).
	 * 
	 * @param s - shark that will be removed from favourites.
	 */
	public void removeFromFavourites(String s) {
		//Removing the String from our ArrayList.
		favourites.remove(s);
	}
	/**
	 * 
	 * This method removes all Strings that are stored within our ArrayList.
	 * 
	 */
	public void removeAll() {
		//Empties the ArrayList of Strings.
		favourites.clear();
	}
}
