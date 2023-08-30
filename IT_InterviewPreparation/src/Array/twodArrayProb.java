package Array;

public class twodArrayProb {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m=5;
		int n=5;
		int k=0;
		char[][] arr =new char[m][n];
		String input="WELCOMETOZOHOCORPORATION";
		for (int i=0;i<m;i++) {
			for (int j=0;j<n;j++) {
				if (k < input.length()) {
					arr[i][j]=input.charAt(k);
					k++;
				}
				
			}
		}
		for (int i=0;i<m;i++) {
			for (int j=0;j<n;j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		
	}

}

/*
 * 3. Save the string “WELCOMETOZOHOCORPORATION” in a two dimensional array and search for substring like “too” in the two dimensional string both from left to right and from top to bottom.

w	e	L	C	O
M	E	T	O	Z
O	H	O	C	O
R	P	O	R	A
T	I	O	n	  

And print the start and ending index as

Start index : <1,2>

End index: <3, 2>
*/
