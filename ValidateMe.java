/*
Name: Arnav Chahal
VUnetID: chahaa1
Email: arnav.chahal@vanderbilt.edu
Class: CS 1101 - Vanderbilt University
Section: 1
Date: 10/28/22
Honor statement: I attest that I understand the honor code for this class and have neither
                 given nor received any unauthorized aid on this assignment.
Program description:  This program asks for the user's test score, their name, the class
                      average, and the user's grade in the class.  It utilizes multiple
                      methods in order to validate that the information the user inputted
                      makes sense with the specified field
 */
import java.util.Scanner;

public class ValidateMe {
    final static double EPSILON = 0.0001;

    public static void main(String[] args) {
        //creates a Scanner object then it prints the score, name, average and grade
        Scanner in = new Scanner(System.in);
        getScore(in);
        getName(in);
        getAverage(in);
        getGrade(in);
    }

    /**
     * This method utilizes user input and multiple scanners in order to get a test score
     * which makes sense, and then it prints it out.  Every time and invalid answer is entered
     * it prints out that it is an invalid score and to enter again
     * @param in The Scanner object which is used to get the test score from the user input
     *           and validate it.
     */
    public static void getScore(Scanner in) {
        //asks for the test score trims the line and then makes a new Scanner
        //for that string
        System.out.print("Enter test score: ");
        String userString = in.nextLine().trim();
        Scanner stringScan = new Scanner(userString);

        //sets up a base value for testScore and then makes sure the next item is an int
        //if it is an int it assigns that value to test score
        int testScore = -1;
        if (stringScan.hasNextInt()) {
            testScore = stringScan.nextInt();
        }

        //the while loop is used to make sure test score is within the acceptable range
        while ((testScore > 100) || (testScore < 0)) {
            System.out.print("Test score must be between 0-100 (inclusive), " +
                    "try again: ");

            //reassigns userString so the user can try again and then makes a new Scanner to
            //check the user entered an int
            userString = in.nextLine().trim();
            Scanner strScan = new Scanner(userString);
            if (strScan.hasNextInt()) {
                testScore = strScan.nextInt();
            }
        }//end of while loop

        System.out.printf("You entered a valid test score of %d\n", testScore);
    }//end of getScore method

    /**
     * This method gets the user's name and then makes sure it is not an empty string
     * @param in this Scanner object is used to get the name from the console
     */
    public static void getName(Scanner in) {
        System.out.print("Enter name: ");
        String name = in.nextLine().trim();

        //this while loop makes sure the entered name is not an empty string
        while (name.length() == 0) {
            System.out.print("Nothing entered, try again: ");
            name = in.nextLine().trim();
        }

        System.out.printf("You entered a valid name of %s\n", name);
    }//end of getName method

    /**
     * This method gets the average for the class from the user inputted data and makes sure
     * it is an average that makes sense.
     * @param in this is the Scanner object used to get the average from the console.
     */
    public static void getAverage(Scanner in) {
        //same logic as in the getScore method
        System.out.print("Enter class average: ");
        String userString = in.nextLine().trim();
        Scanner stringScan = new Scanner(userString);

        //sets the test average to an unreachable value and then makes sure the
        //string from the user is not a blank line and then makes sure the
        //next item is a double
        double testAvg = -1.0;
        if(userString.length() != 0) {
            if (stringScan.hasNextDouble()) {
                testAvg = stringScan.nextDouble();
            }
        }

        //This while loop makes sure the test average is in the acceptable range
        while ((100 + EPSILON < testAvg) || (-EPSILON > testAvg)) {
            System.out.print("Class average must be between 0-100 (inclusive), " +
                    "try again: ");

            //gets a new string from the console and trims it, then it makes a new Scanner
            //for this string and checks the  length is not zero and that is has a double
            userString = in.nextLine().trim();
            Scanner strScan = new Scanner(userString);

            if(userString.length() != 0){
                if (strScan.hasNextDouble()) {
                    testAvg = strScan.nextDouble();
                }
            }
        }//end of while loop

            System.out.printf("You entered a valid class average of %.2f\n", testAvg);
    }//end of getAverage class

    /**
     * This method gets the grade from the user and accepts only valid letter grade
     * @param in this Scanner object is used to get the letter grade from the console
     */
    public static void getGrade(Scanner in) {
        //Asks for the grade then gets it from the console and trims it, and then makes
        //it uppercase
        System.out.print("Enter grade: ");
        String grade = in.nextLine().trim();
        grade = grade.toUpperCase();

        //Sets letterGrade to an unreachable value and then checks if the user input is not
        //a blank line and finally assigns it to the char letterGrade
        char letterGrade = 'k';
        if(grade.length() != 0){
            letterGrade = Character.toUpperCase(grade.charAt(0));
        }

        //This while loop executes if the letter is not a valid letter grade and
        //asks the user to try again
        while (!(letterGrade == 'A' || (letterGrade) == 'B' || (letterGrade) == 'C'
                || (letterGrade) == 'D' || (letterGrade) == 'F')) {
            System.out.print("Grade must be A, B, C, D, or F, try again: ");
            grade = in.nextLine().trim();

            //creates a new scanner and assigns it to the new user input and makes
            //sure it is not a blank line and then assigns the first char to letter grade
            Scanner scan = new Scanner(grade);
            if (grade.length() != 0) {
                letterGrade = scan.next().charAt(0);
                letterGrade = Character.toUpperCase(letterGrade);
            }
        }//end of while loop

        System.out.printf("You entered a valid grade of %s\n",
                Character.toUpperCase(letterGrade));
    }//end of getGrade method
}//end of ValidateMe Class


