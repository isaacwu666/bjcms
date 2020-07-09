package tomweb.xyz.bjcms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import tomweb.xyz.bjcms.utils.RequestResponseLoggingInterceptor;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@MapperScan({"tomweb.xyz.bjcms.dao"})
public class BjcmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BjcmsApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

//        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(clientHttpRequestFactory()));

//        restTemplate.setMessageConverters(Collections.singletonList(mappingJacksonHttpMessageConverter()));

        restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));

        return restTemplate;
    }
}
