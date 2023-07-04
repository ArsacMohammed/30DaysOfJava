import java.util.Arrays;

public class checkforAnagramwithoutHashMap {
	static String input="hello";
	static String input1 = "olleh";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (checkAnagram(input,input1)) {
			System.out.println("the given string is an anagram");
			
		}else {
			System.out.println("The given string is not an anagram");
		}
		
		
	}
	public static boolean checkAnagram(String input,String input1) {
		if (input.length()!=input1.length()) {
			return false;
		}
		char[] charArray1=input.toCharArray();
		char[] charArray2=input1.toCharArray();
		Arrays.sort(charArray1);
		Arrays.sort(charArray2);
		return Arrays.equals(charArray1, charArray2);
	}
}
