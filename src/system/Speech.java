package system;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Speech {

	private String text;
	private String name; 
	
	public Speech(String text, String name) {
		this.text = text; 
		this.name = name;
	}
	
	public Speech(String path) throws FileNotFoundException {
		Scanner stream = new Scanner(new File(path));

		name = path; 
		
		while (stream.hasNextLine()) {
			String line = stream.nextLine(); 
			line.replaceAll("[\\t\\n]", "");
			text += line; 
		}
		
		stream.close();
	}
	
	public String getText() {
		return text; 
	}
	
	public String getName() {
		return name; 
	}
	
}
