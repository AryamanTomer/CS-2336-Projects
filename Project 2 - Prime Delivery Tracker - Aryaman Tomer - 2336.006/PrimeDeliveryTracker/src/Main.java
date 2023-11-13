//Aryaman Tomer axt 210052
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Math;
//This class represents the main format for 
public class Main {
	private static final DecimalFormat d = new DecimalFormat("0");
	public static void main (String[] args) throws IOException{
		Scanner Input = new Scanner(System.in);
		LinkedList<Driver> DriverList = new LinkedList<Driver>();
		//This is a boolean to determine if the file exists and opens properly	
		boolean fileFound = false;
		//Opening the Driver File and Putting the Data into a linked list
		while(fileFound == false) {
			fileFound = OpeningDriverFile(fileFound, DriverList, Input);
		}
		//Setting the file to false before opening the next file
		fileFound = false; 
			fileFound = commandFileInput(fileFound, DriverList, Input);
		//Closing the Input to avoid any memory leaks
		Input.close();
	}
	//This opens the file and enters in the data into the linked list.
	public static <E> boolean OpeningDriverFile (boolean lp, LinkedList<Driver> DList, Scanner input) throws IOException {
		try{
		//Entering the Input File which is driver.txt
			System.out.println("Enter the file name:");
		    String name = input.nextLine();
			try (//Creating a new Buffered Reader to read the file contents
			BufferedReader file = new BufferedReader(new FileReader(name))) {
				//Creating a Loop until all the file lines are read
				for(String line = file.readLine(); line != null; line = file.readLine()){
					//Splitting the line to check data
					String sp[] = line.split(" ");
					//Checking the integrity of the data if it is true, then the data is inserted
					if(dataCheck(sp) == true){
						//The parsingData returns a Driver which is inserted into the Linked List
						Driver tempDriver = parsingData(sp);
						DList.insert(tempDriver);
						
					}
					else{
						//It there isn't a properly processed string, then it does not read properly.
						System.out.println("We cannot process the string at: " + Arrays.toString(sp));
					}
				}
			
			}
			System.out.println(DList.toString(DList));
			//Returning true it the file was successfully read
			return true;
			//If there is an exception and the file wasn't read correctly, then it returns false because there is a FileNotFoundException
		} catch(FileNotFoundException e){
			System.out.println("The file was unfortunately not found");
			return false;
		}
	}
	//Taking a string which is a split line
	public static <E> boolean dataCheck(String c[]){
		String driverName = c[0];
		//If the drivers name doesn't have any of these characters, then it is not a valid name to use
		if(!driverName.matches("[a-zA-Z0-9\\-'\\s]+")){
			System.out.println("The driver name " + driverName + " is unfortunately not valid");
			return false;
		}
		if (c.length < 2) {
			//A valid line should have at least a name and one coordinate pair
			return false;
		}
		int i = 0;
		//Parsing the Cooordinate pair
		Pattern p = Pattern.compile("-?\\d,\\s?-?\\d");
		System.out.println(c.length);
		for(int j = 1; j < c.length; j++){
			Matcher m = p.matcher(c[j]);
			boolean t = m.find();
			if(t == true){
				break;
			}
			else{
				i++;
			}
		}
		for(int j = 0; j < i; j++){
			//Detecting for matching chars in order to determine that other characters are not validated
			p = Pattern.compile("[^a-zA-Z0-9\\-']");
			Matcher m = p.matcher(c[i]);
			boolean match = m.find();
			if(match == false){
				System.out.println(c[j] + " is not valid as it contains unacceptable characters");
				return false;
			}
		}
		//Comparing the first and last vertex in order to confirm the numbers
		String f = c[1];
		String l = c[c.length - 1];
		p = Pattern.compile(f);
		Matcher m = p.matcher(l);
		boolean match = m.find();
		if(match == false){
			System.out.println("The First Vertex is not the same as the Last Vertex");
			return false;
		}
		//Finding the validity of a set of coordinates
		for(int j = 1; j < c.length; j++){
			p = Pattern.compile("-?\\d+(\\.\\d+)?,-?\\d+(\\.\\d+)?");
			m = p.matcher(c[j]);
			match = m.find();
			if(match == false){
				System.out.println("The invalid set of coordinate is currently at: " + c[j]);
				return false;
			}
			
		}
		return true;
	}
	//Parsing the Data so that it can return the proper Driver and implement it into the linked list.
	public static Driver parsingData(String a[]) {
		double area = 0.0;
		String driverName = a[0];
		//Going through the loop to count the values and add up the vertices to calculate the area with the formula
		//0.5(n-2SUMMATIONi = 0 (x(i + 1) + x(i)) (y(i + 1) + y(i)))
		for (int i = 1; i < a.length; i++) {
			String[] coordinates = a[i].split(",");
			//parsing the coordinates and then summing them up.
			if (coordinates.length == 2) {
				double x = Double.parseDouble(coordinates[0]);
				double y = Double.parseDouble(coordinates[1]);
				if (i > 1) {
					//x(i + 1) + x(i) * y(i + 1) + y(i)
					double xPrev = Double.parseDouble(a[i - 1].split(",")[0]);
					double yPrev = Double.parseDouble(a[i - 1].split(",")[1]);
					area += (xPrev + x) * (yPrev - y);
				}
			}
		}
		 area = Math.abs(area / 2);
		return new Driver(driverName, area);
	}
	
