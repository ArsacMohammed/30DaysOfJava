package basics_first_program;

import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.util.ListIterator;
import java.io.FileNotFoundException; 


public class my_sweetprogram {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<String> names =new LinkedList<String>();
		names.push("caelb");
		names.push("kate");
		names.push("nice");
    	
		ListIterator <String> it =names.listIterator();
		it.next();
		it.next();
		it.add("susan");

		
//		while (it.hasNext()) {
//			System.out.println(it.next());
//		}
		for (String s : names) {
			System.out.println(s);
		}

//		System.out.println(it.next());
	}

}