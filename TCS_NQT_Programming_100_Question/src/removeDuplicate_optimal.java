import java.util.Arrays;

public class removeDuplicate_optimal{
	public static void main(String[] args) {
		int[] numbers = {1,2,2,3,4,5,6,4};
		Arrays.sort(numbers);
		int number=removeDuplicate(numbers);
		
        for (int i = 0; i < number; i++) {
            System.out.print(numbers[i] + " ");
        }
		
		
	}

	public static int removeDuplicate(int[] numbers) {
		// TODO Auto-generated method stub
		if (numbers.length==0) {
			System.out.println("No duplicates");
			return 0;
		}
		
		
		int i=0;
		for (int j=1;j<numbers.length;j++) {
			if (numbers[j]!=numbers[i]) {
				i++;
				numbers[i]=numbers[j];
			}
		}
		return i+1;
	}
}