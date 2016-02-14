/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.sii.library.domain.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pl.sii.library.domain.persistence.Book;
import pl.sii.library.domain.persistence.RentStatus;
import pl.sii.library.service.DateUtils;

@Stateless
public class BookRepositoryImpl implements BookRepository {

    @Inject
    private Logger log;	
	
    @Inject
    private Event<Book> bookEventSrc;	
	
    @PersistenceContext(name = "libraryPU")
    private EntityManager em;

    /* (non-Javadoc)
	 * @see pl.sii.library.data.BookRepository#findById(java.lang.Long)
	 */
    @Override
	public Book findById(Long id) {
        return em.find(Book.class, id);
    }

    /* (non-Javadoc)
	 * @see pl.sii.library.data.BookRepository#findAllBooks()
	 */
    @Override
	public List<Book> findAllBooks() {
        TypedQuery<Book> query = em.createNamedQuery(Book.FIND_ALL, Book.class);
        return query.getResultList();
    }
    
    /* (non-Javadoc)
     * @see pl.sii.library.domain.repository.BookRepository#findAllReservedBooks()
     */
    @Override
    public List<Book> findAllReservedBooks(RentStatus status) {
    	TypedQuery<Book> query = em.createNamedQuery(Book.FIND_RESERVED_BY_STATUS, Book.class);
    	query.setParameter("status", status);
        return query.getResultList();
    }
    
    /* (non-Javadoc)
	 * @see pl.sii.library.data.BookRepository#updateBook(pl.sii.library.model.Book)
	 */
    @Override
	public void updateBook(Book book) {
    	em.merge(book);
    	bookEventSrc.fire(book);
    }
    
    @Override
    public void create(Book book) {
        log.info("Creating a book " + book.getTitle());
        if (book.getId() != null) {
            em.merge(book);
        } else {
            em.persist(book);
        }
        bookEventSrc.fire(book);
    }
    
	@Override
	public List<Book> findAllExpiredBooks() {
    	TypedQuery<Book> query = em.createNamedQuery(Book.FIND_EXPIRED, Book.class);
        return query.getResultList();
	}
    
	@Override
	public List<Book> findAllExpiredIn3Days() {
    	TypedQuery<Book> query = em.createNamedQuery(Book.FIND_EXPIRED_AFTER_DAYS, Book.class);
    	Date compareDate = DateUtils.changeCurrentDateByDays(3);
    	query.setParameter("compareDate", compareDate);
        return query.getResultList();
	}

	@Override
	public List<Book> findAllExpiredBooksAfterWeek() {
    	TypedQuery<Book> query = em.createNamedQuery(Book.FIND_EXPIRED_AFTER_DAYS, Book.class);
    	Date compareDate = DateUtils.changeCurrentDateByDays(-7);
    	query.setParameter("compareDate", compareDate);
        return query.getResultList();
	}    
}

