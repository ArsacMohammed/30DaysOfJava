package basics_first_program;
import java.util.ArrayList;
import java.util.List;
public class generic_basics {

	public static void main(String[] args) {
		Admin a =new Admin();
		a.email="arsac@gmail.com";
		a.ln="arsac";

		ArrayList<Admin> admins = new ArrayList<Admin>();
		admins.add(a);
		List<Person> people =(List<Person>)(List<?>) admins;
		doSomething(people);
	}

	private static void doSomething(List<Person> peeps) {
		for (Person p : peeps ) {
			System.out.println(p.ln+" "+p.email);
		}
		
		
	}

}
/* if a manage class is inherited from the employee class then they have a relationship but list of employee and list of manager has no relationship at all */