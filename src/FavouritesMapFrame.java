/**
 * This class is the frame containing the Static Google Map.
 * @author Noah Vincenz Noeh
 * @author Jamie Izak Slome
 * @author Rob Hanns
 * @author Mikhail Summer
 */
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FavouritesMapFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Favourites favouritesClass;

	/**
	 * constructor that gets map from URL and adds it to frame
	 */
	public FavouritesMapFrame() {

		super("Favourites Map");
		
		String path = "https://maps.googleapis.com/maps/api/staticmap?center=King's+College+London,"
				+ "London,UK&zoom=1&size=800x300&maptype=satellite&markers=color:blue%7Clabel:S%7C40"
				+ ".702147,-74.015794&markers=color:green%7Clabel:G%7C40.711614,-74.012318&markers=c"
				+ "olor:red%7Clabel:C%7C40.718217,-73.998284&key=AIzaSyDecUSfydniKzuH5cHYReE9ZLEO0rbPXpo";
		
        
        URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        BufferedImage image = null;
		try {
			image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
        JLabel label = new JLabel(new ImageIcon(image));
     
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(label);
        this.pack();
        this.setLocation(200, 200);
        this.setResizable(false);
        this.setVisible(true);
		
	}
}
