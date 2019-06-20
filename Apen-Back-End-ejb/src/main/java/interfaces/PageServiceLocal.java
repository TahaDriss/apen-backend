package interfaces;

import java.util.List;

import javax.ejb.Local;

import persistence.Page;


@Local
public interface PageServiceLocal {
	
	public int add(Page page);
	public int update(Page page);
	public void delete(Page page);
	public List<Page> getAll();
	public Page getById(int id);

}

