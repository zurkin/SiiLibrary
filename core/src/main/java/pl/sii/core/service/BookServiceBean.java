package pl.sii.core.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.sii.core.entity.Book;

/**
 * Created by a050600 on 2015-02-25.
 */
@Stateless
public class BookServiceBean implements BookService {
	private EntityManager entityManager = null;

	@PersistenceContext(unitName = "libPU")
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	@Override
	public List<Book> findAllBooks() {
		final Query query = entityManager.createQuery("select b from Book b");
		return query.getResultList();
	}
}
