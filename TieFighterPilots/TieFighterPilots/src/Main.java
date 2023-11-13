//Aryaman Tomer axt210052
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.lang.Math;
public class Main {
	public static void main(String[] args) throws IOException {
		//Setting up variables and declaring them
		//The max number of pilots is 20
		Scanner inputFile = null;
		//Pilot variable
		String pilot[]=new String[20];
		//3-D Array to calculate area
		double  verticies[][][]=new double[20][16][2];
		//Coordinates Array to calculate position
		int coords[]=new int[16];
		//Setting the Reader to null by default
		BufferedReader reader = null;
		boolean fileFound = true;
		long lines = 0;
		try {
			//Inputting the file and finding the number of lines
			System.out.println("Enter the file name:");
			try (//Creating a new Scanner variable
			Scanner kb = new Scanner(System.in)) {
				//Initializing the filename variable that contains the filename
				String filename = kb.nextLine();
				//Obtaining the file Path
				Path path = Paths.get(filename);
				//Creating a File variable to check file validation
				File file = new File(filename);
				inputFile = new Scanner(file);
				reader = new BufferedReader(new FileReader(file));
				//finding the number of lines
				try {
					lines = Files.lines(path).count();
					//Throwing an exception if the lines aren't counted properly
				} catch(IOException e) {
					e.printStackTrace();
				}
		//Throwing an exception when the file is not found
			}
		} catch (FileNotFoundException e) {
			System.out.println("The file is currently not found");
			fileFound = false;
			//Catching an Input/Output  Exception
		} finally {
	    	//Only if the file is found I will continue
			if(fileFound) {
				System.out.println("The file has been read");
				//Creating a new string to check all the lines
				String line = reader.readLine();
				int count = 0;
				//Looping through the Input File
				while(line != null) {
					//Getting the name of the pilot from the input file
					String s[]=getString(inputFile.nextLine());
					//Storing the values into an array and defining each coordinate
					storeArray(s, pilot, verticies, coords, count);
					//Moving on to the next line in the file
					line = reader.readLine();
					count++;
				}
				//Closing the Input file
				reader.close();
				//Opening the Output file
				FileWriter myWriter = new FileWriter("pilot_areas.txt");
				//Looping through every line of the output file
				for(int i = 0; i < count; i++) {
					//Obtaining the pilot name
					String pilot_name=pilot[i];
					//Calculating and obtaining the area
					double area=calculateArea(verticies, coords, i);
					//Putting the pilot name and area in one string to output )
					String text = pilot_name+"\t"+String.format("%.2f", area) + "\n";
					
					//Putting the string into the output file
					myWriter.write(text);
				}
				//Closing the Output File
				myWriter.close();

			}
		}
	}
	//Returning the String Array and getting rid of the commas and spaces
	public static String[] getString(String s)

	{

		return s.split("[,\\s]");
	}
	//Calculates the Area
	static double calculateArea(double vertex[][][], int coords[], int count) {
		//Initializing the Area
		double area = 0;
		//Summation of the Area
		for(int i = 1; i < coords[count]; i++) {
			//Inner Formula
			area+=((vertex[i][count][0]+vertex[i-1][count][0])*(vertex[i][count][1]-vertex[i-1][count][1]));
		}
		//Outer Formula
		//Applying the absolute value of the area and dividing it by 2
		area = (double)Math.abs(area)/2;
		//Returning the Area
		return area;
	}
	static void storeArray(String ar[], String pilot[], double vertex[][][], int coords[], int count) {
		//Initializing the pilot to the pilot name
		pilot[count] = ar[0];
		//Initializing the row to count each individual coordinate later on 
		int row = 0;
		//Looping through and Counting each value to each individual coordinate
		for(int i = 1; i < ar.length; i +=2) {
			vertex[row][count][0]=Double.parseDouble(ar[i]);
			vertex[row][count][1]=Double.parseDouble(ar[i+1]);
			row++;
		}
		//Setting the coordinates to each row
		coords[count]=row;
	}
}
