package microservico.email.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservico.email.models.EmailTemplate;
import microservico.email.models.EmailEnviadoModel;
import microservico.email.models.EmailModel;
import microservico.email.repository.EmailEnviadoRepository;
import microservico.email.repository.TemplateEmailRepository;

@RestController
@RequestMapping("/v1/email")
public class EmailController {
    @Autowired
    private TemplateEmailRepository templateRepository;
    
    @Autowired
    private EmailEnviadoRepository emailEnviadoRepository;
    @Autowired
    private JavaMailSender emailSender;
    
    @PostMapping
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<EmailEnviadoModel> enviar(@RequestBody EmailModel model) {
        if (model.getDados() == null) {
            return new ResponseEntity("É necessário informar o dicionário de dados.", HttpStatus.BAD_REQUEST);
        }
        Optional<EmailTemplate> templateExistente = templateRepository.obterPorChave(model.getChave());
        if (!templateExistente.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        for (String chave : model.getDados().keySet()) {
        	System.out.println(model.getDados().get(chave));
            templateExistente.get().enriquecerEmail(chave, model.getDados().get(chave));
        }
        EmailModel e = new EmailEnviadoModel();
        EmailEnviadoModel emailEnviado = (EmailEnviadoModel) e;
        emailEnviado.setAssuntoEmail(templateExistente.get().getAssunto());
        emailEnviado.setCorpoEmail(templateExistente.get().getCorpoEmail());
        emailEnviado.setDestinatarios(model.getDestinatarios());
        emailEnviado.setDestinatariosCopia(model.getDestinatariosCopia());
        emailEnviado.setDataEnvio(java.time.LocalDateTime.now().toString());
        
        SimpleMailMessage emailMessage = new SimpleMailMessage();
        
        String[] destinatarios = new String[emailEnviado.getDestinatarios().size()];
        destinatarios = emailEnviado.getDestinatarios().toArray(destinatarios);
        
        String[] destinatariosCopia = new String[emailEnviado.getDestinatariosCopia().size()];
        destinatariosCopia = emailEnviado.getDestinatariosCopia().toArray(destinatariosCopia);
        try {
            emailMessage.setFrom(templateExistente.get().getRemetente());
            emailMessage.setTo(destinatarios);
            emailMessage.setCc(destinatariosCopia);
            emailMessage.setSubject(emailEnviado.getAssuntoEmail());
            emailMessage.setText(emailEnviado.getCorpoEmail());
            emailSender.send(emailMessage);
        } catch (MailException ex) {
            emailEnviado.setEnviado(false);
            emailEnviadoRepository.save(emailEnviado);
            return new ResponseEntity<EmailEnviadoModel>(emailEnviado, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        emailEnviadoRepository.save(emailEnviado);
        return new ResponseEntity(HttpStatus.OK);
    }
}
