package Array.subsetRealtedProb;
public class allSubsetArray {
    public static void main(String[] args) {
        String input = "WELCOMETOZOHOCORPORATION";
        int rows = 5;
        int cols = 5;

        char[][] array = new char[rows][cols];

        int index = 0; // Used to traverse the input string

        // Filling the array row by row
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (index < input.length()) {
                    array[i][j] = input.charAt(index);
                    index++;
                }
            }
        }

        // Searching for the given subset
        String targetSubset = "TOO";
        int targetLength = targetSubset.length();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j + targetLength <= cols) {
                    StringBuilder rowSubsetBuilder = new StringBuilder();
                    for (int k = j; k < j + targetLength; k++) {
                        rowSubsetBuilder.append(array[i][k]);
                    }
//                    System.out.println(rowSubsetBuilder.toString());
                    if (rowSubsetBuilder.toString().equals(targetSubset)) {
                        System.out.println("Row Subset found at (" + i + "," + j + ") to (" + i + "," + (j + targetLength - 1) + ")");
                    }
                }
                
                if (i + targetLength <= rows) {
                    StringBuilder colSubsetBuilder = new StringBuilder();
                    for (int k = i; k < i + targetLength; k++) {
                        colSubsetBuilder.append(array[k][j]);
                    }
//                    System.out.println(colSubsetBuilder.toString());
                    if (colSubsetBuilder.toString().equals(targetSubset)) {
                        System.out.println("Column Subset found at (" + i + "," + j + ") to (" + (i + targetLength - 1) + "," + j + ")");
                    }
                }
            }
        }
    }
}


/*
3. Save the string “WELCOMETOZOHOCORPORATION” in a two dimensional array and search for substring like “too” in the two dimensional string both from left to right and from top to bottom.

w	e	L	C	O
M	E	T	O	Z
O	H	O	C	O
R	P	O	R	A
T	I	O	n	  

And print the start and ending index as

Start index : <1,2>

End index: <3, 2>
*/