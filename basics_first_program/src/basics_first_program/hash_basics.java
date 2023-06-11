package basics_first_program;
import java.util.HashMap;

import java.util.Set;
public class hash_basics {

	public static void main(String[] args) {
	String  p = new String ("email@email.com");
	String q =new String ("email@email.com");		
	System.out.println(p.equals(q));
	
// if two object is same when the equal method is called then that two object will have the same hashCode.
		/*
		HashMap<String,Integer> ids = new HashMap<String ,Integer>();
		ids.put("Sally",98);
		ids.put("sussan", 87);
		Set<String> keys = ids.keySet();
			for (String key:keys) {
			System.out.println(key);
			System.out.println(ids.get(key));
		}
*/		
		
		
		
	}

}
