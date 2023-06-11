package basics_first_program;
import java.util.ArrayList;
public class generic_basics {

	public static void main(String[] args) {
		ArrayList<Person> people = new ArrayList<Person>();
		Admin a =new Admin();
		people.add(a);
		ArrayList<Admin> admins = new ArrayList<Admin>();
		doSomething(admins);
	}

	private static void doSomething(ArrayList<? extends Person> peeps) {
		//? extends Person means any arraylist type that extends Person class is acceptable.
		System.out.println(peeps);
		
	}

}
/* if a manage class is inherited from the employee class then they have a relationship but list of employee and list of manager has no relationship at all */