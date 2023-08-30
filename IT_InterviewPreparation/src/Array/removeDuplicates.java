package Array;
import java.util.*;

public class removeDuplicates {
// this contains code using hashset and without using hashmap.
	public static void main(String[] args) {
		/*
		// TODO Auto-generated method stub
		int [] numbers = {7,4,3,21,1,2,9,4,5,5,6,6,7,8,8,4,6,9,2,1};
		HashSet<Integer>unique= new HashSet<Integer>();
		
		for (int n : numbers) {
			if (!unique.contains(n)) {
				unique.add(n);
			}
		}
		int[] newArray= new int[unique.size()];
		int index=0;
		for(int value: unique) {
			newArray[index]=value;
			index++;
		}
		Arrays.sort(newArray);
		 for (int i=0;i<unique.size();i++) {
			 System.out.print(newArray[i]+" ");
		 }
		 
		 */
		
		
		//without using hashset
		
		int [] numbers = {7,4,3,21,1,2,9,4,5,5,6,6,7,8,8,4,6,9,2,1};
		Arrays.sort(numbers);
		int i=0;
		for(int j=0;j<numbers.length;j++) {
			if (numbers[j]!=numbers[i]) {
				i++;   /// i++ have to be here
				numbers[i]=numbers[j];
				
			}
		}
		System.out.println(i);
		for (int k=0;k<i+1;k++) {
			System.out.print(numbers[k]+" ");
			
		}
		
	}

}
