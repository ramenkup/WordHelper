import java.util.*;

/* CLASS: WORDHELPER
 * AUTHOR: SPENCER KLINGE - CSC 227 - DUE FEB 11TH
 * 
 * PURPOSE: TAKES AN INPUTED WORD BY THE USER. CALCULATES THE NUMBER OF SYLLABLES
 * 			ACORDING THE THE ASSIGNMENT SPECIFICATONS. THEN PRESENTS A SYLLABIZED VERSION
 * 			PHONETICLY PRONOUNCED, '/' SEPERATING EACH SYLLABLE.
 * 
 * CLASS METHODS:TRANSLATESYLLABLE(STRING)
 * 
 * INSTANCE METHODS: SETWORD(STRING), GETWORD(), NUMBEROFSYLLABLES(), SYLLABLIZE(),
 * 					 PRONOUNCE(), WORDHELPER(STRING), MAIN
 */

public class WordHelper {
	private String word; //USER INPUTED WORD
	private final String letters = new String("abcdefghijklmnopqrstuvwxyz"); /* SEARCHABLE
	DATABASE FOR LETTERS OF THE ALPHABET*/
																		
																			
	private final String vowls = new String("aeiouy"); /* SEARCHABLE
	DATABASE FOR EVERY VOWEL*/
														
	private final String consonants = new String("bcdfghjklmnpqrstvwxz"); /* SEARCHABLE
	DATABASE FOR EVERY CONSONANT*/
																			
/*METHOD: WORDHELPER(STRING)
 * 
 *PURPOSE: CONSTRUCTOR FOR THE WORD HELPER CLASS. FORMATS THE USER INPUT TO LOWERCASE.
 *		   ITERATES THROUGH WORD VIA WHILE LOOP TO REMOVE ANY NON ALPHABETACL CHARACTERS 
 *		   NOT FOUND IN 'FINAL STRING LETTERS'.
 *
 *PRECONDITION: N/A
 *
 *POSTCONDITION: THE ARGUMENT WILL BE LOWERCASE AND CONTAIN ONLY LETTERS.
 *
 *PARAMETER: A COPY OF THE WORD INPUTED BY THE USER.
 *
 *RETURNS: CONSTRUCTER, BUT CALLS SETWORD TO DIRECTLY CHANGE WORD INSTANCE VARIABLE 
 *		   TO THE UPDATED TEMP METHOD ARGUMENT.
 */
	
