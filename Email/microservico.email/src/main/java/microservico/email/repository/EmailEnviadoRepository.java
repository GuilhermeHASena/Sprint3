package microservico.email.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import microservico.email.models.EmailEnviadoModel;

public interface EmailEnviadoRepository extends MongoRepository<EmailEnviadoModel, String>{

}
