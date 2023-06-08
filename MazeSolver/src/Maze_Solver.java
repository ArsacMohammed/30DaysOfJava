import java.util.LinkedList;
public class Maze_Solver {
	static int [][] maze = {
			{2,0,1,1},
			{1,1,0,1},
			{0,0,1,0}
			};
 // 0 is wall
// 1 is path
// 2 is destination
//x is column
//y is row
	static LinkedList<Positions> path =new LinkedList<Positions>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Positions p =new Positions (3,0);
// position is a constructor used to invoke the method asap the constructor is called 
		 path.push(p);
// the reason the push is used instead of the add method because push is used for stack rather than queue.
		 
// in solving the maze ,the last position visited has to be stored in the collection ie., linked list and and recent position has to be peeked for reference.
		 
//that why we are using stack , if recent is not desirable than we use pop method to remove and check other possible way		 
		 maze[path.peek().y][path.peek().x]=0;
//peek is used to get the recent element or in other words retrieve the head of the list without removing it .
		 
		 for (int i=0;i<maze.length;i++) {
			 for (int j=0;j<maze[i].length;j++) {
				 System.out.print(maze[i][j]+ " ");
			 }
			 System.out.println(" ");
//this shows that "maze[path.peek().y][path.peek().x]=0;" is worked and the passed position element becomes zero and gets updated in the maze (defined as static )
		 }
	}

}
