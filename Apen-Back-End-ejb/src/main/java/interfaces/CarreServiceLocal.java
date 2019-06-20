package interfaces;

import java.util.List;

import javax.ejb.Local;

import persistence.Carre;

@Local
public interface CarreServiceLocal {
	public int add(Carre carre);
	public int update(Carre carre);
	public void delete(Carre carre);
	public List<Carre> getAll();
	public Carre getById(int id);

}
