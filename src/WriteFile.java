/**
 * 
 * This class will be used to write data to a file in the memory.
 * @author Noah Vincenz Noeh
 * @author Jamie Izak Slome
 * @author Rob Hanns
 * @author Mikhail Summer
 * 
 */

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriteFile {
	
	private String path;
	private boolean append_to_file = false;
	private FileWriter write;
	private PrintWriter print_line; 
	
	/**
	 * 
	 * The WriteFile constructor will accept a file path as a
	 * String and a boolean value as whether to append or not.
	 * 
	 * @param file_path
	 * @param append_value
	 * @throws FileNotFoundException
	 */
	public WriteFile(String file_path , boolean append_value) throws FileNotFoundException {

		//Setting the fields...
		path = file_path;
		append_to_file = append_value;

	}
	
	/**
	 * 
	 * This method accepts a String parameter which is
	 * then written to the file.
	 * 
	 * @param textLine
	 * @throws IOException
	 */
	public void writeToFile( String textLine ) throws IOException {
		
		//Sets the field...
		write = new FileWriter( path , append_to_file);
		print_line = new PrintWriter( write );
		//Printing on new lines...
		print_line.printf( "%s" + "%n" , textLine);
		print_line.close();
	}
	
	/**
	 * 
	 * This method accepts a String parameter which will be
	 * a file path that is to be set to clear...
	 * 
	 * @param filepath
	 */
	public void clear(String filepath) {
		PrintWriter writer = null;
		try {
			
			//Setting the field...
			writer = new PrintWriter(filepath);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		writer.print("");
		writer.close();
	}


}
