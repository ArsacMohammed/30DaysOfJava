package basics_first_program;

public class my_sweetprogram {
public static void main(String[] args) {
	int [][] data ={
		{1,2,3,4},
		{1,2,5,7},
		{2,3,8,6}

	};
	for (int i=0;i<data.length;i++) {
		for (int j=0;j<data[i].length;j++) {
			System.out.print(data[i][j]+" ");
			}
		System.out.println();
		}
	
	}
}