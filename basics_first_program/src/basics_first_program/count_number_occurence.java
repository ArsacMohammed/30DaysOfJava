package basics_first_program;

import java.util.*;
public class count_number_occurence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter a number to count the digit");
		Scanner in =new Scanner(System.in);
		int number =in.nextInt();
		System.out.println("Enter the digit to find the frequency");
		int d =in.nextInt();
		int result=countDigitOccurence(number,d);
		System.out.println("The count of the digit is :  "+ result);
	
	}

	private static int countDigitOccurence(int number,int d) {
		// TODO Auto-generated method stub
		int sum=0;
		if (number<0) {
			number =-number;
		}
		while (number!=0) {
			int digit =number%10;
			if (digit==d) {
				sum++;
			}
			number=number/10;
			
		}
		return sum;
	}

}
