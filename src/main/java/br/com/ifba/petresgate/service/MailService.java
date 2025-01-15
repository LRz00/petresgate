/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.petresgate.service;

import br.com.ifba.petresgate.domain.Animal;
import br.com.ifba.petresgate.domain.AppUser;
import br.com.ifba.petresgate.exception.EmailNotSentException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author lara
 */
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;


    public void sendAnimalRegisteredEmail(AppUser user, Animal animal) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("🎉 Animal Registrado com Sucesso!");

            String text = String.format(
                    """
                Olá, %s!
                
                Obrigado por contribuir para a proteção dos animais em Irecê! 😊
                
                Detalhes do animal registrado:
                🐾 Espécie: %s
                📍 Local: Rua %s
                
                Caso você precise atualizar as informações do animal no futuro, utilize o seguinte código de confirmação:
                🔑 %s
                
                Sua contribuição faz a diferença na vida de muitos animais. Continue ajudando!
                
                Atenciosamente,
                Equipe PetResgate
                """,
                    user.getFullName(),
                    animal.getSpecies(),
                    animal.getCurrentAddress().getStreet(),
                    user.getConfirmationKey().toString()
            );

            message.setText(text);

            mailSender.send(message);

        } catch (Exception e) {
            throw new EmailNotSentException("Error Sending Email");
        }
    }
    
    public void resendConfirmationKeyEmail(AppUser user){
         try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Chave de confirmação PetResgate!");
            message.setText("Aqui esta sua chave de confirmação para editar os detalhes do animal que voce registrou: " +
                    user.getConfirmationKey().toString());

            mailSender.send(message);

        } catch (Exception e) {
            throw new EmailNotSentException("Error Sending Email");
        }
    }
    
    public void sendAnimalEditedEmail(AppUser user, Animal animal){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Localização do animal editado com sucesso!");
            
            String text = String.format(
                    """
                Olá, %s!
                
                Agradecemos por manter as informações dos animais em nosso sistemas atualizadas. Informamos que as novas informações foram salvas com sucesso!
                
                Detalhes do animal registrado:
                🐾 Espécie: %s
                📍 Local: %s, %s
                
                Caso você precise atualizar as informações do animal novamente no futuro, utilize o seguinte código de confirmação:
                🔑 %s
                
                Sua contribuição faz a diferença na vida de muitos animais. Continue ajudando!
                
                Atenciosamente,
                Equipe PetResgate
                """,
                    user.getFullName(),
                    animal.getSpecies(),
                    animal.getCurrentAddress().getStreet(), animal.getCurrentAddress().getNeighborhood(),
                    user.getConfirmationKey().toString()
            );
            
            message.setText(text);

            mailSender.send(message);
        } catch (Exception e) {
            throw new EmailNotSentException("Error Sending Email");
        }
    }
}
