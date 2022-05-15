
package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.sql.Date;

public class CompletedTaskmodel {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); // Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/electrogrid_customer", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String Completetask(String Tid, String CompletedDate, String Expenditure) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for completed task";
			}
// create a prepared statement
			String query = " insert into completed_task (`Tid`,`completed_date`,`Expenditure`)" + " values (?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			Date date = Date.valueOf(CompletedDate);
			preparedStmt.setString(1, Tid);
			preparedStmt.setDate(2, (java.sql.Date) date);
			preparedStmt.setDouble(3, Double.parseDouble(Expenditure)); // execute the statement
			preparedStmt.execute();
			con.close();
			output = "Task completed";
		} catch (Exception e) {
			output = "Error while inserting to the Completed Task table";
			System.err.println(e.getMessage());
		}
		return output;
	}

// Update 
	public String updateCompletetask(String Tid, String CompletedDate, String Expenditure) {
String output = "";try
	{
		Connection con = connect();
		if (con == null) {
			return "Error while connecting to the database for updating.";
		}
// create a prepared statement
		String query = "UPDATE completed_task SET completed_date=?,Expenditure=? WHERE Tid=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
		Date date = Date.valueOf(CompletedDate);
		preparedStmt.setDate(1, (java.sql.Date) date);
		preparedStmt.setDouble(2, Double.parseDouble(Expenditure));
		preparedStmt.setString(3, Tid);
// execute the statement
		preparedStmt.execute();
		con.close();
		output = "Updated successfully";
	}catch(
	Exception e)
	{
		output = "Error while updating the Completed Task";
		System.err.println(e.getMessage());
	}return output;}
// Get 
	public String readtask(String Tid) {
String output = "";try
	{
		Connection con = connect();
		if (con == null) {
			return "Error while connecting to the database for reading.";
		}
// Prepare the html table to be displayed
		output = "<table border='1'><tr><th>Task ID</th><th>CompletedDate</th>" + "<th>Expenditure</th></tr>";
		String query = "select * from completed_task where Tid=" + Tid + "";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
		while (rs.next()) {
			String Taskno = rs.getString("Tid");
			String CompletedDate = rs.getString("Completed_Date");
			String Expenditure = Double.toString(rs.getDouble("Expenditure"));
// Add into the html table
			output += "<tr><td>" + Taskno + "</td>";
			output += "<td>" + CompletedDate + "</td>";
			output += "<td>" + Expenditure + "</td>";
// buttons
			output += "</tr>";
		}
		con.close();
// Complete the html table
		output += "</table>";
	}catch(
	Exception e)
	{
		output = "Error while reading the Bill data";
		System.err.println(e.getMessage());
	}return output;
	}

	public String deleteTask(String tid) {
String output = "";
try {
Connection con = connect();
if (con == null) {
return "Error while connecting to the database for deleting.";
}
// create a prepared statement
String query = "delete from completed_task where Tid=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setString(1, tid);
// execute the statement
preparedStmt.execute();
con.close();
output = "Task Deleted successfully";
} catch (Exception e) {
output = "Error while deleting the from Task table.";
System.err.println(e.getMessage());
}
return output;
}

	public String readAllTasks() {
Client c = Client.create();
WebResource resource =
c.resource("http://localhost:8083/InquiryManagementService/FeedbackServices/Tasks/readTasks");
String output = resource.get(String.class);
return "From InquiryManagement-service: " + output;
}
}
