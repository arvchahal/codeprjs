 // Program solves the towers of Hanoi problem, and
// demonstrates recursion.

public class TowersOfHanoi
{
   private int numDisks; // number of disks to move

   public TowersOfHanoi( int disks )
   {
      numDisks = disks;
   } // end constructor TowersOfHanoi 

   // recursively move disks between towers
   public void solveTowers( int disks, int sourcePeg, int destinationPeg,
      int tempPeg )
   {
	   this.numDisks = disks;
	   if(disks==1)
	   {
		   System.out.printf("Move disk %d from %d to %d\n", disks, sourcePeg, destinationPeg);
	   }
	   if(disks>1)
	   {
		   solveTowers(disks-1,sourcePeg, tempPeg, destinationPeg );
		   System.out.printf("Move disk %d from %d to %d\n", disks, sourcePeg, destinationPeg);
		   solveTowers(disks-1,tempPeg, destinationPeg, sourcePeg );

		
	   }
	   
		   
		   
	// Write Recursive code to Solve TowersOfHanoi

   } // end method solveTowers
} // end class TowersOfHanoi

