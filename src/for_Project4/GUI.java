package for_Project4;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class GUI extends JFrame {
	
	private JLabel instructionsL;
	private JTextField textEnterTF;
	private JButton enterB;
	private JTextPane pane;
	private Container myCP;
	private Spellchecker sp;
	
	public GUI() {
		super("A Super Stupendous Spellchecker");
		setSize(455, 200);
		setLocation(100,100);
		myCP = getContentPane();
		myCP.setLayout(new FlowLayout());
		
		
		
		
		// you need to path where the wordList is coming from!
		sp = new Spellchecker();
		String textfile = "src/for_Project4/wordList.txt";
        sp.lookup(textfile);
		
		
        
        
        instructionsL = new JLabel("Paste your text below and click the Enter button:");
		instructionsL.setFont(new Font("Arial", Font.PLAIN, 20));
		myCP.add(instructionsL);
		
		textEnterTF = new JTextField(20);
		textEnterTF.setFont(new Font("Arial", Font.PLAIN, 20));
		textEnterTF.setText("");
		myCP.add(textEnterTF);
		
		enterB = new JButton("Enter");
		enterB.setFont(new Font("Arial", Font.PLAIN, 20));
		enterB.addActionListener(new EnterBHandler());
		myCP.add(enterB);
		
		pane = new JTextPane();
		pane.setFont(new Font("Arial", Font.PLAIN, 15));
		myCP.add(pane);
		
		setVisible(true);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
	
	// A method to append a String "s" of Color "c" to the JTextPane.
	public void append(Color c, String s) {
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
		int len = pane.getDocument().getLength();
		pane.setCaretPosition(len);
		pane.setCharacterAttributes(aset, false);
		pane.replaceSelection(s);
	}
	
	
	
	
	// TO DO:	
	public class EnterBHandler implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	// Clear the pane.
	        pane.setText("");

	     // Then, spell check the text entry given in the textEnterTF,
	        String letters = textEnterTF.getText();
	        String[] vocab = letters.split(" ");
	        
	        //Spell checker sp = new Spell checker();
	        
	       

	     //  append words either in black or in red to the pane.
	        for (String i: vocab){
	            String fixed = removePunctAndDowncase(i);
	            boolean isCorrect = sp.Correct(fixed);
	            
	            Color wordColor;
	            if (isCorrect) {
	                wordColor = Color.BLACK;
	            } else {
	                wordColor = Color.RED;
	            }
	            append(wordColor, i + " ");

	         // Format the text so that, every ten words,
	            // a new line is printed.
	            String[] word = pane.getText().split(" ");
	            if (word.length % 10 == 0){
	                append(Color.BLACK, "\n");
	            }
	        }
	    }

		private String removePunctAndDowncase(String word) {
	        String punctuation = ".,?!-\";:()%$#@";
	        String output = word.replaceAll("[" + punctuation + "]", "");
	        output = output.replaceAll("’", "'");
	        return output.toLowerCase();
		}
	}

			
	public static void main(String[] args) {
		GUI myGui = new GUI();
	}


	
}
