import java.sql.*;
import java.util.*;

public class DatabaseConnection{
	
	public Connection getConnection(){
		// loading the driver class
		try{
			String url = "jdbc:mysql://localhost:3307/bcathird";
			String username="root";
			String password="";
			// Loading the jdbc driver
			Class.forName("com.mysql.jdbc.Driver"); 
			// creating connection
			Connection cn = DriverManager.getConnection(url, username, password); 
			System.out.println("Database Connected!");
			return cn;
		}catch(ClassNotFoundException e){
			e.printStackTrace(); // prints the error detail when happened
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void insertData(){
		try{
			// create statement
			Statement stat = getConnection().createStatement();
			String query = "insert into secondsemester (RollNo, FirstName, MiddleName, lastName) values (5, 'Elina', '', 'Tandukar')";
			// execute/query statement
			stat.executeUpdate(query);
			System.out.println("Record inserted!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public ArrayList<Student> selectData(){
		ArrayList<Student> students = new ArrayList<>();
		try{
			Statement stat = getConnection().createStatement();
			String query = "select * from secondsemester";
			ResultSet rs = stat.executeQuery(query);
			
			while(rs.next()){
				Student s = new Student();				
				s.setFirstName(rs.getString("FirstName"));
				students.add(s);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return students;
	}
	
	class Student{
		String firstName;
		public void setFirstName(String fn){
			this.firstName = fn;
		}
		public String getFirstName(){
			return this.firstName;
		}
		
	}
	
	public static void main(String[] args) {
		DatabaseConnection dc = new DatabaseConnection();
		//dc.getConnection();
		//dc.insertData();
		ArrayList<Student> students = dc.selectData();
		for(Student s: students){
			System.out.println(s.getFirstName());
		}
	}
}