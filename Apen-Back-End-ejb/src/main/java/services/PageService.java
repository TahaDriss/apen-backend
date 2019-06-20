package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import interfaces.PageServiceLocal;
import interfaces.PageServiceRemote;
import persistence.Page;

@Stateless
@LocalBean
public class PageService implements PageServiceLocal, PageServiceRemote {
	@PersistenceContext(unitName="Apen-Back-End-ejb")
	EntityManager em;

	@Override
	public int add(Page page) {
		em.persist(page);
		return page.getId();
		}

	@Override
	public int update(Page page) {
		em.merge(page);
		return page.getId();
	}

	@Override
	public void delete(Page page) {
		Page p = em.find(Page.class, page.getId());
		em.remove(p);
		
	}

	@Override
	public List<Page> getAll() {
		 System.out.println("req num 1) SELECT p FROM Page p");

		 TypedQuery<Page> query = em.createQuery("SELECT p FROM Page p", Page.class);
		 return (List<Page>) query.getResultList();
	}

	@Override
	public Page getById(int id) {
		
		return em.find(Page.class, id);
	}

}
