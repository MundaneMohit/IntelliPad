import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;


public class WordPredictorUltimate extends JFrame implements ActionListener, KeyListener {
	
	private static final long serialVersionUID = 1L;

	//Creating interface
	JLabel img = new JLabel();

	Container c = new Container();
	Container saveback = new Container();
	Container suggestions = new Container();
	Container tools = new Container();

	CardLayout card;
	WordStorage ws = new WordStorage();

	URL bgpath = getClass().getResource("/resources/bgimg.jpg");
	ImgPanel j1 = new ImgPanel(bgpath);
	ImgPanel j2= new ImgPanel(bgpath);
	ImgPanel j3 = new ImgPanel(bgpath);
	ImgPanel j4 = new ImgPanel(bgpath);
	ImgPanel j5 = new ImgPanel(bgpath);

	JButton train = new JButton("Train");
	JButton newFile = new JButton("New...");
	JButton instructions = new JButton("Instructions");
	JButton stats = new JButton("Stats");
	JButton about = new JButton("About");
	JButton exit = new JButton("Exit");
	JButton save = new JButton("SAVE");
	JButton back = new JButton("BACK");
	JButton gotit = new JButton("Got it !");
	JButton ok = new JButton("OK");
	JButton boldButton = new JButton(new ImageIcon(getClass().getResource("/resources/boldicon.png")));
	JButton italicsButton = new JButton(new ImageIcon(getClass().getResource("/resources/italicsicon.png")));
	JButton underlineButton = new JButton(new ImageIcon(getClass().getResource("/resources/underlineicon.png")));
	JButton undoButton = new JButton(new ImageIcon(getClass().getResource("/resources/undoicon.png")));
	JButton about1 = new JButton(new ImageIcon(getClass().getResource("/resources/ironman.png")));
	JButton pred1 = new JButton();
	JButton pred2 = new JButton();
	JButton pred3 = new JButton();

	DefaultStyledDocument doc = new DefaultStyledDocument();
	StyleContext sc = new StyleContext();
	Style bold;
	Style italics;
	Style underline;
	
	JTextPane myFile = new JTextPane(doc);
	
	JTextArea howto = new JTextArea();
	JTextArea stat1 = new JTextArea();
	JTextArea  aboutme = new JTextArea(8, 15);

	JTextField wordEntryField = new JTextField(20);

	JLabel title = new JLabel("Welcome to Word Predictor Ultimate");
	JLabel tlbl1 = new JLabel("Enter text here: ");
	JLabel tlbl2 = new JLabel("Instructions:");

	Font myFont = new Font("SansSerif", Font.BOLD, 32);
	Font headings = new Font("Serif" , Font.BOLD, 18);

