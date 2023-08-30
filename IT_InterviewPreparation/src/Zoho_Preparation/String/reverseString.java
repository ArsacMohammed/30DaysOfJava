package Zoho_Preparation.String;
import java.util.*;
public class reverseString {

	public static void main(String[] args) {
	/*	String str="hello world";
		StringBuilder s = new StringBuilder();
		s.append(str);
		s.reverse();	
		System.out.println(s);
		*/
		
		
		String str="Helo world";
		String reverse=reverseString(str);
		System.out.println(reverse);
		
	}
	public static String reverseString (String str) {
		StringBuilder s = new StringBuilder();
		for (int i=str.length()-1;i>=0;i--) {
			s.append(str.charAt(i));
			
			
		}
		return s.toString();
	}

}
