/** 
 * 
 * @author Noah Vincenz Noeh
 * @author Jamie Izak Slome
 * @author Rob Hanns
 * @author Mikhail Summer
 */
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StatisticsFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public StatisticsFrame() {

		super("Statistics");
		setResizable(false);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 82);
		getContentPane().add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 104, 414, 82);
		getContentPane().add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 197, 414, 53);
		getContentPane().add(panel_2);

		this.setSize(450,300);

	}

}
