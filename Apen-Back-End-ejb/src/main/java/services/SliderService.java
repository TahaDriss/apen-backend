package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import interfaces.SliderServiceLocal;
import interfaces.SliderServiceRemote;
import persistence.Image;
import persistence.Slider;

@Stateless
@LocalBean
public class SliderService implements SliderServiceLocal, SliderServiceRemote {
	
	@PersistenceContext(unitName="Apen-Back-End-ejb")
	EntityManager em;

	@Override
	public int add(Slider slider) {
		em.persist(slider);
		return slider.getId();
	}

	@Override
	public int update(Slider slider) {
		em.merge(slider);
		return slider.getId();
	}

	@Override
	public void delete(Slider slider) {
		Slider s = em.find(Slider.class, slider.getId());
		em.remove(s);
		
	}

	@Override
	public List<Slider> getAll() {
		 System.out.println("req num 1) SELECT p FROM Slider p");

		 TypedQuery<Slider> query = em.createQuery("SELECT p FROM Slider p", Slider.class);
		 return (List<Slider>) query.getResultList();
	}

	@Override
	public Slider getById(int id) {
		return em.find(Slider.class, id);
	}
	

}
