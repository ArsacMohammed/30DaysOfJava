package Array;

public class twodArrayProb {
    public static void main(String[] args) {
        // Define the input string and the dimensions of the array
        String input = "WELCOMETOZOHOCORPORATION";
        int rows = 5;
        int cols = 5;

        // Create a 2D array to store the characters from the input string
        char[][] array = new char[rows][cols];

        // Initialize an index to traverse the input string
        int index = 0;

        // Fill the 2D array row by row with characters from the input string
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (index < input.length()) {
                    array[i][j] = input.charAt(index);
                    index++;
                }
            }
        }

        // Define the target subset you want to search for
        String targetSubset = "TOO";
        int targetLength = targetSubset.length();

        // Iterate through the 2D array to search for the target subset
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Check horizontally (row subset)
                if (j + targetLength <= cols) {
                    StringBuilder rowSubsetBuilder = new StringBuilder();
                    for (int k = j; k < j + targetLength; k++) {
                        rowSubsetBuilder.append(array[i][k]);
                    }
                    if (rowSubsetBuilder.toString().equals(targetSubset)) {
                        System.out.println("Row Subset found at (" + i + "," + j + ") to (" + i + "," + (j + targetLength - 1) + ")");
                    }
                }

                // Check vertically (column subset)
                if (i + targetLength <= rows) {
                    StringBuilder colSubsetBuilder = new StringBuilder();
                    for (int k = i; k < i + targetLength; k++) {
                        colSubsetBuilder.append(array[k][j]);
                    }
                    if (colSubsetBuilder.toString().equals(targetSubset)) {
                        System.out.println("Column Subset found at (" + i + "," + j + ") to (" + (i + targetLength - 1) + "," + j + ")");
                    }
                }
            }
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
