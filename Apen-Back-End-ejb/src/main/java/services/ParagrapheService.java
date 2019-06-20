package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import interfaces.ParagrapheServiceLocal;
import interfaces.ParagrapheServiceRemote;
import persistence.Paragraphe;

@Stateless
@LocalBean
public class ParagrapheService implements ParagrapheServiceRemote, ParagrapheServiceLocal{
	@PersistenceContext(unitName="Apen-Back-End-ejb")
	EntityManager em;
	
	@Override
	public int add(Paragraphe paragraphe) {
		em.persist(paragraphe);
		return paragraphe.getId();
	}

	@Override
	public int update(Paragraphe paragraphe) {
		Paragraphe p = em.find(Paragraphe.class, paragraphe.getId());
		paragraphe.setPage(p.getPage());
		paragraphe.setImages(p.getImages());
		em.merge(paragraphe);
		return paragraphe.getId();
	}

	@Override
	public void delete(Paragraphe paragraphe) {
	Paragraphe p =	em.find(Paragraphe.class, paragraphe.getId());
	em.remove(p);
	}

	@Override
	public List<Paragraphe> getAll() {
		 System.out.println("req num 1) SELECT p FROM Paragraphe p");

		 TypedQuery<Paragraphe> query = em.createQuery("SELECT p FROM Paragraphe p", Paragraphe.class);
		 return (List<Paragraphe>) query.getResultList();
	}

	@Override
	public Paragraphe getById(int id) {
		// TODO Auto-generated method stub
		return em.find(Paragraphe.class, id);
	}

}
