import java.util.LinkedList;
public class Maze_Solver {
	static int [][] maze = {
			{1,0,1,1},
			{1,1,2,0},
			{0,0,1,0}
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

		 if (maze[y+1][x]==2) {
			 System.out.println("moved down.you won ");
			 return;
		 }else if(maze[y+1][x]==1){  //check and move down.
			 path.push(new Positions(y+1,x));
			 System.out.println("moved down" );
			 continue;
//new position updated.
		 	}
		 //left
		 if (maze[y][x-1]==2) {
			 System.out.println("moved left .you won ");
			 return;
		 }else if(maze[y][x-1]==1){  //check and move down.
		
			 path.push(new Positions(y,x-1));
			 System.out.println("moved left.");
			 continue;
//new position updated.
		 	}
//moved up
		 if (maze[y-1][x]==2) {
			 System.out.println("moved up.you won ");
			 return;
		 }else if(maze[y-1][x]==1){  //check and move down.
			 path.push(new Positions(y-1,x));
			 System.out.println("moved up");

			 continue;
//new position updated.
		 	}
//moved right
		 if (maze[y][x+1]==2) {
			 System.out.println("moved right .you won ");
			 return;
		 }else if(maze[y][x+1]==1){  //check and move down.

			 path.push(new Positions(y,x+1));
			 System.out.println("moved right");

			 continue;
//new position updated.
		 	}
		 
		 }

	}

}
