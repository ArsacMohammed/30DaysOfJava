
public class countFrequencyOfElement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] num = {1,1,2,3,3,4,2,1,4,5,6,4,3,5,3,2,4,3};
		int n= num.length;
		System.out.println(n);
		countFreq(num,n);	
		}

	public static void countFreq(int [] num,int n) {
		
		// TODO Auto-generated method stub
		boolean [] visited = new boolean[n];
		for (int i=0;i<num.length;i++) {
			// check for if already visited.
			if (visited[i]==true) {
				continue;
			}
			int count=1;
			// check for frequency.
			for (int j=i+1;j<n;j++) {
				if (num[i]==num[j]) {
					visited[j]=true;
					count++;
				}
				
			}
			
			System.out.println(num[i]+" is repeated "+count+" times");
			
		}
		
	}

}
