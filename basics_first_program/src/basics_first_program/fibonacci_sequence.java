package basics_first_program;
import java.util.*;
public class fibonacci_sequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter a number : ");
		Scanner in =new Scanner(System.in);
		int n =in.nextInt();
		if(n<0) {
			System.out.println("negative numbers are not allowed .");
			return ;
		}
		if (n==0) {
			System.out.println("The first number of fibonacci sequence is"+ " "+n);
		}
		else if (n==1){
			System.out.println("the second number in the fibanacci sequence is "+ " "+n);
		}else {
		int sum=0;
		int firstNumber =0;
		int secondNumber=1;
		System.out.println(firstNumber);
		System.out.println(secondNumber);
		for (int i=0;i<n-2;i++) {

			sum =firstNumber+secondNumber;
			System.out.println(sum);
			firstNumber=secondNumber;
			secondNumber=sum;
			}
		}

	}

}
