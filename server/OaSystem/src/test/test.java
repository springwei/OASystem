package test;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class test {
	public static void main(String[] args) {
		PrintWriter pw=null;
		String name="lqs";
		int age=123;
		float score=12.2f;
		try {
			
		pw=new PrintWriter(new FileWriter(new File("e:\\file.txt")),true);
		pw.printf("%s,%d,%f",name,age,score);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("111");
	}

}
