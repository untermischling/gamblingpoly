import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Databank{
	public static void main(String[] args){
               		}
	
	public static void databbl(double inputBalance){
	 try(FileWriter writer = new FileWriter("databank.txt")){
		 	String content = Double.toString(inputBalance);
			writer.write(content);
		}
		catch(IOException e){
			System.out.println("Exception found in try-catch block");
		
		}
	}
}
