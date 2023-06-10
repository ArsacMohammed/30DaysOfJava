import java.util.LinkedList;
public class Maze_Solver {
	static Maze m=new Maze();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [][] maze = {
				{2,1,1,1},
				{0,0,1,1},
				{0,0,0,1}
				};
		m.maze=maze;
		m.start = new Positions(0,3);
		m.path=new LinkedList<Positions>();
		 
		 if (solveMaze(new Positions (0,3))) {
			 System.out.println("you won");
		 }else {
			 System.out.println("no path");
		 }




	}
	 private static boolean solveMaze(Positions p) {
	// TODO Auto-generated method stub
		 m.path.push(p);
 		 
		 while(true) {
		 int y =m.path.peek().y;
		 int x= m.path.peek().x;
		 m.maze[y][x]=0;
		 //down movement
		 if (isValid(y+1,x)) {
		 if (m.maze[y+1][x]==2) {
			 System.out.println("moved down. ");
			 return true;
		 }else if(m.maze[y+1][x]==1){  //check and move down.
			 m.path.push(new Positions(y+1,x));
			//new position updated.
			 System.out.println("moved down" );
			 continue;

		 	}
		 }
		 

		 //left
		 if(isValid(y,x-1)) {
		 if (m.maze[y][x-1]==2) {
			 System.out.println("moved left .");
			 return true;
		 }else if(m.maze[y][x-1]==1){  //check and move down.
		
			 m.path.push(new Positions(y,x-1));
			 System.out.println("moved left.");
			//new position updated.
			 continue;

		 	}
		 }

//moved up
		 if (isValid(y-1,x)) {
		 if (m.maze[y-1][x]==2) {
			 System.out.println("moved up.");
			 return true;
		 }else if(m.maze[y-1][x]==1){  //check and move down.
			 m.path.push(new Positions(y-1,x));
			 System.out.println("moved up");
				//new position updated.
			 continue;

		 	}
		 }
//moved right
		 if(isValid(y,x+1)) {
		 if (m.maze[y][x+1]==2) {
			 System.out.println("moved right . ");
			 return true;
		 }else if(m.maze[y][x+1]==1){  //check and move down.

			 m.path.push(new Positions(y,x+1));
			 System.out.println("moved right");
			 //new position updated.
			 continue;

		 	}
		 }
		 m.path.pop();
		 System.out.println("Moved Back");
		 if (m.path.size()<=0) {
			return false;
		 	}
		 }
	 }
	public static boolean isValid(int y,int x) {
		 if (y<0 || y>=m.maze.length || x<0 || x>=m.maze[y].length) {
			 return false ;
		 }
		 return true;
		 
	 }
/// no matter how many rows and column you  add its gonna do it anyway ........
}
