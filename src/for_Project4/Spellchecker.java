package for_Project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Spellchecker {

    BinarySearchTree<String> dictionary;

    public Spellchecker() {
        dictionary = new BinarySearchTree<>();
        //String textfile = "wordList.txt";
        //lookup(textfile);
    }

    public void lookup(String f) {
        
    	//File myfile = new File(f);
    	//System.out.println(f);
    	
    	try {
            File myfile = new File(f);
            
            //System.out.println(myfile);
            
            Scanner scan = new Scanner(myfile);
            
           // System.out.println(myfile);
            
            while (scan.hasNextLine()) {
                String word = scan.nextLine();
                dictionary.add(word);
                
               // System.out.println(word);
            }
            scan.close();
        
        } catch (FileNotFoundException e) {
            System.out.println("File not found, "+ e.getMessage());
            
            
            //e.printStackTrace();
        }
    }

    public boolean Correct(String word) {
        return dictionary.search(word) != null;
    }

    private String removePunctAndDowncase(String input) {
        String punctuation = ".,?!-\";:()%$#@";
        String output = input.replaceAll("[" + punctuation + "]", "");
        output = output.replaceAll("’", "'");
        return output.toLowerCase();
    }
}
