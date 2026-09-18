import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

//disbl stands for displaying balance
//nir stands for narrowed floatin result
//wnir stands for working narrowed int result
//lfr stands for left floating result
public class Slotmachine{


	private static Scanner scanner =  new Scanner(System.in);
	private static Random random = new Random();

	public static void main(String[] args){
	
	boolean musicOptions = false;
	boolean isRunning = true;
	boolean reward = false;
	int clipUse = 0;
	double balance = 0;

	String filePath = "/home/ackermanzawaudo/Music/bensound.wav";
	File file = new File(filePath);
	Clip clip = null;
	try(BufferedReader reader = new BufferedReader(new FileReader("databank.txt"))){
	balance = Double.parseDouble(reader.readLine());
	}
	catch(IOException | NumberFormatException e){
	e.printStackTrace();
	}
	try(AudioInputStream audioStream = AudioSystem.getAudioInputStream(file)){
		clip = AudioSystem.getClip();
		clip.open(audioStream);
		clipUse = 1;
	}	
	catch(FileNotFoundException e){
		System.out.println("couldn't locate file :/");
	}
	catch(LineUnavailableException e){
		System.out.println("couldn't find resource :/");
	}
	catch(UnsupportedAudioFileException e){
		System.out.println("bad file extension or encoding :/");
	}
	catch(IOException e){
		System.out.println("couldn't proced, IOException has been catch :/");
	}
	int nir = (int) balance;
	double lfr = balance - nir;


	System.out.println("**************************");
	System.out.println("gamblingworldいらつしやませ!:D");
	System.out.println("**************************");
		while(isRunning){
		System.out.print("Press :m to toggle music. :g to gamble, :s to show balance or :q to quite: ");
		String query = scanner.nextLine().toLowerCase();

		if(query.equals("dandelion") && !reward){
		reward = true;
		balance += 100;
		System.out.println("Congratulations! 100  bucks have been conveyed to your account.");
		}
		else{
		query = query.substring(0, 2);
		}

	switch(query){

			case ":m"-> {musicOptions = true;
				     musicPanel(clip, file, clipUse);
				}
			case ":g" -> { int wnir = (int) balance; 
				       balance = slotmachine(wnir, lfr);
				}
			case ":s" -> {System.out.printf("Your balance is: %.2f円%n", balance + lfr);
				}
			case ":q" -> {isRunning = false;
				}
			case "dandelion" -> {break;
				}
			default -> {System.out.println("invalid operation");
			}
		}
		}

		balance += lfr;
		try(FileWriter writer = new FileWriter("databank.txt")){
		String content = Double.toString(balance);
		writer.write(content);
		}catch(IOException e){
			 System.out.println("Exception found in try-catch block that writes databank.txt");

		}
		
		fechar();
	}
	public static int musicPanel(Clip clip, File file, int clipUse){
	boolean musicPanelLoop = true;	

		while(musicPanelLoop){
		System.out.print("Press :h to go home, :pm to play  music, :rm to restart music, :sm to stop music or :qm to quite: ");
		String musicQuery = scanner.nextLine();
	switch(musicQuery){
		case ":h" -> musicPanelLoop = false;
		case ":pm" -> clip.start();
		case ":rm" -> clip.setMicrosecondPosition(0);
		case ":sm" -> clip.stop();
		case ":qm" -> {clip.close();
			       clip = null;
			       musicPanelLoop = false;
			       clipUse = switchZero();
			       return clipUse;

			}
		 default -> {System.out.println("Invalid operation");}
		};
