/**
 * 
 */
package mypackage.src;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Program to reverse the contents of file(s)
 * @author Pradeep Roy
 *
 */
public class FileReversal {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		//Check the arguments
		if(args.length!=0){
			FileReversal fs = new FileReversal();
			for ( int i = 0; i < args.length; i++) {
				//Reverse the contents by pushing line by line using InputStreamReader on stack and then reverse during pop operation
				fs.doFileReverse(args[i]); 

				//Read the complete file in memory and reverse using StringBuilder
				//fs.doFileReverseOneLoop(args[i]); 
			}
		}
		else
			System.out.println("Please provide the file name(s) to reverse (Usage: FileReversal <fileName1> <fileName2> ..)");
	}


	/**
	 * Method      :  doFileReverse																 
	 * Input       :  String (path of the file whose contents need to be reversed)				 
	 * Description :  This method reads the file specified in the input line by line 			 
	 * 				  and pushes each line into the stack.										 
	 * 				  Once all the lines are read and pushed into the stack, the lines are 		 
	 *				  popped out of the stack and each line is then reversed					 
	 */

	public void doFileReverse(String path) throws IOException{
		FileInputStream inputStream = null;
		Scanner myscanner = null;
		try {
			Stack<String> lines = new Stack<String>(); //Create a Stack of Strings
			inputStream = new FileInputStream(path); 
			myscanner = new Scanner(inputStream, "UTF-8");
			while (myscanner.hasNextLine()) { //read file line by line
				String line = myscanner.nextLine(); 
				lines.push(line); //push the lines on stack
			}
			while(! lines.empty()) {
				String reverse = new StringBuilder(lines.pop()).reverse().toString(); //pop the String of lines and reverse using StringBuilder
				System.out.println(reverse); //print the reverse lines
			}

		} catch (FileNotFoundException e)
		{
			System.out.println("File: "+ path + " not found. Please neter a valid path/file name");
		}
		finally {
			if (inputStream != null) {
				try {
					inputStream.close(); //close the InputStream
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(); //Print exceptions StackTrace
				}
			}
			if (myscanner != null) {
				myscanner.close(); //Close the Scanner
			}
		}

	}

	/**
	 * Method      :  doFileReverseOneLoop																 
	 * Input       :  String (path of the file whose contents need to be reversed)				 
	 * Description :  This method reads the file specified in buffer
	 * 				  and reverse it using StringBuilder.					 
	 */


	public void doFileReverseOneLoop(String path) {
		System.out.println("Doing file reversal in one operation");

		try {
			Scanner myscanner = new Scanner(new File(path));
			String line = myscanner.useDelimiter("\\Z").next(); //Read the contents of file till end
			String reverseContent = new StringBuilder(line).reverse().toString(); //Reverse the contents 
			System.out.println(reverseContent); //Print the reverse contents
			myscanner.close();
		} catch (FileNotFoundException e)
		{
			System.out.println("File: "+ path + " not found. Please neter a valid path/file name");
		}
		finally {
		
		}
	}
}