	//Getting the input from the console to accecss the command file
	public static boolean commandFileInput(boolean lp, LinkedList<Driver> DList, Scanner Input) throws IOException{
		System.out.println("Since there is a Driver file, what is the command file name (full format): ");
		BufferedReader commandFile;
		String fileName = "";
		if(Input.hasNextLine())
			fileName = Input.nextLine();
		try{
			//Accessing the command file and reading through all the lines
			commandFile = new BufferedReader(new FileReader(fileName));
			for(String line = commandFile.readLine(); line != null; line = commandFile.readLine()){
				String sp[] = line.split(" ");
				if(commandCheck(sp) == true){
					parsingCommands(sp, DList);
				}
				else{
					System.out.println("The command data is not working at:" + Arrays.toString(sp));
				}
			}
			//Closing the file after everything was read
			Input.close();
			commandFile.close();
			return true;
		} catch (FileNotFoundException e){
			System.out.println("The file you were searching for is unavailable");
			return false;
		}
	}
	//Checking in the command file for any faulty characters
	public static boolean commandCheck(String c[]){
		if(c[0].equals("sort")){
			//The pattern was first the type of command, then getting either the area or the name, the sorting it by ascendance or descendance
			String line = c[0] + " " + c[1] + " " + c[2];
			Pattern p = Pattern.compile("sort area|name asc|des");
			Matcher m = p.matcher(line);
			boolean match = m.find();
			if(match == true){
				return true;
			}
			else{
				return false;
			}
		}
		//Finding anything else other than sort
		else if(c.length != 3 && c[0].equals("sort") == false){
			String line = c[0];
			//Compiling a pattern to find a command pattern
			Pattern p = Pattern.compile(".*[0-9].*[.].*[\\d].*");
			Matcher m = p.matcher(line);
			boolean match = m.find();
			if(match){
				return true;
			}
			//Finding any characters that might be illegal in this program
			else{
				p = Pattern.compile(".*[\\-][\\d].*");
				m = p.matcher(line);
				match = m.find();
				if(match == true){
					System.out.println("This line contains a character that isn't allowed: " + line);
					return false;
				}
				line = "";
				if(c.length == 1){
					line = c[0];
				}
				else if(c.length == 2){
					line = c[0] + " " + c[1];
				}
				else{
					for(int j = 0;j < c.length; j++){
						line += c[j] + " ";
					}
					line += c[c.length];
				}
				match = m.find();
				p = Pattern.compile(".*[^[a-z][A-Z][0-9]\\-\\'\\s].*");
				m = p.matcher(line);
				match = m.find();
				if(match == true){
					System.out.println("This line contains a char character that isn't allowed: " + line);
					return false;
				}
				else{
					return true;
				}
			}
		}
		else{
			return false;
		}
	}
	//processing the commands in the text
	public static <E> void parsingCommands(String c[], LinkedList<Driver> DList){
		//Sorting commands
		if(c[0].equals("sort")){
			//Sorting the areas by either ascending or descending
			if(c[1].equals("area")){
				if(c[2].equals("asc")){
					DList.sort(1);
					System.out.println("Area in Ascending Order:");
					System.out.println(DList.toString(DList));
				}
				else if(c[2].equals("des")){
					DList.sort(1);
					System.out.println("Area in Descending Order:");
					System.out.println(DList.toStringReverse(DList));
				}
			}
			//Sorting the names by ascending or descending
			else if(c[1].equals("name")){
				if(c[2].equals("asc")){
					DList.sort(2);
					System.out.println("List of Names in Ascending Order:");
					System.out.println(DList.toString(DList));

				}
				else if(c[2].equals("des")){
					DList.sort(2);
					System.out.println("List of Names in Descending Order:");
					System.out.println(DList.toString(DList));
				}
			}
		}
		else{
			//Finding a pattern to find if a coordinate set is available in the Linked List
			Pattern p = Pattern.compile(".*[\\d].*[.].*[\\d]");
			Matcher m = p.matcher(c[0]);
			boolean match = m.find();
			if(match == true){
				try {
					double t = Double.parseDouble(c[0]);
					int s = (int)t;
					boolean r = DList.searchInt(s);
					if(r == true){
					System.out.println("We have " + t + " available in the Linked List");
				}
				else{
					System.out.println("The following element: " + t + " was not found in the LinkedList");
				}
					// ...
				} catch (NumberFormatException e) {
					System.out.println("Invalid double value: " + c[0]);
					//Handle the error as needed (e.g., ask the user to provide valid input)
				}
				
			}
			//Finding if additional values are found in the command file
			else{
				String testing = "";
				if(c.length == 1){
					testing = c[0];
				}
				else if(c.length == 2){
					testing = c[0] + " " + c[1];
				}
				else{
					for(int j = 0; j < c.length; j++){
						testing += c[1] + " ";
					}
					testing += c[c.length];
				}
				//Finding if there is a string that is in the LinkedList to verify its integrity
				boolean res = DList.searchString(testing);
				if(res == true){
					System.out.println("");
				}
				else{
					System.out.println(testing + " not found");
				}
			}
		}
	}
	
	
}
