import static org.junit.Assert.*;

import org.junit.Test;


public class Prog3 {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	 public static void main(String [] args)
     {
                     /* instantiating new WordHelper object with the
                      * String "A-p-o-l-l-o-n"
                      */

             //Testing constructor
             System.out.println("Creating new WordHelper with String " +
                                "\"A-p-o-l-l-o-n\"");
             WordHelper x = new WordHelper("A-p-o-l-l-o-n");


                     /*testing syllable/pronouncation methods*/

             //Testing getWord()
             System.out.println("getWord() should return: apollon" +
                                "\ngot: " + x.getWord());
             //Testing numberOfSyllables()
             System.out.println("numberOfSyllables() should return 3" +
                                "\ngot: " + x.numberOfSyllables());
             //Testing syllablize()
             System.out.println("syllablize() should return: a/pol/lon" +
                                "\ngot: " + x.syllablize());
             //Testing pronounce()
             System.out.println("pronounce() should return: ay-pol-lon" +
                                "\ngot: " + x.pronounce());

                     /* setting state variable to "instantiable" */

             //Testing setWord()
             System.out.println("Setting word to instantiable");
             x.setWord("instantiable");
             System.out.println("getWord() should return: instantiable" +
                                "\ngot: " + x.getWord());

                     /*testing syllable/pronouncation methods*/

             //Testing numberOfSyllables()
             System.out.println("numberOfSyllables() should return 3" +
                                "\ngot: " + x.numberOfSyllables());
             //Testing syllablize()
             System.out.println("syllablize() should return: instan/tiab/le" +
                                "\ngot: " + x.syllablize());
             //Testing pronounce()
             System.out.println("pronounce() should return: instan-tiab-le" +
                                "\ngot: " + x.pronounce());

                     /* setting state variable to "caves" */

             //Testing setWord()
             System.out.println("Setting word to caves");
             x.setWord("caves");
             System.out.println("getWord() should return: caves" +
                                "\ngot: " + x.getWord());

                     /*testing syllable/pronouncation methods*/

             //Testing numberOfSyllables()
             System.out.println("numberOfSyllables() should return 2" +
                                "\ngot: " + x.numberOfSyllables());
             //Testing syllablize()
             System.out.println("syllablize() should return: ca/ves" +
                                "\ngot: " + x.syllablize());
             //Testing pronounce()
             System.out.println("pronounce() should return: kay-vez" +
                                "\ngot: " + x.pronounce());
     } //end main

}
