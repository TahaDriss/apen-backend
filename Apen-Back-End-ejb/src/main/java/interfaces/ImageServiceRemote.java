package interfaces;

import java.util.List;

import javax.ejb.Remote;

import persistence.Image;

@Remote
public interface ImageServiceRemote {

	public int add(Image image);
	public int update(Image image);
	public void delete(Image image);
	public List<Image> getAll();
	public Image getById(int id);

}