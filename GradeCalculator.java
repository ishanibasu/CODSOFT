import java.util.Scanner;
public class GradeCalculator 
{

	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("STUDENT GRADE CALCULATOR\n");
		System.out.print("Enter the number of subjects: ");
		int sub=sc.nextInt();
		int total=0;
		System.out.println();
		
		for (int i=1;i<=sub;i++) 
		{
			System.out.print("Enter marks obtained in Subject "+i+": ");
			int marks=sc.nextInt();
			total+=marks;
		}
		System.out.println();
		System.out.println("Total marks obtained: "+total);
		
		double percent=(double)total/sub;
		System.out.println("Average percentage obtained: "+percent);
		switch ((int)percent/10) 
		{
		case 10:
		case 9:
			System.out.println("Grade obtained: O GRADE");
			break;
		case 8:
			System.out.println("Grade obtained: A GRADE");
			break;
		case 7:
			System.out.println("Grade obtained: B GRADE");
			break;
		case 6:
			System.out.println("Grade obtained: C GRADE");
			break;
		case 5:
			System.out.println("Grade obtained: D GRADE");
			break;
		case 4:
			System.out.println("Grade obtained: E GRADE");
			break;
		default:
			System.out.println("Grade obtained: F GRADE");
			break;
		}
		sc.close();
	}

}