	public WordHelper(String temp) {
		int place = 0;// place-holder for iterating 'temp'
		temp=temp.toLowerCase();
		while (place < temp.length()) {
			if (!letters.contains(temp.charAt(place) + "")) {
				String left = temp.substring(0, place); 
				if (place + 1 < temp.length()) {
					String right = temp.substring(place + 1);
					temp = left + right; //GETS RID OF UNDESIRED CHARACTERS
				}
				else{ temp=temp.substring(0,temp.length()-1);
				
			} 
			}
				else {
				place++;//SHIFTS TO NEXT LETTER
			}

		}
		setWord(temp);

	}
//SETTER METHOD
	public void setWord(String temp) {
		word = temp;
	}
//GETTER METHOD
	public String getWord() {
		return word;
	}
	
/*METHOD: NUMBEROFSYLLABLES()
 * 
 *PURPOSE: TO CALCUTE THE NUMBER OF SYLLABLES IN THE WORD INPUT BY THE USER.
 *
 *PRECONDITION: THE WORD IS PROPERLY FORMATTED BY WORDHELPER. THE USER SHOULD ALSO BE AWARE
 *				THAT SYLLABLES ARE CALCULATED ROUGHLY AND NOT ALWAYS ACCORDING TO DICTONARY
 *				RULES.
 *
 *POSTCONDITION: RETURNS THE THE AMOUNT OF SYLLABLES IN THE WORD. THE WORD IS NEVER CHANGED
 *				 IN THE METHOD.
 *
 *RETURNS: THE AMOUNT OF SYLLABLES IN THE WORD.
 */
	public int numberOfSyllables() {
		int place = 0; //PLACEHOLDER FOR ITERATION
		int count = 0; //SYLLABLE COUNTER

		while (place < word.length()) {
			if (word.charAt(place) == 'a' || word.charAt(place) == 'e'
					|| word.charAt(place) == 'i' || word.charAt(place) == 'o'
					|| word.charAt(place) == 'u' || word.charAt(place) == 'y') {
				count++;
			}// while1-if
			place++;
		}// while1

		place = 0; //RESETS

		while (place < word.length() - 1) {
			if (place + 1 < word.length()) {
				if ((word.charAt(place) == 'a' || word.charAt(place) == 'e'
						|| word.charAt(place) == 'i'
						|| word.charAt(place) == 'o'
						|| word.charAt(place) == 'u' || word.charAt(place) == 'y')
						&& (word.charAt(place + 1) == 'a'
								|| word.charAt(place + 1) == 'e'
								|| word.charAt(place + 1) == 'i'
								|| word.charAt(place + 1) == 'o'
								|| word.charAt(place + 1) == 'u' || word
								.charAt(place + 1) == 'y')) {

					count--;
				}
			}
			// while 2-if
			place++;
		}// while2
		if (word.charAt(word.length() - 1) == 'e') { //'E' AT THE END
			count--;
		}
		if (count == -1)
			return 1;

		return count;
	}//method
/*METHOD: SYLLABLIZE
 * 
 *PURPOSE: TO ITERATE AND RECOGNIZE THE SYLLABLES OF THE USER INPUT AND PROPPERLY FORMAT
 *		   THE WORD WITH '/' INBETWEEN SYLLABLES ACCORDING THE 'VCCV' AND 'VCV' PROTOCAL
 *		   OUTLINED BY THE ASSIGNMENT.
 *
 *PRECONDITION: THE WORD IS PROPPERLY FORMATED.
 *
 *POSTCONDITION: THE SYLLABLIZATION IS IN VCV AND VCCV LOGIC.
 *
 *RETURNS: A SYLLABLE DIVIDED VERSION OF THE WORD.
 */
	public String syllablize() {
		String temp = word; //TEMP FOR USER INPUT
		int place = 0; //PLACE HOLDER
		while (place < temp.length()) {//
			if (vowls.contains(word.charAt(place) + "")
					&& word.charAt(place) != '/') {
				if (place + 1 < word.length()
						&& place + 2 < word.length()
						&& place + 3 < word.length()
						&& consonants.contains(word.charAt(place + 1) + "") // if
																			// vccv
																			// pattern
						&& consonants.contains(word.charAt(place + 2) + "")
						&& vowls.contains(word.charAt(place + 3) + "")) {
					String left = word.substring(0, place + 2);
					String right = word.substring(place + 2, word.length());
					word = left + "/" + right;

				}

				if (place + 1 < word.length()
						&& place + 2 < word.length() // if vcv pattern
						&& consonants.contains(word.charAt(place + 1) + "")
						&& vowls.contains(word.charAt(place + 2) + "")) {
					String left = word.substring(0, place + 1);
					String right = word.substring(place + 1, word.length());
					word = left + "/" + right;
				}
				place++;
			}// if first
			else {
				place++;
			}

		}// while

		return word;

	}// method
	
/*METHOD NAME:PRONOUNCE
 * 
 *PURPOSE: USING TRANSLATESYLLABLE, AND AND SYLLABLIZE PHONETIC AND SYLLABIZED 
 *		   VERSION OF THE WORD.
 *
 *PRECONDITION: TRANSLATESYLLABLE AND SYLLABLIZE WORK PROPPERLY.
 *
 *POSTCONDITION:N/A
 *
 *PARAMETER:N/A
 *
 *RETURNS: RETURNS A PHONETIC VERSION OF THE SYLLABIZED VERSION OF THE WORD
 */
	public String pronounce() {
		String temp = this.syllablize();
		String helper= new String();
		int trailSlash=-1;
		int rightSlash=0;
		if(temp.contains('/'+"")){
		for(int i=0; i< temp.length(); i++){
			if(temp.charAt(i)=='/'){
			rightSlash=i;
			helper+=""+ translateSyllable(temp.substring(trailSlash+1, rightSlash)) + "-";
			trailSlash=i;
			}
		
		}
		
		}
		
		helper+=""+ translateSyllable(temp.substring(rightSlash +1)) + "";
		return helper;
		
		/*String temp = this.translateSyllable(word);
		temp = this.syllablize();
		return temp;*/
	}// method: Pronounce

/*METHOD NAME: PRONOUCE
 *
 *PURPOSE: PHONETICLY TRANSLATE THE SYLLABLE ARGUMENT FOR PRONUNCIATION USING
 *		   STRING CLASS REPLACE, ENDSWITH, AND SUBSTRING.
 *
 *PRECONDITION: N/A
 *
 *POSTCONDITION: DOES NOT REPLACE ALL SOUNDS, ONLY ONES OUTLINED BY ASSIGNMENT
 *
 *PARAMETERS: SYL: SYLLABLE TO BE PHONETICALY PRONOUNCED.
 *
 *RETURNS: RETURNS A PHONETICALLY PRONOUNCED VERSION OF THE SYLLABLE.
 */
	private String translateSyllable(String syl) {
		if (syl.endsWith('o' + ""))
			syl = syl.substring(0, syl.length() - 1) + "oh";
		if (syl.endsWith('i' + ""))
			syl = syl.substring(0, syl.length() - 1) + "ee";
		if (syl.endsWith('a' + ""))
			syl = syl.substring(0, syl.length() - 1) + "ay";
		if (syl.endsWith("ce"))
			syl = syl.substring(0, syl.length() - 2) + "ss";
		if (syl.endsWith("es"))
			syl = syl.substring(0, syl.length() - 2) + "ez";
		if (syl.endsWith("vy"))
			syl = syl.substring(0, syl.length() - 2) + "vee";
		while(syl.contains("cc")|| syl.contains("ca") || syl.contains("co")||
				syl.contains("au") || syl.contains("ea") || syl.contains("qu")){
		syl.replace("cc", "k");
		syl.replace("ca", "ka");
		syl.replace("co", "ko");
		syl.replace("au", "aw");
		syl.replace("ea", "ee");
		syl.replace("qu", "kw");
		}
		return syl;

	}
/*ASSIGNMENT: PROGRAM #3: WORDHELPER
 *AUTHOR: SPENCER KLINGE
 *
 *SECTION LEADER:LIZZIE
 *
 *COURSE: CSC227
 *
 *INSTRUCTOR:L. MCCANN
 *
 *DUE: FEB 11TH
 *
 *DESCRIPTION: A PROGRAM THAT TAKES AN INPUT WORD BY THE THE USER, AND FORMATS IT TO 
 *			   PRESENT ITS DIVIDED SYLLABLES AND PHONETIC PRONUNCIATION.
 *
 *DEFICIENCIES: 
 */
	public static void main(String args[]) {
		String mainWord;//COPY OF WORD FOR MAIN
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Please enter word:");
		mainWord = keyboard.nextLine();
		WordHelper box = new WordHelper(mainWord);//WORDHELPER OBJECT
		System.out.println("syllables:" + box.numberOfSyllables());
		System.out.println("\nSyllabized:" + box.syllablize());
		System.out.println("");
		System.out.print("pronunciation:"+box.pronounce());

	}// main
}// class
