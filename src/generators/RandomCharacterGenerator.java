package generators;

import java.util.Random;

/**
 * Class generates a random character between a-zA-Z0-9.
 * @author ajay
 *
 */
public class RandomCharacterGenerator {
	Random rand;
	String charString;
	int charStringLen;
	public RandomCharacterGenerator(){
		rand = new Random();
		charString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqurstuvxyz0123456789";
		charStringLen = charString.length();
	}
	/**
	 * Generates a radnom number between range 0-len(charString)
	 * @return Character from charString index specified by random number.
	 */
	public char generateChar(){
		int index = rand.nextInt(charStringLen);
		return charString.charAt(index);
		
	}
}
