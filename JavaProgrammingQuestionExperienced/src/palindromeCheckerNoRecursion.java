
public class palindromeCheckerNoRecursion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input ="2 Race, e*'&^%$!@# cAr 2";
		String mInput=input.replaceAll("[\\p{Punct}\\s]","");
		System.out.println(mInput);
		int i =0;
		int j=mInput.length()-1;
		while(i<j) {
			if (mInput.toLowerCase().charAt(i)==mInput.toLowerCase().charAt(j)) {
				i++;
				j--;
				continue;
			}else {
			System.out.println("not palindrome");
			return;
			}
		}
		System.out.println("the given string is a plaindrome");
		

	}
}
