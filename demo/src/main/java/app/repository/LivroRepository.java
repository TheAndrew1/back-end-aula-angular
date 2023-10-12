package app.repository;

import app.entity.Livro;
import app.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
