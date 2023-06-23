package basics_first_program;
import java.util.*;
public class digit_count {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter a number to count the digit");
		Scanner in =new Scanner(System.in);
		int number =in.nextInt();
		int result=countDigit(number);
		System.out.println("The count of the digit is :  "+ result);
	
	}

	private static int countDigit(int number) {
		// TODO Auto-generated method stub
		int sum=0;
		while (number!=0) {
			int digit =number%10;
			number=number/10;
			sum++;
			
		}
		return sum;
	}

}
