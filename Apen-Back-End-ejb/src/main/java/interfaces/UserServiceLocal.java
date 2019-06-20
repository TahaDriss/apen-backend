package interfaces;

import java.util.List;

import javax.ejb.Local;

import persistence.User;

@Local
public interface UserServiceLocal {
	
	public User getForLogin(String username, String password);
	public User getById(int id);
	public List<User> getAll();
	public void remove(User u);
	public int update(String userName, String oldPassword, String newPassword);
	public int add(User u);

}
