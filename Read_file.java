package dsa; 
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class readFile {

	public ArrayList<List<String>> readFile1(String csvFile){ //function to read csv data and store it as an arraylist of list
		final String delimiter = ","; //separates elements separated by a ,
		ArrayList<List<String>> adjCourseList = new ArrayList<>();
		String[] tempArr; //array that stores elements in each line
		try {
	         File file = new File(csvFile); //creating an object of the file that we want to read
	         FileReader fr = new FileReader(file); 
	         BufferedReader br = new BufferedReader(fr); 
	         String line = "";
	         while((line = br.readLine()) != null) {
	        	 tempArr = line.split(delimiter);
	         adjCourseList.add( Arrays.asList(tempArr));
	         }
	        adjCourseList.remove(0); 
	      
	        br.close();
	         } catch(IOException ioe) {
	            ioe.printStackTrace();
	     }
		return adjCourseList;
		}
		
}