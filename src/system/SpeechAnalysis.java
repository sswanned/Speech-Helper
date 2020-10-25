package system;

import java.util.ArrayList;

public class SpeechAnalysis {
	
	private CrutchList crutchList; 
	private Speech speech; 
	private int [] countList; 

	public SpeechAnalysis(CrutchList crutchList, Speech speech) {
		this.crutchList = crutchList; 
		this.speech = speech;
		countList = new int [crutchList.getSize()];
	}
	
	public String toString() {
		return "Crutch List: " + crutchList.toString() + "\nSpeech: " + speech.getName(); 
	}
	
	public ArrayList<String> results() {
		countInstances(); 
		
		ArrayList<String> str = new ArrayList<String>(); 
		int counter = 0; 
		
		for (String word : crutchList) {
			if (countList[counter] > 0) {
				str.add(word + ": " + countList[counter] + " occurrence(s)");
			}
			counter++; 
		}
		
		return str; 
	}
	
	private void countInstances() {
		// what to do in cases where dictionary usage matters
		int counter = 0; 
		
		for (String word : crutchList) {
			countList[counter] = numOfOccurrences(speech.getText(), word);
			counter++;
		}
	}
	
	private int numOfOccurrences(String text, String word) {
		word = word.toLowerCase(); 
		text = text.toLowerCase(); 
		int index = 0, count = 0, length = 0; 
		
		while (index != -1) {
			index = text.indexOf(word, index + length);
			
			if (index != -1) {
				count++; 
				length = word.length(); 
			}
		}
		
		return count; 
	}
	
}
