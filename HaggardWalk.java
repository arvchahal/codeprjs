/*
Name: Arnav Chahal
VUnetID: chahaa1
Email: arnav.chahal@vanderbilt.edu
Class: CS 1101 - Vanderbilt University
Section: 1
Date: 10/27/22
Honor statement: I attest that I understand the honor code for this class and have neither
                 given nor received any unauthorized aid on this assignment.
Program description:  This program simulates 5 different scenarios of Haggard McStagger
                      walking near a cliff on the way back home.  This program
                      uses the random object in order to simulate his walk and prints
                      out each step of his walk and if he falls asleep
                      after ten steps or falls of a cliff.
 */
import java.util.Random;
import java.util.Scanner;
public class HaggardWalk {

    //These are the clas constants used throughout the proragm
    static final String  WEST = "One step WEST...be careful Haggard";
    static final String  NORTH = "One step NORTH";
    static final String  EAST = "One step EAST";
    static final String  SOUTH = "One step SOUTH";
    static final int SIM_COUNTER = 5;


    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        Random random = new Random();

        // Get seed from user.
        int seed = getSeed(console);

        // Set seed for random number generator.
        random.setSeed(seed);

        //Calculates the amount of times he falls asleep, and then it prints his walking
        //simulation and the final stats
        int cliffCounter =  walk(random);
        int sleepCounter = 5 - cliffCounter;
        finalStats(sleepCounter,cliffCounter);


    }

    /**
     * Prompts and returns the random number generator seed.
     *
     * THIS METHOD PROVIDED TO YOU. DO NOT MODIFY.
     *
     * @param  console A Scanner object for console (or keyboard) input.
     * @return         The seed to set the random number generator.
     */
    public static int getSeed(Scanner console) {
        System.out.print("Enter a seed: ");
        int seed = console.nextInt();
        return seed;
    }

    /**
     * This method simulates Haggard's walk five different times.  It prints out each step
     * and the step direction along with whether Haggard falls off a cliff or
     * falls asleep
     * @param random A random object used to generate each one of Haggard's steps
     * @return cliffCounter this is an integer value which has the amount of times
     * Haggard falls off a cliff
     */
    public static int walk(Random random) {
        int cliffCounter =0;
        for(int i = 0; i < SIM_COUNTER; i++) {
            System.out.printf("=== Simulation %d ===\n", i + 1);
            //Initial x and y position which reset after each simulation
            int x = 1;
            int y = 0;

            for (int j = 0; j < 10; j++) {
                //Next step and position
                System.out.printf("About to take step no. %d\n", j + 1);
                System.out.printf("Haggard's current position is (%d, %d)\n", x, y);

                //Simulates the step using the random object and identifies which direction
                //Haggard moves based on each output
                int step = random.nextInt(4);

                if (step == 0) {
                    y++;
                    System.out.println(NORTH);
                }

                else if (step == 1) {
                    y--;
                    System.out.println(SOUTH);
                }

                else if (step == 2) {
                    x++;
                    System.out.println(EAST);
                }

                else {
                    x--;
                    System.out.println(WEST);
                }

                //Prints new position and then checks if he falls off the cliff or whether
                //he falls asleep
                System.out.printf("Haggard's new position is (%d, %d)\n", x, y);
                if (x == -2) {
                    System.out.println("\nOh no! Haggard stepped off the cliff!\n");
                    cliffCounter++;
                    j = 10;

                }
                else if (j == 9 && x != -2) {
                    System.out.println("\nHaggard safely fell asleep after taking 10 steps.\n");
                }
            }//end of nested for loop
        }
        return cliffCounter;
    }//end of walk method

    /**
     * This method prints out the final stats after the five simulations
     * This is done by printing out the counter variables.
     * @param sleepCounter this variable is the amount of times that haggard fell asleep
     *                     after 10 steps in the simulation
     * @param cliffCounter this variable is the amount of times haggard fell off the cliff
     *                     in the simulation
     */
    public static void finalStats(int sleepCounter, int cliffCounter){
        System.out.println("=== Final statistics ===");
        System.out.printf("Total number of simulations run: %d\n" +
                "Fell asleep after 10 steps: %d times\n" +
                "Stepped off the cliff: %d times\n", SIM_COUNTER, sleepCounter, cliffCounter);
    }//end of finalStats method
}//end of HaggardWalk class