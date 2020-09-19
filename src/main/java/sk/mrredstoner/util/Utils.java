package sk.mrredstoner.util;

import java.util.Random;

public class Utils {
	private static final Random rnd = new Random();

	/**calculates the number of cows for a given card
	 * @param card the number of the card
	 * @return the number of cows on the card
	 * */
	public static int cows(int card) {
		int out=1;
		if(card % 5 == 0){
			out++;
		}
		if(card % 10 == 0){
			out++;
		}
		if(card % 11 == 0){
			out += 4;
			if(card % 5 == 0) out++; //C-c-c-combo
		}
		return out;
	}

	/**in-place Fisher-Yates shuffle
	 * @param array the array to shuffle
	 * @return the original array, shuffled
	 * */
	public static <T> T[] shuffle(T[] array){
		for (int i = array.length-1; i > 0; i--) {
			int j = rnd.nextInt(i+1); 

			T temp = array[i]; 
			array[i] = array[j]; 
			array[j] = temp; 
		} 
		return array;
	}
	
	public static void doAssert(boolean condition, String message){
		if(!condition)throw new RuntimeException(message);
	}
}
