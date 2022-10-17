package microservico.email.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import microservico.email.models.EmailEnviadoModel;

public interface EmailEnviadoRepository extends MongoRepository<EmailEnviadoModel, String>{

	@Query("{dataEnvio: '?0'}")
	Optional<EmailEnviadoModel> obterPorData(String data);
}
