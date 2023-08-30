package Array;

public class sum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] numbers = {1,2,3,4,3,5,3,2,7,8,9,7,6};
		int sum_=0;
		for (int i=0;i<numbers.length;i++) {
			sum_+=numbers[i];
		}
		System.out.println(sum_);
	}

}
