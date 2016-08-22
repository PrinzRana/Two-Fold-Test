import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RealFold {
	
	static int numberOfDataItems = 5620;
	static int halfnumberOfDataItems = 2810;
	static int numberOfDataCat = 65; 
	static String dataFile1 = "cw2DataSet1.csv";
	static String dataFile2 = "cw2DataSet2.csv";
	
private static int data [][] = new int[numberOfDataItems][numberOfDataCat]; 
//stores the integer in a array
public static void processLine( String line, int offset){
	int firstcomma;
	int secondcomma = -1;
	
	//converts the line and prints out the integers
	for (int value = 0; value < numberOfDataCat; value++){
		String digitString;
		firstcomma = secondcomma;
		//finds the next comma in the line
		secondcomma = line.indexOf(',',firstcomma+1);
		
		if(value < 64)
			//finds the string between the first and second comma
			digitString = line.substring(firstcomma+1,secondcomma);
		else
			digitString = line.substring(firstcomma+1,line.length());
		int digit = Integer.parseInt(digitString);

		//System.out.print(digit + " ");
		data [offset][value] = digit;
	}
}
		// finds out the distance between the integers 
	private static int euclideandistance (int firstTest, int secondTest){
		int totaldistance = 0;
		for (int vectorL=0; vectorL<64; vectorL++){
			int distance = data[firstTest][vectorL] - data[secondTest][vectorL];
			distance = distance * distance; // Squares the distance
			totaldistance = totaldistance + distance;
		} //totaldistance += distance;
		return totaldistance;
	}

	private static boolean categorize(int categorizeItem, int firstTrain, int lastTrain){
		int nearest = 0;
		int lowest = Integer.MAX_VALUE;
		//max_value holds the maximum value an int can have
		
		for(int testItem =firstTrain; testItem < lastTrain; testItem++){
			int min= euclideandistance(categorizeItem,testItem);
			if(min<=lowest){
				lowest=min;
				nearest=testItem;
			}
		}
		if(data[nearest][64] == data[categorizeItem][64])
			{
				return true;}
			else {
				return false;}
	}   	
			//calculates the percentage of the first data set
    	private static void FirstFoldPercentage(){
    		int counter=0;
    		for(int testelement=0; testelement<halfnumberOfDataItems; testelement++){
    			boolean correct = categorize(testelement, halfnumberOfDataItems, numberOfDataItems);
    			if(correct)
    				counter++;
    		} //double wraps a value of the primitive type double in an object
    		    double percentage1= counter*100.0/halfnumberOfDataItems;
    			System.out.println("FirstHalf Data Percentage	:	" + percentage1);
    		}
    	
    	// calculates the percentage of the second data set
    	private static void SecondFoldPercentage(){
    		int counter=0;
    		for(int testelement=halfnumberOfDataItems; testelement<numberOfDataItems; testelement++){
    			boolean correct = categorize(testelement, 0, halfnumberOfDataItems);
    			if(correct)
    				counter++;
    		}
    		    double percentage2= counter*100.0/halfnumberOfDataItems;
    			System.out.println("SecondHalf Data Percentage	:	"  + percentage2);
    		}
    	// calculates the  average percentage of both data sets
    	private static void FullPercentage(){
    		int counter=0;
    		for(int testelement=0; testelement<halfnumberOfDataItems; testelement++){
    			boolean correct = categorize(testelement, halfnumberOfDataItems, numberOfDataItems);
    			if(correct)
    				counter++;
    		} //double wraps a value of the primitive type double in an object
    		    double percentage1= counter*100.0/halfnumberOfDataItems;
    		
    		int counter1=0;
    		for(int testelement=halfnumberOfDataItems; testelement<numberOfDataItems; testelement++){
    			boolean correct = categorize(testelement, 0, halfnumberOfDataItems);
    			if(correct)
    				counter1++;
    		}
    		    double percentage2= counter1*100.0/halfnumberOfDataItems;
    		
    		int counter2=0;
    		for(int testelement=1; testelement<numberOfDataItems; testelement++){
    			boolean correct = categorize(testelement, 0, numberOfDataItems);
    			if(correct)
    				counter2++;
    		} 
    		
    		double complete = percentage1 + percentage2;
    		// first and the second half were added together and divided by 2
			double averagepercentage = complete/2;
			// to find out the average percentage, 
			
    			System.out.println("Full Data Percentage	:		" + averagepercentage );
    				
    		}
    	
    	//function readData
    	public static void readData(int fileNumber)  {

    		String fileName = dataFile1;// reads data set 1
    	    int lineNumber = 0;
    	    if(fileNumber==2)
    	    { fileName = dataFile2;// reads data set 2
    	    lineNumber=halfnumberOfDataItems;
    	    }
    	    String line = null;

    	    try {
    	    	// FileReader reads text files in the default encoding.
    	        FileReader fileReader = 
    	            new FileReader(fileName);

    	     // Always wrap FileReader in BufferedReader.
    	        BufferedReader bufferedReader = 
    	            new BufferedReader(fileReader);

    	        
    	        while((line = bufferedReader.readLine()) != null) {
    	        	 System.out.println(line);
    	            processLine(line,lineNumber);
    		        	lineNumber++;   
    	        }
    	        
    	        // Always close files.
    	        bufferedReader.close();         
    	    }
    	    catch(FileNotFoundException ex) {
    	        System.out.println(
    	            "Unable to Open File '" + 
    	            fileName + "'");                
    	    }
    	    catch(IOException ex) {
    	        System.out.println(
    	            "Error Reading File '" 
    	            + fileName + "'");                  
    	    }
    			}
    	
    	public static void main(String [] args) throws FileNotFoundException, IOException {
    		// TODO Auto-generated method stub
    		//System.out.println("Data File");
    		readData(1);
    		readData(2);
    		System.out.println("");
    		System.out.println("Results of the 2 Fold Test :");
    		FirstFoldPercentage();
    		SecondFoldPercentage();
    		FullPercentage();
    		}
    	}
