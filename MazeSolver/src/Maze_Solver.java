import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Maze_Solver {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		ArrayList<Maze> mazes =readArray();
				
		
		int i =0;
		while (i<mazes.size()) {
		 if (solveMaze(mazes.get(i))) {
			 System.out.println("you won");
		 }else {
			 System.out.println("no path");
		 	}
		 i++;
		 }
		
	}
	 private static ArrayList<Maze> readArray() throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(new File ("src/Mazes.txt"));
		ArrayList<Maze> mazes=new ArrayList<Maze>();
		while (in.hasNext()) {
			
			Maze m =new Maze();

			int rows = Integer.parseInt(in.nextLine());
			m.maze= new int[rows][];
			for (int i=0;i<rows;i++){
				String line =in.nextLine();
				m.maze[i] = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray(); 
				
			}
			m.start=new Positions (Integer.parseInt(in.nextLine()),Integer.parseInt(in.nextLine()));
			mazes.add(m);
			in.nextLine();
			
		
		}
		in.close();
		return mazes;
	}
	private static boolean solveMaze(Maze m) {
	// TODO Auto-generated method stub
		 Positions p = m.start;
		 m.path.push(p);
 		 
		 while(true) {
		 int y =m.path.peek().y;
		 int x= m.path.peek().x;
		 m.maze[y][x]=0;
		 //down movement
		 if (isValid(y+1,x,m)) {
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
		 if(isValid(y,x-1,m)) {
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
		 if (isValid(y-1,x,m)) {
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
		 if(isValid(y,x+1,m)) {
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
	public static boolean isValid(int y,int x,Maze m) {
		 if (y<0 || y>=m.maze.length || x<0 || x>=m.maze[y].length) {
			 return false ;
		 }
		 return true;
		 
	 }
/// if spaces is used to separate the data in txt file rather than symbol then u gotta be careful might be some changes make a note of it
}
