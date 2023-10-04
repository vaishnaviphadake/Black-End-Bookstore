package com.hibernateJPAMapping.StudentDB.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hibernateJPAMapping.StudentDB.Entity.Book;
import com.hibernateJPAMapping.StudentDB.Entity.Student;
import com.hibernateJPAMapping.StudentDB.Repository.BookRepository;

@RestController
public class BookController {
		@Autowired 
		private BookRepository bookRepository;
		
		@GetMapping("/books")
		public List<Book> getAllBooks(){
			return bookRepository.findAll();
		}
		
		@GetMapping("/bookId/{id}")
		public Optional<Book> getBookById(@PathVariable Long id){
			return bookRepository.findById(id);
		}
		
		@GetMapping("/bookName/{name}")
		public List<Book> getBookByName(@PathVariable String name){
			return bookRepository.findByName(name);
		}
		
		@GetMapping("/bookAuthor/{author}")
		public List<Book> getBookByAuthor(@PathVariable String author){
			return bookRepository.findByAuthor(author);
		}
		
		@PostMapping("/book")
		public Book addBook(@RequestBody Book book) {
			return bookRepository.save(book);
		}
	
		@PostMapping("/books")
		public List<Book> addBooks(@RequestBody List<Book> books) {
			return bookRepository.saveAll(books);
		}


		@PutMapping("/book/{id}")
		public Book updateBook(@PathVariable Long id,  @RequestBody Book book) {
				if(!bookRepository.existsById(id)) {
					return  null;
				}
			  Book b1 = bookRepository.getById(id);
			  b1.setName(book.getName());
			  b1.setPages(book.getPages());
			  b1.setAuthor(book.getAuthor());
			  b1.setPrice(book.getPrice());
			 
			  bookRepository.save(b1);
			  return book;
			  
		}


		@DeleteMapping("/book/{id}")
		public String deleteBook(@PathVariable Long id) {
			Book b1 = bookRepository.getById(id);
			 bookRepository.delete(b1);
			 return "Book deleted";
		}
		
		@DeleteMapping("/books")
		public String deleteAllBooks() {
			
			 bookRepository.deleteAll();
			 return "All books are sold out !!!";
		}

}
