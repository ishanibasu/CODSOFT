import java.util.Scanner;
public class NumberGame 
{

	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Let's Play THE NUMBER GAME");
		System.out.println();
		int points=0;
		int score=0;
		int round=1;
		boolean play=true;
		while(play) 
		{	System.out.println("ROUND "+round+"\n");
			int at;
			int compnum=(int)((Math.random()*100)+1);
			System.out.println("A number is generated from 1 to 100");
			System.out.println("You have 5 attempts to guess the number\nALL THE BEST!\n");

			for (at=4;at>=0;at--) 
			{
				System.out.print("Enter Your Guess: ");
				int userguess=sc.nextInt();
				if(userguess==compnum) 
				{
					System.out.println("Your Guess is Correct. CONGRATULATIONS!");
					points+=1;
					score+=1;
					break;
				}
				else if(userguess>compnum) 
				{
					System.out.println("Your Guess is Too High. Try again!");
				}
				else 
				{
					System.out.println("Your Guess is Too Low. Try again!");
				}
				System.out.println("\nAttempts left: "+at);
				if (at==0) 
				{
					System.out.println("\nSorry, no attempts left");
					System.out.println("The number generated was "+compnum);	
				}
			}
			System.out.println("\nSCORE FOR ROUND "+round+": "+score+"\n");
			score=0;
			
			System.out.print("Do you want to play ROUND "+(round+1)+"? (Y/N): ");
			String ans=sc.next();
			if(ans.equalsIgnoreCase("Y")) 
			{
				round++;
			}
			else if(ans.equalsIgnoreCase("N"))
			{
				play=false;
			}
			System.out.println();
		}
		System.out.println("Final Score (all rounds combined): "+points);
		System.out.println("Total rounds played: "+round);
		System.out.println("Thanks for Playing! See you Soon.");
		
		sc.close();
	}

}
