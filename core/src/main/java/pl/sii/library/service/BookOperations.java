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
 
package pl.sii.library.service;

import pl.sii.library.domain.persistence.Book;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class BookOperations {

    @Inject
    private Logger log;

    @PersistenceContext(name = "libraryPU")
    private EntityManager em;

    @Inject
    private Event<Book> bookEventSrc;

    public void create(Book book) {
        log.info("Creating a book " + book.getTitle());
        if (book.getId() != null) {
            em.merge(book);
        } else {
            em.persist(book);
        }
        bookEventSrc.fire(book);
    }

}
*/