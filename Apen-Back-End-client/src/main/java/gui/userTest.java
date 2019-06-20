package gui;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import interfaces.UserServiceRemote;
import persistence.User;
import util.PasswordUtils;

public class userTest {

	public static void main(String[] args) throws NamingException, IOException {
		// TODO Auto-generated method stub
				InitialContext cx= new InitialContext();
				UserServiceRemote proxyUser =(UserServiceRemote)cx.lookup("Apen-Back-End-ear/Apen-Back-End-ejb/UserService!interfaces.UserServiceRemote");
		
				
				/*User u = new User();
				u.setEmail("test");
				u.setUsername("test");
				u.setPassword("test");
				
				proxyUser.add(u);*/
				
				//User u =proxyUser.getForLogin("a", "a");
				
				//System.out.println(u.getUsername());
				
				User u = proxyUser.getById(4);
				System.out.println(u.getUsername());
				boolean passwordMatch = PasswordUtils.verifyUserPassword("adminn", "16mGz+ZHJIvlCGwBquWMKT67tik/1F0K4Yum9HeiicM=", "4nBTMdSUl9RHRWgPVRDry12O4JMiEp");
				System.out.println(passwordMatch);
				
				

	}

}
