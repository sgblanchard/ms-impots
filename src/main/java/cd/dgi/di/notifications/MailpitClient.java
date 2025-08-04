package cd.dgi.di.notifications;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

import java.util.Map;

public interface MailpitClient {
    @PostExchange(
            value="/api/v1/send",
            contentType = MediaType.APPLICATION_JSON_VALUE
    )
    public void send(
            @RequestBody Map<String, Object> body
    );
}
