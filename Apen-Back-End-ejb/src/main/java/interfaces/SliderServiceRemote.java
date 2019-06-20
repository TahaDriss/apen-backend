package interfaces;

import java.util.List;

import javax.ejb.Remote;

import persistence.Slider;


@Remote
public interface SliderServiceRemote {
	
	public int add(Slider slider);
	public int update(Slider slider);
	public void delete(Slider slider);
	public List<Slider> getAll();
	public Slider getById(int id);

}
