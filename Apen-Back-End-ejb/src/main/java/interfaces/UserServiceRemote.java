package interfaces;

import java.util.List;

import javax.ejb.Remote;

import persistence.User;

@Remote
public interface UserServiceRemote {
	
	public User getForLogin(String username, String password);
	public User getById(int id);
	public List<User> getAll();
	public void remove(User u);
	public int update(String userName, String oldPassword, String newPassword);
	public int add(User u);

}
