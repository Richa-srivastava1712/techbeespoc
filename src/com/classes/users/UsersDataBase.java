package com.classes.users;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.constants.QueryConstants;
import com.constants.URLConstants;

public class UsersDataBase {
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static Connection getConnection() {
		try {
			Class.forName(URLConstants.DATABASEDRIVER);
			URLConstants.DATABASECONNECTION = DriverManager.getConnection(URLConstants.DATABASEURL,
					URLConstants.DATABASEUSERNAME, URLConstants.DATABASEPASSWORD);
			LOGGER.log(Level.INFO, "CONNECTION ESTABLISHED");
		} catch (Exception exception) {
			LOGGER.log(Level.WARNING, "EXCEPTION OCCURED  " + exception);
		}
		return URLConstants.DATABASECONNECTION;
	}
	

	public static List<User> getAllEmployees() {
		List<User> list = new ArrayList<>();

		try {
			URLConstants.DATABASECONNECTION = UsersDataBase.getConnection();
			PreparedStatement preparedStatement = URLConstants.DATABASECONNECTION
					.prepareStatement(QueryConstants.DISPLAYALLUSERS);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User currentUser = new User();
				currentUser.setUser_id(resultSet.getInt(1));
				currentUser.setFirst_name(resultSet.getString(2));
				currentUser.setLast_Name(resultSet.getString(3));
				currentUser.setEmail_Id(resultSet.getString(5));
				currentUser.setMobile_No(resultSet.getString(6));
				currentUser.setCountry(resultSet.getString(12));
				list.add(currentUser);
			}
			LOGGER.log(Level.INFO, "PRINTED ALL USERS");
			URLConstants.DATABASECONNECTION.close();
		} catch (Exception exception) {
			LOGGER.log(Level.WARNING, "EXCEPTION OCCURED  " + exception);
		}

		return list;
	}
//------------------------------------------------ employee--------------------------------------------------------------------------------------------------------------------- 
             public static List<User> getAllEmployee(String FirstName, String LastName){  
			 List<User> filterList=new ArrayList<>();  
		try{  
			URLConstants.DATABASECONNECTION=UsersDataBase.getConnection();  
			
	        filterList=UserDaoLayer.getAllSearchUser(FirstName,LastName);
	        LOGGER.log(Level.INFO,"SEARCHED USER DISPLAYED");
	        URLConstants.DATABASECONNECTION.close();  }
	  catch(Exception exception){LOGGER.log(Level.WARNING,"EXCEPTION OCCURED  "+exception);}
	    return filterList;  
	 } 

	
	public static int deleteUser(int id) {
		int status = 0;
		try {
			URLConstants.DATABASECONNECTION = UsersDataBase.getConnection();
			PreparedStatement preparedStatement = URLConstants.DATABASECONNECTION
					.prepareStatement(QueryConstants.DELETEUSERS);
			preparedStatement.setInt(1, id);
			status = preparedStatement.executeUpdate();
			LOGGER.log(Level.INFO, "USER DELETED");
			URLConstants.DATABASECONNECTION.close();
		} catch (Exception exception) {
			LOGGER.log(Level.WARNING, "EXCEPTION OCCURED  " + exception);
		}

		return status;
	}
