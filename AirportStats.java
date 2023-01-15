import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
public class AirportStats {
    public static void main(String[] args) throws FileNotFoundException {

        //Creates a Scanner object and gets the user string for the airport name
        Scanner in = new Scanner(System.in);
        String userStr = checkCSV(in);

        //This makes sure that the airport name entered was verified so the rest of the method
        //does not execute
        if(userStr!=null){

            //creates the valid file and a Scanner object for it
            String file = isValid(in);
            FileInputStream fileInput = new FileInputStream(file);
            Scanner fileScan = new Scanner(fileInput);

            //sets the initial year and prints out the table and creates an array to hold the
            //total number of flights cancelled, delayed, diverted, and on time
            int year = 2003;
            print(userStr);
            int totalFlights[] = new int[4];
            totalFlights = getStats(userStr,fileScan, year, totalFlights);
            printEnd(totalFlights);
        }
    }

    /**
     * This method is used to get the name of the airport from the user it either is the code
     * or it is the actual name of the airport.  This method checks to see whether the entered
     * name is a valid name of an airport if it is not then it returns null and says airport
     * is not found
     * @param in This is the Scanner object which is used to get the name or code name of the
     *           airport from the user
     * @return The user entered airport name is returned if it is a valid name otherwise null is
     * returned
     * @throws FileNotFoundException This catches any file nto found exceptions that may occur
     * if a file is not found.
     */
    public static String checkCSV(Scanner in) throws FileNotFoundException {

        //asks for the code or name from the user, assigns it to a variable
        System.out.print("Enter airport name or code: ");
        String userStr = in.nextLine();
        String inter = "International";

        //creates a file and Scanner for the file with all the specific airport names
        //and codes
        FileInputStream code = new FileInputStream("airports-code.csv");
        Scanner codeScan = new Scanner(code);

        //looks to see if the airport the user entered is valid if it is then it returns
        //the user string if it is not then it returns null
        while(codeScan.hasNextLine()){

            String x = codeScan.nextLine();
            String temp[] = x.split(",");
            if(temp.length==2) {
                if(userStr.equalsIgnoreCase(temp[0])){
                    userStr = temp[0];

                    return userStr;
                }

                if(userStr.toUpperCase().contains(inter.toUpperCase())){
                    System.out.println("Airport not found.");
                    return null;
                }

                if(temp[1].toUpperCase().contains(userStr.toUpperCase())) {
                    userStr = temp[0];
                    return userStr;
                }
            }
        }//end of while loop

        System.out.println("Airport not found.");
        return null;
    }//end of checkCSV method

    /**
     * This method is used to make sure the data file entered is an actual file.  If it is not
     * it keeps prompting the user to enter a valid data file until one is entered
     * @param in This is the scanner object used to get user input for what file they want to
     *           scan
     * @return  fileName is returned which is the valid file name inputted by the user.
     */
    public static String isValid(Scanner in){
        System.out.print("Enter data file name: ");
        String fileName = in.nextLine().trim();
        boolean validFile;

        //checks for the file and catches the exception if it is a real file then the while
        //loop is not executed.
        try{ FileInputStream file = new FileInputStream(fileName);
            validFile= true;
        }

        catch
        (FileNotFoundException e){
            validFile = false;
        }

        //makes sure the filename is a real file if not it loops till a real file
        //is entered
        while(( validFile )==false || fileName.length()==0){
            System.out.print("File does not exist, try again: ");
            fileName = in.nextLine().trim();
            try{ FileInputStream file = new FileInputStream(fileName);
                validFile= true;
            }

            catch
            (FileNotFoundException e){
                validFile = false;
            }
        }

        System.out.printf("%s successfully found.\n\n", fileName);

        return fileName;
    }//end of isValid method
    public static void print(String userStr) throws FileNotFoundException {

        FileInputStream code = new FileInputStream("airports-code.csv");
        Scanner codeScan = new Scanner(code);
        while (codeScan.hasNextLine()) {
            String x = codeScan.nextLine();
            String temp[] = x.split(",");
            for (int i = 0; i < temp.length; i++) {

                if (userStr.equalsIgnoreCase(temp[i])) {
                    System.out.println(temp[0] + " | " + temp[1]);
                }
            }
        }
        System.out.println("Flight Statistics\n" +
                "\n" +
                "============================================================\n" +
                "Year     Cancelled       Delayed      Diverted       On Time\n" +
                "============================================================");
    }//end of print method

