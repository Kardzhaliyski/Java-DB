package bookshopsystem.services.bookshop;

import bookshopsystem.models.bookshop.Author;
import bookshopsystem.repositories.bookshop.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getRandomAuthor() {
        long authorCount = authorRepository.count();
        int randomAuthorNumber = (int) (Math.random() * authorCount);
        Page<Author> authorPage = authorRepository.findAll(PageRequest.of(randomAuthorNumber, 1));

        if (authorPage.hasContent()) {
            return authorPage.getContent().get(0);
        }

        return null;
    }

    @Override
    public void seedDatabase(Path path) throws IOException {
        Files.readAllLines(path).forEach(line -> {
            if (line.isBlank()) {
                return;
            }

            Author author;

            String[] data = line.split(" ");
            if (data.length == 1) {
                author = new Author(data[0]);
            } else {
                author = new Author(data[0], data[1]);
            }

            authorRepository.save(author);
        });
    }

    @Override
    public List<Author> getAuthorsWithBooksReleasedBefore(int year) {
        return authorRepository.findAllDistinctByBooksReleaseDateBefore(LocalDate.of(year, 1, 1));
    }

    public List<Author> getAllOrderedByBooksCount() {
        return authorRepository.findAllOrderedByBooksCount();
    }

}
