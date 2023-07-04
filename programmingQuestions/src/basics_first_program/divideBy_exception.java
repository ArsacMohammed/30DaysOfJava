package basics_first_program;
import java.util.*;
// exception handling .
public class divideBy_exception {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=5;
		try {
			int c = a/5;
			System.out.println(c);
		}catch(ArithmeticException e) {
			System.out.println("cannot divide by zero ");
		}finally {
		System.out.println("program ended");
		}

	}

}
