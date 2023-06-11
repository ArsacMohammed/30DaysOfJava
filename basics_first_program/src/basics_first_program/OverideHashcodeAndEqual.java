package basics_first_program;

public class OverideHashcodeAndEqual {
	public static void main(String[] args) {
	Person p =new Person("arsac@gmail.com","caleb");
	Position pos1=new Position(10,10,20);
	p.position =pos1;
	Person q =new Person("arsac@gmail.com","caleb");
	Position pos2 =new Position (10,10,20);
	q.position=pos2;
	System.out.println(p.hashCode());
	System.out.println(q.hashCode());
//	System.out.println(p.equals(q));
//	System.out.println(p.hashCode());
//	System.out.println(q.hashCode());
//	
//	Object q1="jelly";
//	Object p1="jelly";
//	System.out.println(p.equals(q));
//	System.out.println(p1.hashCode());
//	System.out.println(q1.hashCode());
	}
}
