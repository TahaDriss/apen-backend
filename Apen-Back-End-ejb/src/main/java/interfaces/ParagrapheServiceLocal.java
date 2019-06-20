package interfaces;

import java.util.List;

import javax.ejb.Local;

import persistence.Paragraphe;


@Local
public interface ParagrapheServiceLocal {
	public int add(Paragraphe paragraphe);
	public int update(Paragraphe paragraphe);
	public void delete(Paragraphe paragraphe);
	public List<Paragraphe> getAll();
	public Paragraphe getById(int id);

}
