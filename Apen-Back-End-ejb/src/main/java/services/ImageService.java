package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import interfaces.ImageServiceLocal;
import interfaces.ImageServiceRemote;
import persistence.Image;
import persistence.Paragraphe;

@Stateless
@LocalBean
public class ImageService implements ImageServiceLocal , ImageServiceRemote {
	@PersistenceContext(unitName="Apen-Back-End-ejb")
	EntityManager em;

	@Override
	public int add(Image image) {
		em.persist(image);
		return image.getId();
	}

	@Override
	public int update(Image image) {
		em.merge(image);
		return image.getId();
	}

	@Override
	public void delete(Image image) {
		Image i  =  em.find(Image.class, image.getId());
		em.remove(i);
	}

	@Override
	public List<Image> getAll() {
		 System.out.println("req num 1) SELECT p FROM Image p");

		 TypedQuery<Image> query = em.createQuery("SELECT p FROM Image p", Image.class);
		 return (List<Image>) query.getResultList();
	}

	@Override
	public Image getById(int id) {
		
		return em.find(Image.class, id);
	}

}
