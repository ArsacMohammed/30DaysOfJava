package basics_first_program;
import java.util.ArrayList;
public class generic_basics {

	public static void main(String[] args) {
//		Pair<String,Integer> pair =new Pair<String,Integer>();
//		pair.setX("caleb");
//		pair.setY(10);
//		Pair<String,Integer> pair1 =new Pair<String,Integer>();
//		pair1.setX("caleb");
//		pair1.setY(10);
//		
//		ArrayList<Pair<String,Integer>> pears = new ArrayList<Pair<String,Integer>>();
//		pears.add(pair1);
//		pears.add(pair);
		
		ArrayList<Person> people =new ArrayList<Person>();
		Admin a =new Admin();
		people.add(a);
		
		ArrayList<Admin> admins = new ArrayList<Admin>();

		doSomething(admins);
	}

	private static void doSomething(ArrayList<Admin> a) {
		System.out.println(a);
		
	}

//	private static void doSomething(Person p) {
//		System.out.println(p);
//		
//	}

}
