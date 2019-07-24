package HW4_ActivityTracker;

/*
Honor Code Affirmation

On my honor as a CCSF Student, I, Emre Yasa, thset
I have not given or received inappropriate help on this assignment
 */

/*
Design:
1)Importing the the classes needed 
2)Main do-while loop to check if the user enter valid input
	A.No unit test 
	B.Length check 
	C. Input length
	D.Non-numeric characters
	F.Last character test
3) Selections to help with if statements
	A.N for track an activity
		makeNew(keyboard); //Create new file
	B.V for view activity
		viewActivity(keyboard);//View the activity
	C.Q for quit the activity
		elapsedTime(start);
4)Methods
	A.makeFileName() ->The user makes a file name along with corrections
	B.makeFile() -> Testing the fileName, and then this program returns true if it creates the file, and returns false, if failed.
	C.makeNew() -> If previous test is valid, create a new file.
	D.viewActivity() -> View activity tracker method
	E.elapsedTime()-> To find out the time spent when the user starts the activity

*/

import java.io.*; // Provides for system input and output through data streams, serialization and the file system
import java.util.Scanner; //Gets the user input
import java.util.Date;// Activates date class



public class HW4_ActivityTracker {
	
	
	
	 public static void main (String []args) throws IOException {
		 
		 String input; //The user input
		 boolean validInput = false;//Validation input
		 String lastCharInput; //to hold the value of Activity tracker
		 Scanner keyboard = new Scanner(System.in);//Create a scanner object
		 String activityName = ""; //To hold activity name
		 String activityFileName = "";//to hold activity file name
		 long creationTimeStamp = 0; //Holds the time created
		 Date start = new Date();// Date object
		 long fileCreationTime = 0; //file creation time initialized with 0
		 
		 System.out.print(" *** Welcome to the Activity Tracker *** \n\n");
	
		 //Validating if the user enter correct for of input
		 do {			 
			//Activity Tracker Menu
			
			 System.out.print("Activity Tracker Menu\n");
			 System.out.print("\"N\"--track a new activity\n");
			 System.out.print("\"V\"--view an activity\n");
			 System.out.print("\"Q\"--quit activity tracker\n");
			 System.out.print("Enter a selection : ");
		     input = keyboard.nextLine();
		     
	
			//Length check
		     if (input.length() == 0)
		     {
		        System.out.println("Please respond the menu");
		        validInput = false;
		        continue;
		     } //if
		     
			//Input length 
		     if(input.length() > 1)
		      {
		        System.out.printf("More digit than expected with this entry %s, please try again.\n", input);
		        continue;
		      } 
			 
			//Non-numeric test
	       	  char ch = input.charAt(0);
	          if(ch  < '9' && ch > '0')
	          {
	             System.out.printf("Selection is numeric , please try again.\n", ch );
	             continue;
	          }//if
	          
	          
			 
			//Last character in input is not s,m,h,d,w OK
		      lastCharInput = input.toUpperCase().substring(input.length() -1, input.length());
		      switch (lastCharInput)
		      {
				   case "N":
				   case "V":
				   case "Q":
				      break; //break out of switch loop
		         default :
		           System.out.printf(" Selection :'%s' character unit is unknown, please try again.\n", lastCharInput);
		           continue;//break out of switch loop and goes to the very beginning of do-while loop, if character unit is unknown
		      }
		      
			  	
			  			  	
		      // if the user select "N" as an activity selection
			 if (lastCharInput.equals("N")) 
			 {
				
					 makeNew(keyboard); //Create a new file
				 
			 }
				 
		      // if the user select "V" as an activity selection						
			 if(lastCharInput.equals("V"))
			  {

					viewActivity(keyboard);//View the activity
						
			  }
					
		      // if the user select "Q" as an activity selection      
			 if(lastCharInput.equals("Q")) 
			 {
				  System.out.print("Selection : Q\n");
				  long elapsedMillis = elapsedTime(start);
				  System.out.print("Thanks for taking your time, goodbye.");
				  System.exit(0); //The mission is completed and the program ends.
			 }
		       
		    } while(true);//Main do while loop ends
		
		 
	      }//Main
	 
			 
			 //The user makes a file name along with corrections
			 private static String makeFileName(String activityName)
			 {		
					 
					 //Correction if the user entered unplanned letters
					 activityName = activityName.replace(" ", "_");
					 activityName = activityName.replace("/", "-");
					 activityName = activityName.replace(":", "");
					 activityName = activityName.replace("!", "");
					 activityName = activityName.replace("@", "");
					 activityName = activityName.replace("*", "");
					 
					 String fileName = "ACTIVITY-" + activityName + ".txt";//Holds the planned name of activity beforehand by developer.
				 
					
					 return fileName;//Returns the file name
			  }
	 
