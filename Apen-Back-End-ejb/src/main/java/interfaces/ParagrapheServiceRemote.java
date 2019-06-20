package interfaces;

import java.util.List;

import javax.ejb.Remote;

import persistence.Paragraphe;

@Remote
public interface ParagrapheServiceRemote {
	public int add(Paragraphe paragraphe);
	public int update(Paragraphe paragraphe);
	public void delete(Paragraphe paragraphe);
	public List<Paragraphe> getAll();
	public Paragraphe getById(int id);

}
