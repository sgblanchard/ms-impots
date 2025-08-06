package cd.dgi.di.notifications;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
public class NotificationsConfiguration {
    private static final Logger log = LoggerFactory.getLogger(NotificationsConfiguration.class);
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
        RestClient clientForData =
                RestClient
                        .builder()
                        .baseUrl(this.dataUrl)
                        .defaultStatusHandler(HttpStatusCode::isError, (request, response) ->  {
                            log.error("{}", request);
                            log.error("{}", response);
                        })
                        .defaultHeaders(
                                httpHeaders -> {
                                    httpHeaders.set("Accept", APPLICATION_JSON_VALUE);
                                })
                        .build();

        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory
                        .builderFor(RestClientAdapter.create(clientForData))
                        .build();
        return httpServiceProxyFactory
                .createClient(DataClient.class)
                ;
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