	// Trains the program
	// Adds listener to Components
	// Adds Components to Panels
	// Sets layouts
	// Adds Panels to Frame
	public WordPredictorUltimate() {

		// Training on mobydick.txt
		try {
			ws.train(getClass().getResourceAsStream("/resources/mobydick.txt"));
		} catch (NullPointerException | IOException e1) {
			System.out.println(e1.toString());		}
		ws.build();
		// END OF Training


		// Adding JPanels to JFrame
		card = new CardLayout(5,5);
		c= this.getContentPane();
		c.setLayout(card);
		c.add("Mainpg", j1);
		c.add("Startpg", j2);
		c.add("Instrpg", j3);
		c.add("Statspg", j4);
		c.add("Aboutpg", j5);

		// Adding listeners to Components
		wordEntryField.addKeyListener(this);
		train.addActionListener(this);
		newFile.addActionListener(this);
		instructions.addActionListener(this);
		about.addActionListener(this);
		stats.addActionListener(this);
		exit.addActionListener(this);
		pred1.addActionListener(this);
		pred2.addActionListener(this);
		pred3.addActionListener(this);
		boldButton.addActionListener(this);		
		italicsButton.addActionListener(this);		
		underlineButton.addActionListener(this);
		undoButton.addActionListener(this);
		save.addActionListener(this);
		back.addActionListener(this);
		gotit.addActionListener(this);
		ok.addActionListener(this);
		about1.addActionListener(this);

		// Setting layout for JPanels
		j1.setLayout(new BoxLayout(j1, BoxLayout.PAGE_AXIS));
		j2.setLayout(new BoxLayout(j2, BoxLayout.PAGE_AXIS));
		j3.setLayout(new BoxLayout(j3, BoxLayout.PAGE_AXIS));
		j4.setLayout(new BoxLayout(j4, BoxLayout.PAGE_AXIS));
		j5.setLayout(new BoxLayout(j5, BoxLayout.PAGE_AXIS));

		// Adding components to Panel 1 (Main Page)
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setFont(myFont);
		train.setAlignmentX(Component.CENTER_ALIGNMENT);
		train.setMaximumSize(instructions.getMaximumSize());
		newFile.setAlignmentX(Component.CENTER_ALIGNMENT);
		newFile.setMaximumSize(instructions.getMaximumSize());
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
		stats.setAlignmentX(Component.CENTER_ALIGNMENT);
		stats.setMaximumSize(instructions.getMaximumSize());
		about.setAlignmentX(Component.CENTER_ALIGNMENT);
		about.setMaximumSize(instructions.getMaximumSize());
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		exit.setMaximumSize(instructions.getMaximumSize());

		j1.add(title);
		j1.add(Box.createRigidArea(new Dimension(0, 20)));
		j1.add(train);
		j1.add(Box.createRigidArea(new Dimension(0, 10)));
		j1.add(newFile);
		j1.add(Box.createRigidArea(new Dimension(0, 10)));
		j1.add(instructions);
		j1.add(Box.createRigidArea(new Dimension(0, 10)));
		j1.add(stats);
		j1.add(Box.createRigidArea(new Dimension(0, 10)));
		j1.add(about);
		j1.add(Box.createRigidArea(new Dimension(0, 10)));
		j1.add(exit);

		// Adding components to Panel 2 (New... Page)
		saveback.setLayout(new BoxLayout(saveback, BoxLayout.LINE_AXIS));
		saveback.add(save);
		saveback.add(Box.createRigidArea(new Dimension(50, 0)));
		saveback.add(back);
		tlbl1.setFont(headings);
		tlbl1.setAlignmentX(Component.CENTER_ALIGNMENT);
		myFile.setEditable(false);
		myFile.setBorder(BorderFactory.createEtchedBorder(2, Color.BLACK, Color.BLACK));
		wordEntryField.setMaximumSize(new Dimension(600, 10));
		pred1.setFont(pred1.getFont().deriveFont(Font.BOLD));
		pred2.setFont(pred1.getFont().deriveFont(Font.BOLD));
		pred3.setFont(pred1.getFont().deriveFont(Font.BOLD));
		bold = sc.addStyle("BoldStyle", null);
		StyleConstants.setBold(bold	, true);
		italics = sc.addStyle("ItalicStyle" , null);
		StyleConstants.setItalic(italics, true);
		underline = sc.addStyle("UnderlineStyle" , null);
		StyleConstants.setUnderline(underline, true);
		suggestions.setLayout(new BoxLayout(suggestions, BoxLayout.LINE_AXIS));
		suggestions.add(pred1);
		suggestions.add(Box.createRigidArea(new Dimension(30, 0)));
		suggestions.add(pred2);
		suggestions.add(Box.createRigidArea(new Dimension(30, 0)));
		suggestions.add(pred3);
		tools.setLayout(new BoxLayout(tools, BoxLayout.LINE_AXIS));
		tools.add(boldButton);
		tools.add(italicsButton);
		tools.add(underlineButton);
		tools.add(undoButton);
		
		
		j2.add(tlbl1);
		j2.add(Box.createRigidArea(new Dimension(0, 5)));
		j2.add(wordEntryField);
		j2.add(suggestions);
		j2.add(Box.createRigidArea(new Dimension(0, 20)));
		j2.add(tools);
		j2.add(myFile);
		j2.add(Box.createRigidArea(new Dimension(0, 10)));
		j2.add(saveback);

		// Adding components to Panel 3 (Instructions Page)
		howto.setText("\n1. Use train option to train words from a text file."
				+ "\n2. The program has already been trained on 200,000 words."
				+ "\n3. Learns every word you type. The more you type, the better it gets."
				+ "\n4. Enter word into the textbox."
				+ "\n5. Press 1, 2 or 3 to select the desired word."
				+ "\n6. Press 'Space' to start the next word."
				+ "\n7. Press 'Enter' to start a new line."
				+ "\n8. Save your file to a desired location.");
		howto.setEditable(false);
		howto.setFont(new Font("MonoSpaced", Font.BOLD, 13));
		howto.setBorder(null);
		tlbl2.setFont(headings);
		tlbl2.setAlignmentX(Component.CENTER_ALIGNMENT);
		gotit.setAlignmentX(Component.CENTER_ALIGNMENT);

		j3.add(tlbl2);
		j3.add(howto);
		j3.add(gotit);

		// Adding components to Panel 4 (Stats Page)
		stat1.setText("Total training words: ");
		stat1.setEditable(false);
		stat1.setBackground(getForeground());

		j4.add(stat1);
		j4.add(ok);

		// Adding components to Panel 5 (About Page)
		aboutme.setWrapStyleWord(true);
		aboutme.setLineWrap(true);
		aboutme.setMaximumSize(new Dimension(500, 300));
		aboutme.setBorder(null);
		aboutme.setBackground(new Color(0,0,0,0));
		aboutme.setFont(new Font("Serif", Font.BOLD, 15));
		aboutme.setText("Word Predictor Ultimate is a text editor that simply predicts what you are about to type."
				+ "It uses multiple Hashmaps to store the encountered words and predicts the most suitable words dynamically."
				+ "This project has been developed in order to bring the advanced keyboard functionalities from handheld "
				+ "devices to laptops and Desktop PCs."
				+ "\n\n\n\t\t\tCreated by:"
				+ "\n\t\t\t\tMohit Bansal");
		aboutme.setAlignmentX(Component.CENTER_ALIGNMENT);
		aboutme.setEditable(false);
		about1.setContentAreaFilled(false);
		about1.setBorder(null);
		about1.setAlignmentX(Component.CENTER_ALIGNMENT);

		
		j5.add(aboutme);
		j5.add(about1);

		setSize(700, 300);
	}

