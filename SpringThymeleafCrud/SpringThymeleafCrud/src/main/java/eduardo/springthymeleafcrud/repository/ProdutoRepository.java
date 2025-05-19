package eduardo.springthymeleafcrud.repository;

import eduardo.springthymeleafcrud.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
