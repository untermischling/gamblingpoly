import java.util.Locale;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//Learn code with bro : xTtlL8E4LzTQ



public class Bank {
	static Scanner scanner = new Scanner(System.in);
	static {
		scanner.useLocale(Locale.US);
	}

		public static void main(String[] args) {
			double balance = 0;

			try(BufferedReader reader = new BufferedReader(new FileReader("databank.txt"))){
				balance = Double.parseDouble(reader.readLine());			
			}catch(IOException | NumberFormatException e){
				e.printStackTrace();
			}

			boolean isRunning = true;
			String choice;
			String query = null;

				System.out.println("**************");
				System.out.println("BAKING PROGRAM銀行");
				System.out.println("**************");
				System.out.println("\":s.\" Show Balance");
				System.out.println("\":d.\" Deposit");
				System.out.println("\":w.\" Withdraw");
				System.out.println("\":q.\" Exit出口");
				System.out.println("**************");

			while(isRunning){

				System.out.print("Enter your choice (\":s\"-\":q\"): ");

				choice = scanner.nextLine().toLowerCase();

				if(choice.length() >= 2){
				 query = choice.substring(0, 2);
				} else{
				System.out.println("To short短ぃ");
				}
				switch(query) {
					case ":s" -> showBalance(balance);
					case ":d" -> balance += deposit(balance);
				        case ":w" -> balance -= withdraw(balance);
					case ":q" -> isRunning = exit();
					default -> System.out.println("INVALID CHOICE >:( 無効");
					}
				}
			scanner.close();

			}
	static void showBalance(double balance){
		System.out.println("**************");
		System.out.printf("Your balance is: $%.2f%n", balance);
		System.out.println("**************");


	}
	static double deposit(double balance){

			double depositAm;

			System.out.print("Enter the amount to be deposited: ");
			depositAm = scanner.nextDouble();
			scanner.nextLine();

			if(depositAm < 0){
				System.out.print("Amount can't be negative>:(負\n");
				return 0;
			}
			else{
			        double newBalance = balance + depositAm;
				System.out.printf("Your current balance is %.2f, %.2f have been added to %.2f%n", newBalance, depositAm, balance);
				Databank databank = new Databank();
				databank.databbl(newBalance);

				return depositAm;
			}
	}
	static double withdraw(double balance){

		double withdrawAm;
		System.out.print("Enter an amount to be withdraw: ");
		withdrawAm = scanner.nextDouble();
		scanner.nextLine();

		if(withdrawAm > balance){
			System.out.print("INSUFFICIENT FUNDS:(\n");
			return 0;
		}
		else if(withdrawAm < 0){
			System.out.print("Amount can't be negative >:(負\n");
			return 0;
		}
		else{
			double newBalance = balance - withdrawAm;
			System.out.printf("Your current balance is %.2f, %.2f have been removed from %.2f%n", newBalance, withdrawAm, balance);
			return withdrawAm;
		}
	}
	static boolean exit(){
		System.out.println("Goodbye!さようなら");
	return false;
	}
}
