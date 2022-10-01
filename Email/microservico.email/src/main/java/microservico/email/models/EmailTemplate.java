package microservico.email.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("templates")
public class EmailTemplate {
	

	@Id
	private String id;
	
	private String chave;
	
	private String assunto;
	private String corpoEmail;
	private String remetente;	
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getCorpoEmail() {
		return corpoEmail;
	}
	public void setCorpoEmail(String corpoEmail) {
		this.corpoEmail = corpoEmail;
	}
	public String getRemetente() {
		return remetente;
	}
	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}
	
	public void enriquecerEmail(String chave,String valor) {
		
		this.assunto = this.assunto.replace("<"+chave+">", valor);
		this.corpoEmail = this.corpoEmail.replace("<"+chave+">", valor);
	}

}
