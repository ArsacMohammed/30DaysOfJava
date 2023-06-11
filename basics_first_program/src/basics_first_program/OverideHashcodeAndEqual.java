package basics_first_program;
import java.util.HashSet;
import java.util.HashMap;

public class OverideHashcodeAndEqual {
	public static void main(String[] args) {
		HashSet<Person> people =new HashSet<Person>();
		Person p =new Person("arsac@gmail.com","caleb");
		Position pos1=new Position(10,10,20);
		p.position =pos1;
		Person q =new Person("arsac@gmail.com","caleb");
		Position pos2 =new Position (10,10,20);
		q.position=pos2;
		people.add(p);
		people.add(q);
		
		HashMap<String,Person> peeps =new HashMap<String,Person>();
		peeps.put("arsac@gmail.com",p);
		peeps.put("arsac@gmail.com",q);
		System.out.println("hashmap: "+(peeps.containsKey("arsac@gmail.com")));
		
		
		
		
		
//		names.add("rusthum");
//		names.add("kite");
//		names.add("mickel");
//		names.add("adam");
//		names.add("abdul");
//		for (String name:names) {
//			System.out.println(name);
//		}
//		

 

	}
}
