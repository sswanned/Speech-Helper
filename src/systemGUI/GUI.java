package systemGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import system.*;

public class GUI implements ActionListener {
	
	private static final int frameSize = 600, sizeX = 400, sizeY = 180, sizeXL = 300, 
			sizeYL = 250, sizeXR = 120, sizeYR = 240; 
	private static JFrame frame; 
	private CrutchList crutchList; 
	private Speech speech; 
	private JPanel pane; 
	private JButton seeListButton, recScreenButton, recordButton, resultsButton, goBackButton; 
	private JTextArea file;
	private Font headingFont = new Font("Sans Serif", Font.BOLD, 18);
	private Font normalFont = new Font("Sans Serif", Font.PLAIN, 14);
	
	{
		 try {
			crutchList = new CrutchList("crutchList.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public JPanel welcome() {
		pane = new JPanel();
	    pane.setAlignmentX(Component.CENTER_ALIGNMENT);
	    pane.setPreferredSize(new Dimension(sizeX, sizeY));
	    pane.setMaximumSize(new Dimension(sizeX, sizeY)); 
	    
	    JLabel welcome = new JLabel("Welcome to Speech Helper!");
		welcome.setFont(headingFont);
	    JLabel heading = new JLabel("Helping you eliminate crutch words from your speech"); 
		heading.setFont(normalFont);
	    seeListButton = new JButton("See Word List");
	    seeListButton.addActionListener(this);
	    recScreenButton = new JButton("Test Your Speech");
	    recScreenButton.addActionListener(this);
		JButton close = new JButton("Close App");
		close.addActionListener(e -> frame.dispose());
		pane.add(welcome);
		pane.add(heading); 
		pane.add(Box.createRigidArea(new Dimension(sizeX, 10)));
		pane.add(seeListButton); 
		pane.add(recScreenButton);
		pane.add(close);
	    
		return pane;
	}
	
	public JPanel list() {
		pane = new JPanel(); 
		pane.setAlignmentX(Component.CENTER_ALIGNMENT);
		pane.setPreferredSize(new Dimension(sizeXL, sizeYL));
		pane.setMaximumSize(new Dimension(sizeXL, sizeYL)); 
		
		JLabel label = new JLabel("List of crutch words: ");
		label.setFont(normalFont);

		String [] words = new String [crutchList.getSize()];
		int counter = 0; 
		for (String word : crutchList) {
			words[counter] = word; 
			counter++;
		}
		
		JList<String> list = new JList<String>(words); 
		JScrollPane listScroll = new JScrollPane(list);
		listScroll.setPreferredSize(new Dimension(sizeXL, sizeYL/2));
		
		/*
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame addWord = new JFrame("Add word");
				addWord.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				addWord.setSize(300, 200);
				addWord.getContentPane().setLayout(new BoxLayout(addWord.getContentPane(), BoxLayout.Y_AXIS));
				
				JPanel panel = new JPanel(); 
				panel.setAlignmentX(Component.CENTER_ALIGNMENT);
				JLabel l1 = new JLabel("Enter word");
				JTextArea word = new JTextArea(1, 20); 
				JLabel l2 = new JLabel("Should the word be deleted?");
				JRadioButton yes = new JRadioButton("Yes");
				JRadioButton no = new JRadioButton("No");
				JLabel l3 = new JLabel("Enter any alternatives");
				JTextArea alts = new JTextArea(2, 20); 
				JButton submit = new JButton("Submit");
				submit.addActionListener(action -> {
					addWord.dispose(); 
					crutchList.addWord(word.getText(), true);
				});
				
				panel.add(l1);
				panel.add(word);
				panel.add(l2);
				panel.add(yes);
				panel.add(no);
				panel.add(l3);
				panel.add(alts);
				panel.add(submit); 
				
				addWord.getContentPane().add(panel);
				
			    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			    int x = (int) ((screen.getWidth() - addWord.getWidth()) / 2);
			    int y = (int) ((screen.getHeight() - addWord.getHeight()) / 2);
			    addWord.setLocation(x, y);
				addWord.setVisible(true);
			}
			
		});
		JButton delButton = new JButton("Remove");
		delButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String word = list.getSelectedValue(); 
				
				JFrame addWord = new JFrame("Delete word");
				addWord.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				addWord.setSize(280, 100);
				addWord.getContentPane().setLayout(new BoxLayout(addWord.getContentPane(), BoxLayout.Y_AXIS));
				
				JPanel panel = new JPanel(); 
				panel.setAlignmentX(Component.CENTER_ALIGNMENT);
				
				JLabel l = new JLabel("Delete word \"" + word + "\" from database? ");
				JButton yes = new JButton("Yes");
				yes.addActionListener(action -> {
					crutchList.deleteWord(word);
					addWord.dispose(); 
					pane.revalidate();
				});
				
				JButton no = new JButton("No");
				no.addActionListener(action -> addWord.dispose());
				
				panel.add(l);
				panel.add(yes);
				panel.add(no);
				
				addWord.getContentPane().add(panel);
				
			    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			    int x = (int) ((screen.getWidth() - addWord.getWidth()) / 2);
			    int y = (int) ((screen.getHeight() - addWord.getHeight()) / 2);
			    addWord.setLocation(x, y);
				addWord.setVisible(true);
			}
			
		});*/
		goBackButton = new JButton("Go Back");
		goBackButton.addActionListener(this);
		
		pane.add(label); 
		pane.add(listScroll);
		//pane.add(addButton);
		//pane.add(delButton);
		pane.add(goBackButton);
		
		return pane; 
	}
	
	public JPanel record() {
		pane = new JPanel(); 
		pane.setAlignmentX(Component.CENTER_ALIGNMENT);
		pane.setPreferredSize(new Dimension(sizeXR, sizeYR));
		pane.setMaximumSize(new Dimension(sizeXR, sizeYR)); 
	    
	    JLabel label = new JLabel("Upload transcript");
		label.setFont(normalFont);
		
		recordButton = new JButton("Choose File"); 
		recordButton.addActionListener(this);
		file = new JTextArea(1, 10);
		file.setText("(no file selected -- must be .txt)");
		JScrollPane scroll = new JScrollPane (file, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		file.setEditable(false);
		resultsButton = new JButton("See Results");
		resultsButton.addActionListener(this);
		resultsButton.setEnabled(false);
		goBackButton = new JButton("Go Back");
		goBackButton.addActionListener(this);

		pane.add(label);
		pane.add(Box.createRigidArea(new Dimension(sizeXR, 10)));
		pane.add(recordButton); 
		pane.add(scroll); 
		pane.add(resultsButton);
		pane.add(goBackButton);
	    
		return pane; 
	}
	
	public JPanel results() {
		pane = new JPanel(); 
		pane.setAlignmentX(Component.CENTER_ALIGNMENT);
		pane.setPreferredSize(new Dimension(sizeXL, sizeYL));
		pane.setMaximumSize(new Dimension(sizeXL, sizeYL)); 
		
		JLabel label = new JLabel("Crutch words in your speech: ");
		label.setFont(normalFont);

		SpeechAnalysis res = new SpeechAnalysis(crutchList, speech);
		ArrayList<String> crutchCount = res.results();
		
		String [] countArray = new String [crutchCount.size()];
		int count = 0; 
		
		for (String element : crutchCount) {
			countArray[count] = element; 
			count++; 
		}
		
		JList<String> list = new JList<String>(countArray); 
		JScrollPane listScroll = new JScrollPane(list);
		listScroll.setPreferredSize(new Dimension(sizeXL, sizeYL/2));

		/*detailButton = new JButton("See Details");
		detailButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String word = list.getSelectedValue(); 
				
				JFrame popup = new JFrame("Error");
				popup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				popup.setSize(300, 300);
				popup.getContentPane().setLayout(new BoxLayout(popup.getContentPane(), BoxLayout.Y_AXIS));
				
			    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			    int x = (int) ((screen.getWidth() - popup.getWidth()) / 2);
			    int y = (int) ((screen.getHeight() - popup.getHeight()) / 2);
			    popup.setLocation(x, y);
				
				
			    popup.setVisible(true);
			}
			
		});*/

		recScreenButton = new JButton("Go Back");
		recScreenButton.addActionListener(this);
		
		pane.add(label); 
		pane.add(listScroll);
		//pane.add(detailButton);
		pane.add(recScreenButton);
		
		return pane; 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == seeListButton) {
			changePanel(sizeYL, list());
		} else if (e.getSource() == recScreenButton) {
			changePanel(sizeYR, record());
		} else if (e.getSource() == goBackButton) {
			changePanel(sizeY, welcome());
		} else if (e.getSource() == recordButton) {
			try {
				final JFileChooser fc = new JFileChooser(); 
				fc.addChoosableFileFilter(new FileFilter() {
					@Override
					public boolean accept(File f) {
						if (f.isDirectory()) {
							return true; 
						}
						return f.getName().toLowerCase().endsWith(".txt");
					}
	
					@Override
					public String getDescription() {
						// TODO Auto-generated method stub
						return "Textfiles (*.txt)";
					}
				});
				int r = fc.showOpenDialog(null);
	    
				if (r == JFileChooser.APPROVE_OPTION) { 
					String path = fc.getSelectedFile().getAbsolutePath();
					
					if (path.toLowerCase().endsWith(".txt")) {
						speech = new Speech(path);
						file.setText(path); 
						resultsButton.setEnabled(true);
						pane.revalidate();
					} else {
						throw new Exception();
					}
				} 
			} catch (Exception e1) {
				error("Invalid File");
			} 
		} else if (e.getSource() == resultsButton) {			
			changePanel(sizeYL, results());
		}
	}
	
	private void error(String message) {
		JFrame error = new JFrame("Error");
		error.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		error.setSize(200, 100);
		error.getContentPane().setLayout(new BoxLayout(error.getContentPane(), BoxLayout.Y_AXIS));
		
	    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((screen.getWidth() - error.getWidth()) / 2);
	    int y = (int) ((screen.getHeight() - error.getHeight()) / 2);
	    error.setLocation(x, y);
		
		JLabel msg = new JLabel("ERROR: " + message);
		JButton close = new JButton("Close");
		close.addActionListener(e -> error.dispose());
		error.getContentPane().add(msg);
		error.getContentPane().add(close);
		error.setVisible(true);
	}
	
	private void changePanel(int ysize, JPanel panel) {
		frame.getContentPane().removeAll(); 
		frame.getContentPane().add(Box.createRigidArea(new Dimension(0, (frameSize - ysize) / 2)));
		frame.getContentPane().add(panel);		
		frame.revalidate();
		frame.repaint();
	}
	
	public static void createAndDisplayGUI() {
		frame = new JFrame("Speech Helper"); 

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameSize, frameSize);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		// center window on screen
	    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((screen.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((screen.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);	
		
	    GUI gui = new GUI(); 
	    
		gui.changePanel(sizeY, gui.welcome());
		
	    frame.setResizable(false);
	    frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		GUI.createAndDisplayGUI();
	}

}