	// Switch between panels upon button click
	// Select the prediction when clicked
	// Shows save file dialog box when save option clicked
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == train)
			getTrainfile();
		else if(src == newFile){
			myFile.setText("");
			predBox();
			card.show(c, "Startpg");
			wordEntryField.requestFocusInWindow();
		}
		else if(src==instructions)
			card.show(c, "Instrpg");
		else if(src==stats){
			card.show(c, "Statspg");
			getStats();
		}
		else if (src == about)
			card.show(c, "Aboutpg");
		else if (src == exit) 
			System.exit(0);
		else if(src==back||src==gotit||src==ok||src==about1) 
			card.show(c, "Mainpg");
		else if(src == save)
			saveFile();
		else if (src == boldButton & myFile.getSelectedText()!=null)
			doc.setCharacterAttributes(myFile.getSelectionStart(), myFile.getSelectedText().length(), bold, true);	
		else if (src == italicsButton & myFile.getSelectedText()!=null)
			doc.setCharacterAttributes(myFile.getSelectionStart(), myFile.getSelectedText().length(), italics, true);	
		else if (src == underlineButton & myFile.getSelectedText()!=null)
			doc.setCharacterAttributes(myFile.getSelectionStart(), myFile.getSelectedText().length(), underline, true);	
		else if (src == undoButton & !myFile.getText().isEmpty()) {
			try {
				doc.remove(doc.getLength() - getLastWord(myFile).length() - 1, getLastWord(myFile).length()+1);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
		}
		else if(src == pred1 & pred1.getText() != "")
			keyReleased(new KeyEvent(pred1, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_1, '1' ));
		else if(src == pred2 & pred2.getText() != "")
			keyReleased(new KeyEvent(pred1, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_2, '2' ));
		else if(src == pred3 & pred3.getText() != "")
			keyReleased(new KeyEvent(pred1, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_3, '3' ));
		
	}

	// Called when user enters something in the TextField
	@Override
	public void keyReleased(KeyEvent e) {

		if(e.getKeyChar() == (char)KeyEvent.VK_BACK_SPACE)
			updateBest(wordEntryField.getText());
		else if(e.getKeyChar() == (char)KeyEvent.VK_ENTER)
		{
			myFile.setText(myFile.getText() + wordEntryField.getText() + "\n");
			wordEntryField.setText("");
			predBox();
		}
		else if(e.getKeyChar() == ' '){
			ws.trainNextWord(getLastWord(myFile), wordEntryField.getText());
			ws.trainWord(wordEntryField.getText());
			myFile.setText(myFile.getText() + wordEntryField.getText());
			showNextBest(wordEntryField.getText());
		}
		else if(e.getKeyChar()=='1'& pred1.getText() != "")
		{
			ws.trainWord(pred1.getText());
			ws.trainNextWord(getLastWord(myFile), pred1.getText());
			myFile.setText(myFile.getText() + pred1.getText()+" ");
			showNextBest(pred1.getText());
		}
		else if(e.getKeyChar()=='2'& pred2.getText() != "")
		{
			ws.trainWord(pred2.getText());
			ws.trainNextWord(getLastWord(myFile), pred2.getText());
			myFile.setText(myFile.getText() + pred2.getText()+" ");
			showNextBest(pred2.getText());
		}
		else if(e.getKeyChar()=='3'& pred3.getText() != "")
		{
			ws.trainWord(pred3.getText());
			ws.trainNextWord(getLastWord(myFile), pred3.getText());
			myFile.setText(myFile.getText() + pred3.getText()+" ");
			//para.append(pred3.getText()+" ");
			showNextBest(pred3.getText());
		}
		else 
			updateBest(wordEntryField.getText());
	}	


	// Calls build() method
	// Clears  wordEntryField 
	// Shows next word suggestions
	private void showNextBest(String x) {
		ws.build();
		String nextgetter=ws.getNextBest(x.trim());
		String nextbest3[]= nextgetter.split("\n");
		wordEntryField.setText("");
		int i=nextbest3.length;
		pred1.setText("");
		pred2.setText("");
		pred3.setText("");
		if(i>0)
			pred1.setText(nextbest3[0].toString());
		if(i>1)
			pred2.setText(nextbest3[1].toString());
		if(i>2)
			pred3.setText(nextbest3[2].toString());
		predBox();
	}

	// Updates suggestion when called
	private void updateBest(String x){
		String getter=ws.getBest(x);
		String best3[]= getter.split("\n");
		int i=best3.length;
		pred1.setText("");
		pred2.setText("");
		pred3.setText("");
		if(i>0)
			pred1.setText(best3[0].toString());
		if(i>1)
			pred2.setText(best3[1].toString());
		if(i>2)
			pred3.setText(best3[2].toString());
		predBox();

	}

	// Retrieves last word that was entered to predict next word
	private String getLastWord(JTextPane para2) {
		String[] allWords = para2.getText().split(" ");
		String lastWord = allWords[allWords.length - 1];
		return lastWord;
	}

	// Hides prediction button if it is empty
	private void predBox() {
		wordEntryField.requestFocusInWindow();
		if(pred1.getText()=="")
			pred1.setVisible(false);
		else 
			pred1.setVisible(true);
		if(pred2.getText()=="")
			pred2.setVisible(false);
		else 
			pred2.setVisible(true);
		if(pred3.getText()=="")
			pred3.setVisible(false);
		else 
			pred3.setVisible(true);

	}

	// Accepts a file from user to train program
	private void getTrainfile() {
		JFileChooser opener = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files" , "txt");
		opener.setFileFilter(filter);
		opener.setCurrentDirectory(new File(System.getProperty("user.dir")));
		opener.setDialogTitle("Select Training File");
		int returnval = opener.showDialog(null, "Select");

		if(returnval==JFileChooser.APPROVE_OPTION)
		{
			try {
				ws.train(new FileInputStream(opener.getSelectedFile()));
			} catch (NullPointerException | IOException e) {
				e.printStackTrace();
			}
			ws.build();
			JOptionPane.showMessageDialog(null, "File trained successfully!", "DONE", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// Saves the typed text to a text file	
	private void saveFile()
	{
		JFileChooser saver = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files" , "txt");
		saver.setFileFilter(filter);
		saver.setCurrentDirectory(new File(System.getProperty("user.dir")));
		saver.setDialogTitle("Save as...");
		int returnval = saver.showSaveDialog(null);
		File file = saver.getSelectedFile();  
		BufferedWriter txt2file = null;  
		if (returnval == JFileChooser.APPROVE_OPTION)  
		{  
			try  
			{  
				txt2file = new BufferedWriter( new FileWriter( file.getAbsolutePath()+".txt"));  
				txt2file.write( myFile.getText());  
				txt2file.close( );  
				JOptionPane.showMessageDialog(this, "The File was Saved Successfully!",  
						"Success!", JOptionPane.INFORMATION_MESSAGE);  
			}  
			catch (IOException e)  
			{  
				JOptionPane.showMessageDialog(this, "The Text could not be Saved!",  
						"Error!", JOptionPane.INFORMATION_MESSAGE);  
			}  
		}  
	}

	// Retrieves important stats about the program using Stats.java
	private void getStats()
	{
		String allstats = "";
		// Test lookup using random prefixes between 1-6 characters
		allstats += "Random load test:\n\n";
		Stats stats1 = new Stats();
		final String VALID = "abcdefghijklmnopqrstuvwxyz'";
		final long TEST_NUM = 1000000;
		long hits = 0;
		for (long i = 0; i < TEST_NUM; i++) {
			String prefix = "";
			for (int j = 0; j <= (int) (Math.random() * 6); j++)
				prefix += VALID.charAt((int) (Math.random() * VALID.length()));
			String entry = ws.getBest(prefix);
			if (entry != "")
				hits++;
		}
		allstats += stats1;
		allstats += "\nHit % = " + ((double) hits / TEST_NUM * 100.0);
		//return allstats;
		stat1.setAlignmentX(Component.CENTER_ALIGNMENT);
		stat1.setText("Total training words: " + ws.getTrainingCount() + "\n\n"+ allstats );


	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	// Main method to start Swing Applet
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();}
		WordPredictorUltimate cmpl = new WordPredictorUltimate();
		cmpl.setSize(600,400);
		cmpl.setVisible(true);
		cmpl.setLocationRelativeTo(null);
		cmpl.setDefaultCloseOperation(EXIT_ON_CLOSE);


	}
}

class ImgPanel extends JPanel
{
	/* USED FOR DISPLAYING A BACKGROUND IMAGE */
	private static final long serialVersionUID = 1L;
	private Image img;

	public ImgPanel(URL imgpath) {
		img = new ImageIcon(imgpath).getImage();
	}

	@Override
	protected void paintComponent(Graphics g) {
		//super.paintComponent(g);
		g.drawImage(img , 0, 0, null);
	}

}


