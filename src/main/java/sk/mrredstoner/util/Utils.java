package sk.mrredstoner.util;

public class Utils {

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
}
