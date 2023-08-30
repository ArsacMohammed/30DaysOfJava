package Zoho_Preparation.String;

public class palindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="river";
		String original = str;
		StringBuilder str1=new StringBuilder();
		for(int i=str.length()-1;i>=0;i--) {
			str1.append(str.charAt(i));
		}
		String result = str1.toString();
		System.out.println(result);
		if(original.equals(result)) {
			System.out.println("palindrome"); 
		}else {
			System.out.println("not ");
		}
	}

}
