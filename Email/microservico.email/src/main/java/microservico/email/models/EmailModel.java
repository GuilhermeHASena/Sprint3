package microservico.email.models;

import java.util.HashMap;
import java.util.List;


public class EmailModel {
	
	protected String chave;
	protected List<String> destinatarios;
	protected List<String> destinatariosCopia;
	protected HashMap<String,String> dados;
	
	
	public String getChave() {
		return chave;
	}
	public void setChave(String id) {
		this.chave = id;
	}
	public List<String> getDestinatarios() {
		return destinatarios;
	}
	public void setDestinatarios(List<String> destinatarios) {
		this.destinatarios = destinatarios;
	}
	public List<String> getDestinatariosCopia() {
		return destinatariosCopia;
	}
	public void setDestinatariosCopia(List<String> destinatariosCopia) {
		this.destinatariosCopia = destinatariosCopia;
	}
	public HashMap<String, String> getDados() {
		return dados;
	}
	public void setDados(HashMap<String, String> dados) {
		this.dados = dados;
	}
	
}
