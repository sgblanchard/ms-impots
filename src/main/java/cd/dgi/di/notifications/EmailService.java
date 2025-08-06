package cd.dgi.di.notifications;

import cd.dgi.di.comptes.Code;
import cd.dgi.di.profiles.Profil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Component
public class EmailService {
    private final MailpitClient mailpitClient;

    public void send(Profil profil, Code code) {
       Map<String, Object> parameters = Map.of(
                "To", List.of(Map.of("Name", profil.getPrenom(), "Email", code.getProfil().getEmail())),
                "From", Map.of("Name", "Service des impots", "Email", "no-reply@impots.cd"),
                "Subject", String.format("Votre code d'activation de %s", code.getValeur()),
                "Text", String.format("Votre code d'activation de %s", code.getValeur()),
                "HTML", String.format("Votre code d'activation de %s", code.getValeur())
        );
        this.mailpitClient.send(parameters);
    }
}
