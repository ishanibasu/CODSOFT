package Students;

import java.sql.*;
import java.util.*;

public class StudentManagement {
	public static void main(String[] args)
	{
		Scanner inp=new Scanner(System.in);
        while (true) 
        { 
            System.out.print("MAIN MENU\n1. Enter a new student\n2. Delete an existing student\n3. Search for a student\n4. Display all students\n5. Exit program\nEnter your choice (1-5): ");
            int choice=inp.nextInt();
            inp.nextLine();
            System.out.println();
            if(choice==5)
                break;
            boolean result;
            switch (choice) {
                case 1:
                    System.out.print("Enter Roll Number: ");
                    String rollno=inp.nextLine();
                    System.out.print("Enter Name: ");
                    String stname=inp.nextLine();
                    System.out.print("Enter Grade (1-5): ");
                    int grade=inp.nextInt();
                    inp.nextLine();
                    result=add_student(stname,rollno,grade);
                    if(!result)
                    {
                        System.out.println("Operation could not be completed successfully");
                    }
                    else
                    {
                        System.out.println("New student added to database");
                    }
                    break;
                case 2:
                    System.out.print("Enter Roll Number: ");
                    String rolldel=inp.nextLine();
                    result=remove_student(rolldel);
                    if(!result)
                    {
                        System.out.println("Operation could not be completed successfully");
                    }
                    else
                    {
                        System.out.println("Student record deleted from database");
                    }
                    break;
                case 3:
                    System.out.print("Enter Name: ");
                    String nameser=inp.nextLine();
                    search_student(nameser);
                    break;
                case 4:
                    System.out.print("Enter Sort Order\n1. Name\n2. Roll No\n3. Grade\nEnter Choice: ");
                    int ord=inp.nextInt();
                    display_students(ord);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid input, enter a number between 1 and 5");;
            }
        }
        System.out.println();
        inp.close();

	}

    public static boolean add_student(String nm,String rl,int gr)
    {
        if(nm==null || nm.isEmpty() || rl==null || rl.isEmpty() || gr<1 || gr>5)
        {
            return false;
        }
        else
        {
            try 
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "Admin@123");
                Statement stm=conn.createStatement();
                String insertSQL="INSERT INTO student (stuname,rollno,grade) VALUES (?,?,?);";
                PreparedStatement prestate=conn.prepareStatement(insertSQL);
                prestate.setString(1, nm);
                prestate.setString(2, rl);
                prestate.setInt(3, gr);
                prestate.executeUpdate();
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }

    public static boolean  remove_student(String rl)
    {
        if(rl==null || rl.isEmpty())
        {
            return false;
        }
        else
        {
            try 
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "Admin@123");
                Statement stm=conn.createStatement();
                String deleteSQL="DELETE FROM student WHERE rollno=?;";
                PreparedStatement prestate=conn.prepareStatement(deleteSQL);
                prestate.setString(1, rl);
                int result=prestate.executeUpdate();
                if(result==0)
                	return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }

    public static void search_student(String nm)
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "Admin@123");
            Statement stm=conn.createStatement();
            String searchSQL="SELECT * FROM student WHERE stuname LIKE ?;";
            PreparedStatement prestate=conn.prepareStatement(searchSQL);
            prestate.setString(1, "%"+nm+"%");
            ResultSet resset=prestate.executeQuery();
            int norec=0;
            while(resset.next())
            {
            	norec++;
            	System.out.println("Student Name: "+resset.getString("stuname"));
            	System.out.println("Roll No: "+resset.getString("rollno"));
            	System.out.println("Grade: "+resset.getString("grade"));
            	System.out.println();
            }
            System.out.println("Total " + norec + " record(s) found");
            System.out.println();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public static void display_students(int ord)
    {
    	try
    	{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "Admin@123");
	        Statement stm=conn.createStatement();
	        String displaySQL="SELECT * FROM student ORDER BY stuname ASC;";
	        switch(ord)
	        {
	        case 1:
		        displaySQL="SELECT * FROM student ORDER BY stuname ASC;";
		        break;
	        case 2:
	        	displaySQL="SELECT * FROM student ORDER BY rollno ASC;";
	        	break;
	        case 3:
	        	displaySQL="SELECT * FROM student ORDER BY grade ASC;";
	        	break;
	        default:
	        	System.out.println("Valid input between 1 and 3");
	        }
	        ResultSet resset=stm.executeQuery(displaySQL);
	        System.out.println("Roll\tGrade\tStudent's Name");
	        int norec=0;
	        while(resset.next())
	        {
	        	norec++;
		        System.out.println(resset.getString("rollno")+"\t"+resset.getString("grade")+"\t"+resset.getString("stuname"));
	        }
	        System.out.println("Total " + norec + " record(s) displayed");
	        System.out.println();
	    } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
    }
}
