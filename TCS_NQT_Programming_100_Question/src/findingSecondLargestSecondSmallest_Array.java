
public class findingSecondLargestSecondSmallest_Array {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] num= {1,3,2,4,5,3,6,7,4,7};
		int len=num.length;
		if (num.length<2) {
			return;
		}
		int foundSecondMax=findMax(num,len);
		int foundSecondMin = findMin(num,len);
		System.out.println("the second min is : "+foundSecondMin);
		System.err.println("the second max is : "+foundSecondMax);
	}
	
	private static int  findMin(int[] num,int len) {

		int secondMinimum=Integer.MAX_VALUE;
		int minimum=Integer.MAX_VALUE;
		for (int i=0;i<len;i++) {
			if (num[i]<minimum) {
				secondMinimum=minimum;
				minimum=num[i];				
			}else if (num[i]<secondMinimum && num[i]!=minimum){
				secondMinimum=num[i];
			}
			
		}
		return secondMinimum;
	}

	private static int  findMax(int[] num,int len) {
		int secondMaximum=Integer.MIN_VALUE;
		int maximum=Integer.MIN_VALUE;
		for (int i=0;i<len;i++) {
			if (num[i]>maximum) {
				secondMaximum=maximum;
				maximum=num[i];				
			}else if (num[i]>secondMaximum && num[i]!=maximum){
				secondMaximum=num[i];
			}
			
		}
		return secondMaximum;
	}

}
