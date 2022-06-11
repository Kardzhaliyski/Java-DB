package bookshopsystem.repositories;

import bookshopsystem.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Category, Long> {
}
