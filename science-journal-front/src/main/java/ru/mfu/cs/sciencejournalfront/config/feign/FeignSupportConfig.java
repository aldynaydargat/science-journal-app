package ru.mfu.cs.sciencejournalfront.config.feign;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class FeignSupportConfig {
    @Bean
    public Encoder multipartFormEncoder() {
        List<HttpMessageConverter<?>> messageConverters = new RestTemplate().getMessageConverters();
        messageConverters.add(new MappingJackson2HttpMessageConverter());

        return new SpringFormEncoder(new SpringEncoder(() ->
                new HttpMessageConverters(messageConverters))
        );
    }
}