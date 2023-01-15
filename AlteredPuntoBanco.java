/*
Name: Arnav Chahal
VUnetID: chahaa1
Email: arnav.chahal@vanderbilt.edu
Class: CS 1101 - Vanderbilt University
Section: 1
Date: 11/16/22
Honor statement: I attest that I understand the honor code for this class and have neither
                 given nor received any unauthorized aid on this assignment.
Program description:  This program is used to simulate a game of Punto Banco.  Two cards are
                      given to both the player and the bank with each card having specific
                      values based on the games rules.  The program then asks the user
                      if they want to play again and it keeps going until the user prompts
                      it to stop.  Then at the end it siplays the stats of the game with
                      percetages of wins and loses for the bank and the player and the
                      amount of times they tied.
 */
import java.util.Scanner;
import java.util.Random;
public class AlteredPuntoBanco {
    public static void main(String[] args) {
        //Creates a new Scanner object and a Random object
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        //gets the seed and sets the seed
        int seed = getSeed(in);
        rand.setSeed(seed);

        //gets the player and bank card values
        int playerCardsTotal = getCards(rand, "Player");
        int arr[];
        if(playerCardsTotal == 8 || playerCardsTotal == 9){
             arr = winner(playerCardsTotal, 0);
            playAgain(arr, in, rand);

        }

        //finds the winner and asks if the user wants to play again
        else{
            int bankCardsTotal = getCards(rand, "Bank");
            arr = winner(playerCardsTotal, bankCardsTotal);
            playAgain(arr, in, rand);
        }
        printEnding(arr);
    }


    /**
     * This method gets the seed from the user and makes sure it is within the acceptable range
     * @param in This is the Scanner object which is used to get the seed from the user
     * @return This returns the user entered seed in the valid range.
     */
    public static int getSeed(Scanner in){
        System.out.print("Enter a seed: ");
        int seed = -1;
        seed = in.nextInt();

        while(seed < 1){
            System.out.print("Not a positive number, try again: ");
            seed = in.nextInt();
        }
        System.out.println();
        return seed;
    }//end of getSeed method

    /**
     * This method simulates the player and bank getting cards and then draws another card
     * if they need one and then it returns the total value of the hand
     * @param rand This is the random variable which is used to generate each card from
     *             an 2-10 and also the face cards
     * @param type This is the type of player whether it is the bank or the player
     * @return total is the integer returned which is the total value of each hand
     */
    public static int getCards(Random rand, String type){

        //Creates a new array which can hold three elements and then a for loop which draws
        //the cards and sees if a third card is needed

        int arr[] = new int[3];
        String[] num = {"first", "second", "third"};
        for(int i = 0;i < 3; i++) {
            int card = rand.nextInt(13)+1;

            if (card > 10) {
                //identifies which type of face card it is
                String jack = (card == 11) ? "Jack":"";
                String queen = (card == 12) ? "Queen":"";
                String king = (card == 13) ? "King":"";
                System.out.printf("%s draws %s card: %s%s%s\n", type, num[i], jack, queen, king);
                }

            //checks for Ace
            else if(card == 1){
                System.out.printf("%s draws %s card: Ace\n", type, num[i]);
                arr[i] = card;
            }

            //does every other card
            else{
                System.out.printf("%s draws %s card: %d\n", type, num[i], card);
                arr[i] = card;
            }

            //sees if a third card needs to be drawn
            if((arr[0] + arr[1]) % 10 >= 6 && i == 1){
                i = 3;
            }

        }//end of for loop

        //gets the total value of the hand and prints it out before returning it
        int total = (arr[0] + arr[1] + arr[2]) % 10;
        System.out.printf("%s total: %d\n\n", type, total);
        return total;

    }//end of getCards method

    /**
     * This method is used to check who the winner by comparing the value totals of each hand
     * @param playerTotal This is the total value of the player hand
     * @param bankTotal THis is the total value of the dealer hand
     * @return The value returned is an array which keeps track of who won or if there was a tie
     */
    public static int[] winner(int playerTotal, int bankTotal){
        int arr[] = new int[3];

        //These conditional statements check to see if it was a player win, bank win, or a tie
        if(playerTotal > bankTotal){
            System.out.println("Result of game: player wins\n");
             arr[0] += 1;
        }

        else if(bankTotal > playerTotal){
            System.out.println("Result of game: bank wins\n");
            arr[1] += 1;
        }

        else{
            System.out.println("Result of game: tie\n");
            arr[2] += 1;
        }

        return arr;
    }//end of winner method

    /**
     * This method is used to ask the player if they would like to play again and it keeps track
     * of the stas from previous games and then prints it out when the user does not want
     * to keep playing
     * @param arr This is an integer array which is used to keep track of the total amount of
     *            games played and what was the outcome of each
     * @param in This is the Scanner object which is used to ask the user if they want to play
     *           again or not.
     * @param rand This is the random object which is used to see if get the different
     *             card values for each game.
     */
    public static void playAgain(int arr[], Scanner in, Random rand){

        System.out.print("Would you like to play again (Y/N)? ");
        String userStr = in.next();

        //gets the first letter from the string
        char answer = Character.toUpperCase(userStr.charAt(0));



        //keeps running until user inputs to stop
        while(answer == 'Y') {

            int playCard  = getCards(rand, "Player");
            int temp[];
            if(playCard ==9 || playCard ==8){
                 temp =  winner(playCard, 0);
            }
            else{
            int bankCard = getCards(rand, "Bank");
             temp =  winner(playCard, bankCard);}

            //This for loop keeps track of the total player wins, bank wins, and ties
            for(int i=0;i<temp.length;i++){
                arr[i] +=temp[i];
            }

            //Asks to play again and is a chance to break out of the loop
            System.out.print("Would you like to play again (Y/N)? ");
            userStr = in.next();
            answer = Character.toUpperCase(userStr.charAt(0));

        }//end of while loop

        //prints out game statistics at the end
        int total = arr[0]+arr[1]+arr[2];

    }//end of playAgain class

    /**
     * This method is used to print the ending statistics of the game and it uses the array
     * from the play again method to get the total wins, losses, and ties
     * @param arr This is the array from the play again method which holds the wins losses
     *            and the ties
     */
    public static void printEnding(int arr[]){

        //gets the total games played
        int total = arr[0]+arr[1]+arr[2];

        //gets the proper plural of game and then it prints out the statistics
        String plural = total ==1 ? "game": "games";

        System.out.printf("Player wins: %d of %d %s (%.1f%%)\n" +
                        "Bank wins: %d of %d %s (%.1f%%)\n" +
                        "Ties: %d of %d %s (%.1f%%)\n", arr[0], total, plural,
                (double)(arr[0])/total *100, arr[1], total, plural,(double)(arr[1])/total *100 ,
                arr[2], total, plural, (double)(arr[2])/total *100);
    }//end of printEnding method
}//end of AlteredPuntoBanco class