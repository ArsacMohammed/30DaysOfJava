//refer youtube link --https://youtu.be/XYQecbcd6_c
package Zoho_Preparation.String;

//Program to find longest Plaindromic substring
// this program focus on expand from center
public class longestPalindromeSubstring {
	public static void main(String[] args) {
		 String s = "forgeeksskeegfor";
		 String result=longestPalindrome(s);
		 System.out.println(result);
	}
    static int maxLen = 0;
    static int lo = 0;
    public static String longestPalindrome(String s) {
        char[] input = s.toCharArray();
        if(s.length() < 2) {
            return s;
        }
        
        for(int i = 0; i<input.length; i++) {
        	//for odd length in the string 
            expandPalindrome(input, i, i);
            //for even length in the substring
            expandPalindrome(input, i, i+1);
        }
        return s.substring(lo, lo+maxLen);
    }
    
    public static  void expandPalindrome(char[] s, int l, int r) {
        while(l >= 0 && r < s.length && s[l] == s[r]) {
            l--;
            r++;
        }
        if(maxLen < r - l - 1) {
            maxLen = r - l - 1;
            lo = l+1;
        }
    }
}