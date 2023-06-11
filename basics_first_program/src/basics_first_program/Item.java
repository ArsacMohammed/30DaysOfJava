package basics_first_program;
//custom type generic example 
//generic gives compile time error but without generic the program gives runtime error which is bad ,debugging is hard.
public class Item <T> {
	private T x;
	T getX() {
		return x;
	}
	void setX(T x) {
		this.x=x;
	}
	
}
