package com.osttra.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.osttra.to.User;
import com.osttra.utils.DBUtils;

@Repository
public class UserRepository {
	
	

	public void addUser(User user) {

		try {
			
			if(user.getRole().equals("admin")) {
				user.setStatus("active");
			}else {
				user.setStatus("inactive");
			}

			Connection connection = DBUtils.getConnection();

			PreparedStatement statement = connection.prepareStatement("insert into user values(?, ?, ?, ?, ?, ?, ?)");

			statement.setString(1, user.getUserid());
			statement.setString(2, user.getName());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getRole());
			statement.setString(6, user.getStatus());
			statement.setInt(7, user.getAttempts());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("inside catch of addUser() of UserRepository...");
		}

	}

	public User getUser(String userid, String password) {

		User user = null;

		try {

			Connection connection = DBUtils.getConnection();

			PreparedStatement statement = connection.prepareStatement("select * from user where userid = ? and Password = ?");

			statement.setString(1, userid);
			statement.setString(2, password);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				String name = resultSet.getString(2);
				String email = resultSet.getString(3);
				String role = resultSet.getString(5);
				String status = resultSet.getString(6);
				int attempts = resultSet.getInt(7);

				user = new User(userid, name, email, password, role, status, attempts);
			}
		} catch (Exception e) {
			System.out.println("inside catch of getUser(username and password) of UserRepository...");
			e.printStackTrace();
		}

		return user;
	}

	public User deleteUser(String userid) {
		
		User user = null;

		try {

			Connection connection = DBUtils.getConnection();

			PreparedStatement statement = connection.prepareStatement("delete from user where userid = ?");

			statement.setString(1, userid);

			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println("inside catch of getUser(username) of UserRepository...");
			e.printStackTrace();
		}

		return user;
		
	}

	public void update(User user) {
		
		try {

			Connection connection = DBUtils.getConnection();

			PreparedStatement statement = connection.prepareStatement("update user set name = ?, email = ? where userid = ?");

			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getUserid());

			statement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("inside catch of update of UserRepository...");
			e.printStackTrace();
		}
		
	}
	
	public List<User> getAllUser() {
		
		User user = null;
		
		List<User> userList = new ArrayList<>();

		try {
			
			Connection connection = DBUtils.getConnection();

			PreparedStatement statement = connection.prepareStatement("select * from user where role !='admin' ");

			ResultSet resultSet = statement.executeQuery();
			
			while( resultSet.next()){
				
				String userid = resultSet.getString(1);
				String name = resultSet.getString(2);
				String email = resultSet.getString(3);
				String password = resultSet.getString(4);
				String role = resultSet.getString(5);
				String status = resultSet.getString(6);
				int attempts = resultSet.getInt(7);

				user = new User(userid, name, email, password, role, status, attempts);
				userList.add(user);
			}
			
		} catch (Exception e) {
			
			System.out.println(e);
			System.out.println("inside catch of getAllUsers() of UserRepository...");
		}
		return userList;
	}

	public void blockTheUser(String userid) {
		
		try {

			Connection connection = DBUtils.getConnection();

			PreparedStatement statement = connection.prepareStatement("update user set status = 'blocked' where userid = ?");

			statement.setString(1, userid);

			statement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("inside catch of block of UserRepository...");
			e.printStackTrace();
		}

	}

	public void activateTheUser(String userid) {

		try {

			Connection connection = DBUtils.getConnection();

			PreparedStatement statement = connection
					.prepareStatement("update user set status = 'active' where userid = ?");

			statement.setString(1, userid);

			statement.executeUpdate();
			
			PreparedStatement statement2 = connection.prepareStatement("UPDATE user SET attempts = 0 WHERE userid = ?");

			statement2.setString(1, userid);
			
			statement2.executeUpdate();

		} catch (Exception e) {
			System.out.println("inside catch of active of UserRepository...");
			e.printStackTrace();
		}

	}

	public User getUser(String userid) {
		User user = null;

		try {

			Connection connection = DBUtils.getConnection();

			PreparedStatement statement = connection.prepareStatement("select * from user where userid = ?");

			statement.setString(1, userid);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				String name = resultSet.getString(2);
				String email = resultSet.getString(3);
				String password = resultSet.getString(4);
				String role = resultSet.getString(5);
				String status = resultSet.getString(6);
				int attempts = resultSet.getInt(7);
				
				user = new User(userid, name, email, password, role, status, attempts);
			}
		} catch (Exception e) {
			System.out.println("inside catch of getUser(username) of UserRepository...");
			e.printStackTrace();
		}

		return user;
	}

	public void wrongAttempt(User wrongUser) {
		
		try {

			Connection connection = DBUtils.getConnection();

			PreparedStatement statement = connection.prepareStatement("UPDATE user SET attempts = ? WHERE userid = ?");

			statement.setInt(1, wrongUser.getAttempts()+1);
			statement.setString(2, wrongUser.getUserid());
			
			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println("inside catch of wrongAttempt(user) of UserRepository...");
			e.printStackTrace();
		}
		
	}

	public void rightAttempt(User user) {
		
		try {

			Connection connection = DBUtils.getConnection();

			PreparedStatement statement = connection.prepareStatement("UPDATE user SET attempts = 0 WHERE userid = ?");

			statement.setString(1, user.getUserid());
			
			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println("inside catch of wrongAttempt(user) of UserRepository...");
			e.printStackTrace();
		}
		
	}



}
