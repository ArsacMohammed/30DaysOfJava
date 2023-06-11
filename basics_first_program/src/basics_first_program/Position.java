package basics_first_program;

import java.util.Objects;

public class Position {
	public int x;
	public int y;
	public int z;
	Position(int x,int y,int z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	@Override
	public int hashCode() {
		return Objects.hash(x,y,z);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}
	
}
