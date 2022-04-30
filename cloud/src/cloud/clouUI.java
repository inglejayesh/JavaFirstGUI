package cloud;

import java.awt.EventQueue;

import java.util.regex.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class clouUI {

	private JFrame frame;
	private JTextField textSentenceField;
	private JTextField txtLetterField;
	private JLabel txtOutput;
	private JLabel errorInSentence;
	private JLabel errorInLetter;
	private String trimErrorMsg = "The letter does not exist in the sentence";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clouUI window = new clouUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public clouUI() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 561, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel txtHeading = new JLabel("Assignment 03");
		txtHeading.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtHeading.setHorizontalAlignment(SwingConstants.CENTER);
		txtHeading.setBounds(155, 11, 113, 36);
		frame.getContentPane().add(txtHeading);
		
		JLabel txtSentence = new JLabel("Enter a sentence :");
		txtSentence.setBounds(34, 78, 111, 14);
		frame.getContentPane().add(txtSentence);
		
		JLabel txtLetter = new JLabel("Enter a letter :");
		txtLetter.setBounds(34, 121, 96, 14);
		frame.getContentPane().add(txtLetter);
		
		textSentenceField = new JTextField();
		
		textSentenceField.setBounds(155, 75, 132, 20);
		frame.getContentPane().add(textSentenceField);
		textSentenceField.setColumns(10);
		
		txtLetterField = new JTextField(11);		
		txtLetterField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
                  char c = e.getKeyChar();
				
				
				if (Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c))
				{
					
					txtLetterField.setEditable(true);
				}
				else
				{
					txtLetterField.setEditable(false);
				}
			}
		});
		txtLetterField.setColumns(10);
		txtLetterField.setBounds(155, 118, 132, 20);
		frame.getContentPane().add(txtLetterField);
		
		JButton btnSubmitButton = new JButton("Submit");
		btnSubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateForm()) {
				    result();
				}
			}
		});
		btnSubmitButton.setBounds(179, 165, 89, 23);
		frame.getContentPane().add(btnSubmitButton);
		
		JLabel txtResult = new JLabel("Result :");
		txtResult.setBounds(34, 218, 46, 14);
		frame.getContentPane().add(txtResult);
		
		txtOutput = new JLabel("");
		txtOutput.setBounds(155, 218, 366, 14);
		frame.getContentPane().add(txtOutput);
		
		errorInSentence = new JLabel("");
		errorInSentence.setBounds(301, 78, 220, 14);
		frame.getContentPane().add(errorInSentence);
		
		errorInLetter = new JLabel("");
		errorInLetter.setBounds(301, 121, 220, 14);
		frame.getContentPane().add(errorInLetter);
	}
	public boolean validateForm()
	{
		  String _sentence = textSentenceField.getText();
		  String _letter =  txtLetterField.getText();
		  boolean _isValid = true;

		  errorInSentence.setText("");
		  errorInLetter.setText("");
		  txtOutput.setText("");

		  //regex
		  String  sentenceRegex = "([a-zA-Z]+[\s]*)+";
		  String letterRegex = "^[a-zA-z]$";
		  
		  Pattern sentencePattern = Pattern.compile(sentenceRegex, Pattern.MULTILINE);
		  Matcher sentenceMatcher = sentencePattern.matcher(_sentence);

		  Pattern letterPattern = Pattern.compile(letterRegex, Pattern.MULTILINE);
		  Matcher letterMatcher = letterPattern.matcher(_letter);
		  
		  if (_sentence == null || _sentence.trim().length() == 0) {
		    errorInSentence.setText("*Blanks are not allowed");
		    _isValid = false;
		  } else if (_sentence.length() > 500 ||  !sentenceMatcher.find()) {
			  errorInSentence.setText("*Please Enter Valid Sentence");
		    _isValid = false;
		  }

		  if (_letter == null || _letter.trim().length() == 0) {
			  errorInLetter.setText("*Blank are not allowed");
		    _isValid = false;
		  } else if (_letter.length() > 1 || !letterMatcher.find()) {
			  errorInLetter.setText("*Please Enter Valid Letter");
		    _isValid = false;
		  }
		  return _isValid;
	}
	public void result() {
		
		  String _sentence = textSentenceField.getText();
		  String _letter =  txtLetterField.getText();
		  int index = _sentence.indexOf(_letter); //to check the letter present in sentence
		  String result = index == -1 ? trimErrorMsg : _sentence.substring(index + 1); //validating the letter in sentence
		  txtOutput.setText(result);
		}
}
