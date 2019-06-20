package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import interfaces.CarreServiceLocal;
import interfaces.CarreServiceRemote;
import persistence.Carre;
import persistence.Paragraphe;

@Stateless
@LocalBean
public class CarreService implements CarreServiceLocal, CarreServiceRemote {
	@PersistenceContext(unitName="Apen-Back-End-ejb")
	EntityManager em;

	@Override
	public int add(Carre carre) {

		em.persist(carre);
		return carre.getId();
	}

	@Override
	public int update(Carre carre) {
		em.merge(carre);
		return carre.getId();
	}

	@Override
	public void delete(Carre carre) {
		Carre c = em.find(Carre.class, carre.getId());
		em.remove(c);
		
	}

	@Override
	public List<Carre> getAll() {
		 System.out.println("req num 1) SELECT p FROM Carre p");

		 TypedQuery<Carre> query = em.createQuery("SELECT p FROM Carre p", Carre.class);
		 return (List<Carre>) query.getResultList();
	}

	@Override
	public Carre getById(int id) {
		return em.find(Carre.class, id);
	}

}
