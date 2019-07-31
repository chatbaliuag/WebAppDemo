package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import beans.LoginBean;

public class LoginDao {

	public boolean validate(LoginBean loginBean) {
		String dbUrl = "jdbc:mysql://localhost:3306/users";
		String user = "root";
		String password = "";
		
		boolean status = false;
		
		try {
			Connection myConn = DriverManager.getConnection(dbUrl, user, password);
			Statement myStmt = myConn.createStatement();
			//TO READ EVERYTHING FROM THE DB
//			ResultSet myRs = myStmt.executeQuery("select * from admin");
			
//			System.out.println("User \t\tPassword\n=========================");
//			while(myRs.next()) {
//				System.out.print(myRs.getString("firstname") + " " + myRs.getString("lastname")+ 
//								"\t"+myRs.getString("password")+ "\n");
//			}
			
			
			PreparedStatement preparedStatement = myConn.prepareStatement("select * from admin "
					+ "where firstname = ? and password = ?");
			
			preparedStatement.setString(1, loginBean.getUSERNAME());
			preparedStatement.setString(2, loginBean.getPASSWORD());
			
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();
			
			myStmt.close();
			myConn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}

}
