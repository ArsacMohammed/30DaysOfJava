package basics_first_program;
import java.util.HashMap;
import java.util.Set;
public class hash_basics {

	public static void main(String[] args) {
		HashMap<String,Integer> ids = new HashMap<String ,Integer>();
		ids.put("Sally",98);
		ids.put("sussan", 87);

		
		Set<String> keys = ids.keySet();
		for (String key:keys) {
			System.out.println(key);
			System.out.println(ids.get(key));
		}
	}

}
