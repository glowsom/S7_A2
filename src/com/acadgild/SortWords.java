package com.acadgild;
import java.util.*;
/*
 * WAP to sort words of given strings, then sort characters in each word
 * For example String = “ My name is XYZ” so My, name, is, XYZ is separate
 * so first sort these
 * then sort the character of each word.
 * 
 * For the sake of comments, the terms low,least,less imply alphabetical precedence
 * 
 */
public class SortWords {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner user = new Scanner(System.in);
		
		//Asking user for his input
		System.out.println("Please enter a phrase");
		
		//Taking user's input
		String words = user.nextLine();
		user.close();
		
		/*
		 * Print out words in alphabetical order while capturing an array of words
		 * in ascending order. More efficient since we will need to sort them again.
		 */		
		System.out.println("\nWords in alphabetical order:");
		String[] wordArray = wordSorter(words);
		
		//Separator
		System.out.println();
	
		//Print out Alphabetically sorted (chars) 
		System.out.println("\nCharacters of each word in alphabetical order:");
		for(int i=0; i<wordArray.length; i++){
			System.out.print(sortChars(wordArray[i])+" ");
		}
	}
	

	/*
	 * Best to Split String into String[] for easy comparison.
	 * To sort the words in the String[], we will start from the first word.
	 * That word will be compared to the rest of the words in the array, until the
	 * least-value word is found.
	 * If the first word is the least nothing happens, because it's alphabetically positioned;
	 * else, that word will be switched with the least-value word found.
	 * That CURRENT POSITION will never be used in future comparisons, because it's already known
	 * to be the least compared to the others after it. This will be tracked by variable "int i"
	 * 
	 * As "int i" progresses through it's iterations, it will be compared with all words after it;
	 * "int j" will iterate through the positions of all the words after word at index "i".
	 * 
	 * This way, every position before "index i" will be ordered, which means at the end of iterations
	 * every position should be ordered.
	 */
	public static String[] wordSorter(String string){
		
		String[] wordsArray = string.split(" ");	//Split words into an array
		
		/*
		 * As we progress through the array, this variable will hold several words temporarily
		 * as such, a StringBuffer will be more economical the String with computation resources.
		 */
		StringBuffer hold = new StringBuffer("");	//StringBuffer for string manipulation
		
		int lowestWordIndex;
		
		//Words at all indices before i are alphabetically ordered
		for(int i=0; i<wordsArray.length; i++){
			
			lowestWordIndex = i;			//Tracking the index of the lowest value word, starting with i
			hold.append(wordsArray[i]);		//hold word at index i for possible switching later on
			
			//Compare word at index 'i' with words that come after it [i compared to j(which is, i+1, i+2, ... )]
			for(int j=i+1; j<wordsArray.length; j++){
				
				/*
				 * If current word at index j is lower than lowest word found so far,
				 * lowestWordIndex in now j
				 */
				if(wordsArray[lowestWordIndex].compareToIgnoreCase(wordsArray[j]) > 0 )
					lowestWordIndex = j;
			}
			/*
			 * If lowestWordIndex is i, then nothing happens, if not lowest word must switch positions with
			 * word at index i.
			 */
			if(lowestWordIndex != i){
				wordsArray[i]=wordsArray[lowestWordIndex];	//lowest word found taking it's rightful place
				wordsArray[lowestWordIndex]=hold.toString();	//word at index i switching places
			}
			hold.setLength(0);	//Reset StringBuffer
		}

		//Prints out Alphabetic order of words
		for(int i=0; i<wordsArray.length; i++){
			System.out.print(wordsArray[i]+" ");
		}
		
		return wordsArray; //Returns array for easy handling of Strings in next phase
	}
	
	/*
	 * To sort the chars in each word, we will start from the first char.
	 * that char will be compared to the rest of the chars in the word, until the
	 * least-value char is found.
	 * If the first char is the least nothing happens, because it's alphabetically positioned;
	 * else, that char will be switched with the least-value char found.
	 * That CURRENT POSITION will never be used in future comparisons, because it's already known
	 * to be the least compared to the others after it. This will be tracked by variable "int i"
	 * 
	 * As "int i" progresses through it's iterations, it will be compared with all chars after it;
	 * "int j" will iterate through the positions of all the characters after it.
	 * 
	 * This way, every position before "int i" will be ordered, which means at the end of iterations
	 * every position should be ordered.
	 */
	static String sortChars(String word){
		
		char hold;
		int lowestCharIndex;
		
		/*
		 * We will be directly switching the positions of chars in the word,
		 * for that reason a StringBuffer will be more resource efficient.
		 */
		StringBuffer sBWord = new StringBuffer(word);
		
		for(int i=0; i<sBWord.length(); i++){
			
			hold = sBWord.charAt(i);	//holds the char in index "i" of word
			lowestCharIndex = i;		//starting with i, this will hold the index of the lowest char
			
			//"int j" will iterate through all chars indices after current index to find the lowest char
			for(int j=1+i; j<sBWord.length(); j++){
				
				//Compare lowest found char to next char
				if(Character.toLowerCase(sBWord.charAt(lowestCharIndex)) > Character.toLowerCase(sBWord.charAt(j)))
					lowestCharIndex = j;	//holds the index of the lowest found char so far
				
			}
			/*
			 * if the index of lowest char is i, nothing must change, but if it isn't
			 * then the lowest char must switch places with char at index i, for later comparison
			 */
			if(lowestCharIndex != i){
				sBWord.setCharAt(i, sBWord.charAt(lowestCharIndex)); //lowest char taking it's rightful place
				sBWord.setCharAt(lowestCharIndex, hold); //char at index i taking the other place
			}
		}
		
		return sBWord.toString();//return alphabetically ordered word
	}

}
