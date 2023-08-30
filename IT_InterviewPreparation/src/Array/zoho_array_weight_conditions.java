package Array;

public class zoho_array_weight_conditions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] weight= {10,36,89,54,12};
		for (int i =0;i<weight.length;i++) {
			if (isSquare(weight[i])) {
				System.out.println(weight[i]+" "+"5");
			}else if (isMul(weight[i])) {
				System.out.println(weight[i]+" "+"4");
			}
			else if( isEven(weight[i])) {
				System.out.println(weight[i]+" "+"3");
			}
			else {
				System.out.println(weight[i]+" "+"0");
			}
		}

	}

	private static boolean isEven(int i) {
		// TODO Auto-generated method stub
		if (i%2!=0) {
			return false;
		}
		return true;
	}

	private static boolean isMul(int i) {
		// TODO Auto-generated method stub
		if (i%4==0 & i%6==0) {
			return true;
		}
		return false;
	}

	private static boolean isSquare(int i) {
		// TODO Auto-generated method stub
		int number=(int)Math.sqrt(i);
		if(number*number==i) {
			return true;
		}
		return false;
	}

}


/*
/* Given a set of numbers like <10,36,54,89,12> we want to find sum of weights based on the following conditions
1. 5 if a perfect square
2. 4 if multiple of 4 and divisible by 6
3. 3 if even number

And sort the numbers based on the weight and print it as follows

<10,its_weight>,<36,its weight><89,its weight>
*/