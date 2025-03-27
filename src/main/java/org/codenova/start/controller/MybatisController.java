package org.codenova.start.controller;

import lombok.AllArgsConstructor;
import org.codenova.start.entity.Book;
import org.codenova.start.entity.Movie;
import org.codenova.start.repository.BookRepository;
import org.codenova.start.repository.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/mybatis")
@AllArgsConstructor
public class MybatisController {

    private MovieRepository movieRepository;
    private BookRepository bookRepository;

    @GetMapping("/movie")
    public String movieHandle (Model model) {

        List<Movie> movies = movieRepository.findAll();

        model.addAttribute("movies", movies);

        return "mybatis/movie";
    }

    @GetMapping("/book")
    public String bookHandle(Model model){

        model.addAttribute("books", bookRepository.findAll());

        return "mybatis/book";
    }

    @GetMapping("/book/create")
    public String bookCreateHandle(@ModelAttribute Book book){

        int r = bookRepository.create(book);
        return "redirect:/mybatis/book";
    }

    @GetMapping("/mybatis/book-create")
    public String mybatisBookCreateHandle(Model model) {
        Book book = Book.builder().author("양귀자").title("모순").publisher("쓰다").
                publishedAt(LocalDate.parse("2013-04-01")).build();
        int r =  bookRepository.create(book);
        return "";
    }

}
