package book;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BookDataService {
	
	@Autowired
    private BookRepository bookRepository;
	
    public ResponseEntity<Book> getBookById(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            return ResponseEntity.ok().body(book.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }
    
    
    public ResponseEntity<Book> updateBook(Long bookId,Book bookDetails) {
    	Optional<Book> optionalBook = bookRepository.findById(bookId);
    	if (optionalBook.isPresent()) {
    		Book book = optionalBook.get();
    		book.setTitle(bookDetails.getTitle());
    		book.setAuthor(bookDetails.getAuthor());
    		book.setDescription(bookDetails.getDescription());
    		book.setPrice(bookDetails.getPrice());
    		Book updatedBook = bookRepository.save(book);
    		return ResponseEntity.ok().body(updatedBook);
    	} else {
    		return ResponseEntity.notFound().build();
    		}
    }
    
    public ResponseEntity<Void> deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
        return ResponseEntity.noContent().build();
    }


}
