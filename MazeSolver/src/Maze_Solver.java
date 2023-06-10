import java.util.LinkedList;
public class Maze_Solver {
	static int [][] maze = {
			{2,1,1,1},
			{0,0,1,1},
			{0,0,0,1}
			};

	static LinkedList<Positions> path =new LinkedList<Positions>();
// y=row
// x=column
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Positions p =new Positions (0,3);
// initial position ,push to stack
		 path.push(p);
		 
//y=0,x=3
		 
		 
		 
/* program for maze with out of bound exception and in the next commit we are going to deal with it */
		 while(true) {
		 int y =path.peek().y;
		 int x= path.peek().x;
		 maze[y][x]=0;
		 //down movement
		 if (isValid(y+1,x)) {
		 if (maze[y+1][x]==2) {
			 System.out.println("moved down.you won ");
			 return;
		 }else if(maze[y+1][x]==1){  //check and move down.
			 path.push(new Positions(y+1,x));
			//new position updated.
			 System.out.println("moved down" );
			 continue;

		 	}
		 }
		 

		 //left
		 if(isValid(y,x-1)) {
		 if (maze[y][x-1]==2) {
			 System.out.println("moved left .you won ");
			 return;
		 }else if(maze[y][x-1]==1){  //check and move down.
		
			 path.push(new Positions(y,x-1));
			 System.out.println("moved left.");
			//new position updated.
			 continue;

		 	}
		 }

//moved up
		 if (isValid(y-1,x)) {
		 if (maze[y-1][x]==2) {
			 System.out.println("moved up.you won ");
			 return;
		 }else if(maze[y-1][x]==1){  //check and move down.
			 path.push(new Positions(y-1,x));
			 System.out.println("moved up");
				//new position updated.
			 continue;

		 	}
		 }
//moved right
		 if(isValid(y,x+1)) {
		 if (maze[y][x+1]==2) {
			 System.out.println("moved right .you won ");
			 return;
		 }else if(maze[y][x+1]==1){  //check and move down.

			 path.push(new Positions(y,x+1));
			 System.out.println("moved right");
			 //new position updated.
			 continue;

		 	}
		 }
		 path.pop();
		 System.out.println("Moved Back");
		 if (path.size()<=0) {
			 System.out.println("No Path");
			
		 }
		 
		 }


	}
	 public static boolean isValid(int y,int x) {
		 if (y<0 || y>=maze.length || x<0 || x>=maze[y].length) {
			 return false ;
		 }
		 return true;
		 
	 }
/// no matter how many rows and column you  add its gonna do it anyway ........
}
