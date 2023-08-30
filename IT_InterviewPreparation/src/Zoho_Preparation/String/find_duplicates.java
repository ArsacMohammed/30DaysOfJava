package Zoho_Preparation.String;
import java.util.*;
// this contains remove dupplicates in a string with and without using hashmap .
public class find_duplicates {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input = "Hello world";
		String removedupString=  removeDuplicate(input);
		System.out.println(removedupString);
	}

	private static String removeDuplicate(String input) {
		// TODO Auto-generated method stub
		boolean [] seen = new boolean[256];
		StringBuilder result = new StringBuilder();
		for (char c : input.toCharArray()) {
			if (!seen[c]) {
				seen[c]=true;
				result.append(c);
			}
		}
		return result.toString();
	}

	/*private static String removeDuplicate(String input) {
		// TODO Auto-generated method stub
		HashMap<Character,Integer>charcount=new HashMap<Character,Integer>();
		StringBuilder result = new StringBuilder();
		for (char c :input.toCharArray()) {
			if (!charcount.containsKey(c)){
				charcount.put(c,1);
				result.append(c);
			}
		}
		return result.toString();
	} */

}
