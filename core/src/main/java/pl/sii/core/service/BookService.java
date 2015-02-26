package pl.sii.core.service;

import java.util.List;

import javax.ejb.Local;

import pl.sii.core.entity.Book;

/**
 * Created by a050600 on 2015-02-25.
 */
@Local
public interface BookService {

	public List<Book> findAllBooks();


}
