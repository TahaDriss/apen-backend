package interfaces;

import java.util.List;

import javax.ejb.Remote;

import persistence.Carre;

@Remote
public interface CarreServiceRemote {
	
	public int add(Carre carre);
	public int update(Carre carre);
	public void delete(Carre carre);
	public List<Carre> getAll();
	public Carre getById(int id);

}
