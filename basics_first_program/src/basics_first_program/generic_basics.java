package basics_first_program;

public class generic_basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Item <String> items =new Item <String>();
		items.getX ();
		items.setX("caleb");
		System.out.println(items.getX());
	}

}