			 //Testing the fileName, and then this program returns true if it creates the file, and returns false, if failed.
			 private static boolean makeFile(String fileName, String activityName) throws IOException 
			 {
		 
					 Date fileCreationDate = new Date(); //Date constructor
					 long fileCreationTime = fileCreationDate.getTime(); //Get current time in integer format, returns a long value representing the number of milliseconds
				 
				 
				
					 System.out.printf("Activity \"%s\" tracked in file \"%s\"\n\n", activityName, fileName);//Print out the output
				 
					 File file = new File(fileName); //file constructor
					 if(file.exists()) 
					 {
					        
						  System.out.print("The file name already exists.\n");
						  return false; // return to the caller
					  
					 }
				 
					  PrintWriter pw = new PrintWriter(fileName);//Open the file
							
					  pw.printf("%d %s\n", fileCreationTime, activityName);//Write the data to the file
					  pw.close();//Close the file
				      return true;//Method returns true, if it creates file.
		 
	         }
			 
			 //If previous test is valid, create a new file.
			 private static void makeNew(Scanner keyboard) throws IOException  
			 {
		 
			
					 String activityName = "";//Holds the activity name.
					 
					 //Small test loop for if there is no any input
					 do {
						 System.out.print("Enter an activity name: "); 
						 activityName = keyboard.nextLine();
						  if(activityName.equals("")) {
							  System.out.println("You did not type anything. ");
						  }
						  
				     } while(activityName.equals(""));
					 
					 String fileName  = makeFileName(activityName);//To hold file name, method brings the file name created by user.
					 
					 makeFile(fileName, activityName);//Create a brand new file to help with two parameters 
				 
			 } 
	 
			 //View activity tracker method
			 private static void viewActivity(Scanner keyboard) throws IOException 
			 {
		   
			    String fileName = ""; //Hold the name of file
			    //Checking the if there is no entry and ask the user enter an input.
			 	do 
			 	{
			 		System.out.print("Selection : V\n");
					System.out.print("Enter the activity file name: ");
					fileName = keyboard.nextLine();
			 	 } while (fileName.equals(""));
			    
			 
			    File file = new File(fileName);//File constructor
			    //Make sure the file does not exist
				if(!file.exists()) 
				{
			    	   	System.out.print("Please write another file name, it does not exist.\n");
				} else {
					
					   Scanner activityFile = new Scanner(file); //a scanner object for reading entries from file
					   long creationTimeStamp = activityFile.nextLong(); 
					   String activityName = activityFile.nextLine();
						
					   Date creationDate = new Date(creationTimeStamp); //Creation date obj represent date and time just as the activity file was created.
					   String creationTimeStampStr = creationDate.toString(); //The string version of timestamp
					   System.out.printf("Activity tracking for \"%s\" started on %s \n", activityName, creationTimeStampStr);//Print out the time of file created for the user.
					   activityFile.close();//Closing the file
					   
				}//if
			
		   
	       }//viewActivity
			 
			//To find out the time spent when the user starts the activity
		   private static long elapsedTime(Date startDate) 
		   {
				   Date endDate = new Date(); //create an date object
				   long end = endDate.getTime(); // hold the milliseconds
				   long start = startDate.getTime();// hold the milliseconds
				   long passedTime = (end-start)/1000;//Milliseconds turn into the seconds
				 
				   //Time conversion
				   long minutes = (((passedTime  % 604800) % 86400) % 3600) / 60;
				   long hours = ((passedTime  % 604800) % 86400) / 3600;
				   long days = (passedTime  % 604800) / 86400;
				   long weeks = passedTime  / 604800;			
				   long seconds_output = (passedTime  % 3600) % 60;
					
					System.out.println( " Activity tracker was " + weeks + " week(s) "
					+ days+  " day(s) "+ hours + " hour(s), " 
					+ minutes + " minute(s), " + seconds_output + " seconds");
					
					return passedTime;
			 }
			  
	 
}//class

/*
  Test Report :
*** Welcome to the Activity Tracker *** 

Activity Tracker Menu
"N"--track a new activity
"V"--view an activity
"Q"--quit activity tracker
Enter a selection : N
Enter an activity name: Pet care2
Activity "Pet care2" tracked in file "ACTIVITY-Pet_care2.txt"

Activity Tracker Menu
"N"--track a new activity
"V"--view an activity
"Q"--quit activity tracker
Enter a selection : v
Selection : V
Enter the activity file name: ACTIVITY-Pet_care2.txt
Activity tracking for " Pet care2" started on Fri Apr 13 17:03:22 PDT 2018 

Activity Tracker Menu
"N"--track a new activity
"V"--view an activity
"Q"--quit activity tracker
Enter a selection : q
Selection : Q
Activity tracker was 0 week(s) 0 day(s) 0 hour(s), 1 minute(s), 57 seconds
Thanks for taking your time, goodbye.
 */