//------------------------------------------------------------------employees ----------------------------------------------------------------------------------------------------
	public static List<User> getAllEmployees(String userName) {
		List<User> filterList = new ArrayList<>();
		// userName="%"+userName+"%";
		try {
			URLConstants.DATABASECONNECTION = UsersDataBase.getConnection();
			PreparedStatement preparedStatement = URLConstants.DATABASECONNECTION
					.prepareStatement(QueryConstants.SEARCHUSER);
			preparedStatement.setString(1, userName);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User currentUser = new User();
				currentUser.setUser_id(resultSet.getInt(1));
				currentUser.setFirst_name(resultSet.getString(2));
				currentUser.setLast_Name(resultSet.getString(3));
				currentUser.setEmail_Id(resultSet.getString(5));
				currentUser.setMobile_No(resultSet.getString(6));
				currentUser.setCountry(resultSet.getString(12));
				filterList.add(currentUser);
			}
			LOGGER.log(Level.INFO, "SEARCHED USER DISPLAYED");
			URLConstants.DATABASECONNECTION.close();
		} catch (Exception exception) {
			LOGGER.log(Level.WARNING, "EXCEPTION OCCURED  " + exception);
		}

		return filterList;
	}
	 public static List<User> UpdateAllEmployees(String userName){  
	 	    List<User> filterList=new ArrayList<>();  
	 	   // userName="%"+userName+"%";
	 	try{  
	 	URLConstants.DATABASECONNECTION=UsersDataBase.getConnection();  
	 	PreparedStatement preparedStatement=URLConstants.DATABASECONNECTION.prepareStatement(QueryConstants.SEARCHUSER);  
	 	preparedStatement.setString(1,userName); 
	 	ResultSet resultSet=preparedStatement.executeQuery();  
	 	while(resultSet.next()){  
	 	    User currentUser=new User();  
	 	    currentUser.setUser_id(resultSet.getInt(1));  
	 	    currentUser.setFirst_name(resultSet.getString(2));  
	 	    currentUser.setLast_Name(resultSet.getString(3));  
	 	    currentUser.setEmail_Id(resultSet.getString(5)); 
	 	    filterList.add(currentUser);  
	 	}  
	 	LOGGER.log(Level.INFO,"SEARCHED USER DISPLAYED");
	 	URLConstants.DATABASECONNECTION.close();  
	 	}catch(Exception exception){LOGGER.log(Level.WARNING,"EXCEPTION OCCURED  "+exception);}  

	 	return filterList;  
	 	} 
			 public static List<Feed> getAllFeeds(String date){  
		        List<Feed> filterFeed=new ArrayList<>();  
		       // userName="%"+userName+"%";
		        
		try{  
			URLConstants.DATABASECONNECTION=UsersDataBase.getConnection();  
	        PreparedStatement preparedStatement=URLConstants.DATABASECONNECTION.prepareStatement(QueryConstants.SEARCHFEED);  
	        preparedStatement.setString(1,date); 
	        ResultSet resultSet=preparedStatement.executeQuery();  
	        while(resultSet.next()){  
	            Feed currentUser=new Feed();  
	           currentUser.setFeed_Text(resultSet.getString(2));
	           currentUser.setPhoto((Blob) resultSet.getBlob(3));
	           currentUser.setFeed_Id(resultSet.getInt(6));
	            filterFeed.add(currentUser);  
	        }  
	        LOGGER.log(Level.INFO,"SEARCHED USER DISPLAYED");
	        URLConstants.DATABASECONNECTION.close();  
	    }catch(Exception exception){LOGGER.log(Level.WARNING,"EXCEPTION OCCURED  "+exception);}  
	      
	    return filterFeed;  
	 } 
		 
		 
		 public static List<Feed> getFeedImage(int id){  
		        List<Feed> filterFeed=new ArrayList<>();  
		     
		        
		try{  
			URLConstants.DATABASECONNECTION=UsersDataBase.getConnection();  
	        PreparedStatement preparedStatement=URLConstants.DATABASECONNECTION.prepareStatement(QueryConstants.SEARCHFEEDIMAGE);  
	        preparedStatement.setInt(1,id); 
	        ResultSet resultSet=preparedStatement.executeQuery();  
	        while(resultSet.next()){  
	            Feed currentUser=new Feed();  
	           currentUser.setFeed_Text(resultSet.getString(2));
	           currentUser.setPhoto((Blob) resultSet.getBlob(3));
	           currentUser.setFeed_Id(resultSet.getInt(6));
	            filterFeed.add(currentUser);  
	        }  
	        LOGGER.log(Level.INFO,"IMAGE DISPLAYED");
	        URLConstants.DATABASECONNECTION.close();  
	    }catch(Exception exception){LOGGER.log(Level.WARNING,"EXCEPTION OCCURED  "+exception);}  
	      
	    return filterFeed;  
	 }
		  public static int InsertFeed(int id, String note, String myFile) throws FileNotFoundException{  
	            int status=0; 
	            
	            System.out.println(id);
	            System.out.println(note);
	           // File userImage = new File(image);
	           // System.out.println(userImage);
	            File myImage=new File(myFile);
	            
	            FileInputStream userImage = new FileInputStream(myImage.getAbsoluteFile());
	            //FileInputStream userImage = new FileInputStream("C:\\Users\\hppc\\Pictures\\Screenshots\\testImage.png");
	               
	            System.out.println(userImage);
	            try{  
	                URLConstants.DATABASECONNECTION=UsersDataBase.getConnection();  
	                PreparedStatement preparedStatement=URLConstants.DATABASECONNECTION.prepareStatement(QueryConstants.INSERTFEED);  
	               
	                preparedStatement.setInt(1, id);
	                preparedStatement.setString(2,note);
	                preparedStatement.setBinaryStream(3, userImage);

	 

	                status=preparedStatement.executeUpdate(); 
	                LOGGER.log(Level.INFO,"VALUES INSERTED");
	                URLConstants.DATABASECONNECTION.close();  
	            }catch(Exception exception){LOGGER.log(Level.WARNING,"EXCEPTION OCCURED  "+exception);}  
	              
	            return status;  
	        }  
	      
}
