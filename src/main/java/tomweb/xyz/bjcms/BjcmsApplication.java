package tomweb.xyz.bjcms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import tomweb.xyz.bjcms.utils.RequestResponseLoggingInterceptor;

import java.util.Collections;

@SpringBootApplication
public class BjcmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BjcmsApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

//        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(clientHttpRequestFactory()));

//        restTemplate.setMessageConverters(Collections.singletonList(mappingJacksonHttpMessageConverter()));

        restTemplate.setInterceptors( Collections.singletonList(new RequestResponseLoggingInterceptor()) );

        return restTemplate;
    }
}
