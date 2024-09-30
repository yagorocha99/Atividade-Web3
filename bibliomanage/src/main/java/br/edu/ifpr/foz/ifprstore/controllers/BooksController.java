package br.edu.ifpr.foz.ifprstore.controllers;

import br.edu.ifpr.foz.ifprstore.models.Author;
import br.edu.ifpr.foz.ifprstore.models.Book;
import br.edu.ifpr.foz.ifprstore.models.BookStatus;
import br.edu.ifpr.foz.ifprstore.repositories.AuthorRepository;
import br.edu.ifpr.foz.ifprstore.repositories.BookRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = {"/books", "/books/create", "/books/update", "/books/delete"})
public class BooksController extends HttpServlet {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public BooksController() {
        this.bookRepository = new BookRepository();
        this.authorRepository = new AuthorRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/books/create":
                showCreateForm(req, resp);
                break;
            case "/books/update":
                showUpdateForm(req, resp);
                break;
            case "/books/delete":
                deleteBook(req, resp);
                break;
            default:
                listBooks(req, resp);
                break;
        }
    }

    private void listBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookRepository.getBooks();
        req.setAttribute("books", books);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/books.jsp");
        dispatcher.forward(req, resp);
    }

    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = authorRepository.getAll();
        req.setAttribute("authors", authors);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/books-create.jsp");
        dispatcher.forward(req, resp);
    }

    private void showUpdateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        List<Author> authors = authorRepository.getAll();
        req.setAttribute("authors", authors);

        Book book = bookRepository.getById(id);
        req.setAttribute("book", book);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/books-update.jsp");
        dispatcher.forward(req, resp);
    }

    private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        bookRepository.delete(id);
        resp.sendRedirect(req.getContextPath() + "/books");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/books/create":
                createBook(req, resp);
                break;
            case "/books/update":
                updateBook(req, resp);
                break;
        }
    }

    private void createBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("field_name");
        LocalDate birthDate = LocalDate.parse(req.getParameter("field_birthDate"));
        Integer authorId = Integer.valueOf(req.getParameter("field_author"));
        String statusString = req.getParameter("field_status");

        Author author = new Author();
        author.setId(authorId);

        BookStatus status = BookStatus.valueOf(statusString.toUpperCase());

        Book book = new Book();
        book.setName(name);
        book.setDate(birthDate);
        book.setAuthor(author);
        book.setStatus(status);

        bookRepository.insert(book);
        resp.sendRedirect(req.getContextPath() + "/books");
    }

    private void updateBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.valueOf(req.getParameter("field_id"));
        String name = req.getParameter("field_name");
        LocalDate birthDate = LocalDate.parse(req.getParameter("field_birthDate"));
        Integer authorId = Integer.valueOf(req.getParameter("field_author"));
        String statusString = req.getParameter("field_status");

        Author author = new Author();
        author.setId(authorId);

        BookStatus status = BookStatus.valueOf(statusString.toUpperCase());

        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setDate(birthDate);
        book.setAuthor(author);
        book.setStatus(status);

        bookRepository.update(book);
        resp.sendRedirect(req.getContextPath() + "/books");
    }
}
