package basics_first_program;
//custom type generic example 
//generic gives compile time error but without generic the program gives runtime error which is bad ,debugging is hard.
public class Pair <T,U> {
	private T x;
	private U y;
	T getX() {
		return x;
	}
	void setX(T x) {
		this.x=x;
	}
	U getY() {
		return y;
	}
	void setY(U y) {
		this.y=y;
	}
}
