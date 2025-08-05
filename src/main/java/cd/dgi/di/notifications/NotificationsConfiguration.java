package cd.dgi.di.notifications;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class NotificationsConfiguration {
    private final String mailpitUrl;
    private final String dataUrl;

    public NotificationsConfiguration(
            @Value("${providers.mails.endpoint:}") String mailpitUrl,
            @Value("${providers.data.endpoint:}") String dataUrl
    ) {
        this.mailpitUrl = mailpitUrl;
        this.dataUrl = dataUrl;
    }
    @Bean
    public DataClient dataClient() {
        RestClient client = RestClient.create(dataUrl);
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory
                        .builderFor(RestClientAdapter.create(client))
                        .build();
        return httpServiceProxyFactory
                .createClient(DataClient.class);
    }
    @Bean
    public MailpitClient mailpitClient() {
        RestClient client = RestClient.create(mailpitUrl);
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory
                    .builderFor(RestClientAdapter.create(client))
                    .build();
        return httpServiceProxyFactory
                .createClient(MailpitClient.class);
    }



}
