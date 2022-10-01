package fiap.com.br.sprint3.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import fiap.com.br.sprint3.model.ProdutoModel;

public interface ProdutoRepository extends MongoRepository<ProdutoModel, String> {

}
