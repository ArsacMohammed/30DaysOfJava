package basics_first_program;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException; 
public class my_sweetprogram {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner in = new Scanner (new File ("src\\basics_first_program\\bros.txt"));
		List<String> bros=new ArrayList<String>();
		while (in.hasNext()){
			bros.add(in.nextLine());
		}
		for ( String bro:bros) {
			System.out.println("name: "+bro);
		}
		
		/*
		System.out.println("give us a size");
		int size =in.nextInt();

		int [] grades= new int [5];
		System.out.println("enter "+size +" number one after the another");
		for (int i=0;i<size;i++) {
			grades[i]=in.nextInt();
			}
		for (int i=0;i<size;i++) {
			System.out.print(grades[i]+" ");
			
		}
		*/
	}

}