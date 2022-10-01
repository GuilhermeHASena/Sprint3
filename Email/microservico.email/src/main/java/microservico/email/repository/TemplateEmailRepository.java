package microservico.email.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import microservico.email.models.EmailTemplate;


public interface TemplateEmailRepository extends MongoRepository<EmailTemplate, String> {

	@Query("{chave: '?0'}")
	Optional<EmailTemplate> obterPorChave(String chave);
}
