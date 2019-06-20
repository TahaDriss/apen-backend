package interfaces;

import java.util.List;

import javax.ejb.Local;

import persistence.Slider;


@Local
public interface SliderServiceLocal {
	
	public int add(Slider slider);
	public int update(Slider slider);
	public void delete(Slider slider);
	public List<Slider> getAll();
	public Slider getById(int id);

}