    /**
     * This method is used to get the stats for the amount of flights whihc were either delayed,
     * cancelled, diverted, or on time.  Then it prints out the statistics in an ordered table and
     * returns the total amount of flights, delayed, cancelled, diverted, or on time.
     * @param userStr This is the user string which is used to make sure that the airport is
     *                the same as the one the user entered.
     * @return total array is returned which is an integer array which holds the total amount of
     *         flights delayed, cancelled, diverted, and on time.
     */
    public static int[] getStats(String userStr,Scanner fileScan,int year, int total[]){

        //These are the arrays which hold the total amount of cancelled, delayed, diverted
        //and on time flights and the arr array is used as a temp array for each iteration
        //of the while loop
        //int total[] = new int[4];
        int[]  arr = new int[4];

        //This while loop keeps repeating for each line until it reaches the end of the file
        while( fileScan.hasNextLine() )
        {
            //creates a temporary String to be read and then splits the String into an array
            //with each new element after the comma
            String temp = fileScan.nextLine();
            String[]  allElements= temp.split(",");

            //makes sure the line is a valid line with a length of six and that
            //the user innputted airport is the one being analyzed
            if(allElements.length == 6 && userStr.equals(allElements[0])) {

                //gets the current year and then if the current year and year variable don't
                //match it will print all the values for that year and go to the next year
                //it also adds all the elements of that year to the total array
                int currYear = Integer.parseInt(allElements[1].split("/")[0]);

                if(currYear != year) {
                    System.out.printf("%d %,13d %,13d %,13d %,13d\n",
                            year, arr[0], arr[1], arr[2], arr[3]);


                    for(int i =0;i<4;i++){
                        total[i] += arr[i];
                    }

                    // assigns year to the new year and resets the array
                    year = currYear;
                    Arrays.fill(arr, 0);
                    }

                //adds all the cancelled flights to the temp array
                for(int j=0;j<4;j++){
                    arr[j] += Integer.parseInt(allElements[j+2]);
                }
            }//end of overarching if

            //gets the last line and prints it and adds it to the total
            if( !fileScan.hasNextLine() ){
                System.out.printf("%d %,13d %,13d %,13d %,13d\n",year,arr[0],arr[1],arr[2],arr[3]);

                for(int i =0;i<4;i++){
                     total[i] += arr[i];

                }
            }//end of if statement

        }//end of while loop

        return total;

    }//end of getStats method

    /**
     * This is the print end method which prints the formatting for the end of the table, and
     * it also prints the averages for the cancelled flights, on time flights,
     * delayed fligths, and diverted flghts.
     * @param total This is the total amount of delayed, on time, diverted, and cancelled flights
     *              which was gotten from the getStats method
     */
    public static void printEnd(int total[]){

        //Calculates the total amount of flights
        int totalFlights =0;
        for(int i =0; i<total.length;i++) {
             totalFlights += total[i];
        }

        //assigns integers for the different parts of the array
        int cancelledFlights = total[0];
        int delayedFlights = total[1];
        int divertedFlights = total[2];
        int onTimeFlights = total[3];

        //prints out the formatting for the end of the table and does the averages
        System.out.println("============================================================");

        System.out.printf("%,18d %,13d %,13d %,13d\n %16.1f%% %12.1f%% %12.1f%% %12.1f%%\n",
                cancelledFlights,delayedFlights,divertedFlights,onTimeFlights,
                (double)(cancelledFlights)/totalFlights * 100,
                (double)(delayedFlights)/totalFlights * 100,
                (double)(divertedFlights)/totalFlights * 100,
                (double)(onTimeFlights)/totalFlights * 100);
    }//end of printEnd method
}//end of AirportStats class