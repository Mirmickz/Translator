package com.example.translator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

/**Конфигурационный класс для настройки RestTemplate и включения механизма повторных попыток.
 */
@Configuration
@EnableRetry
public class RestTemplateConfig {

    /**Создает и настраивает экземпляр RestTemplate.
     RestTemplate используется для выполнения HTTP-запросов и является частью Spring Web.
     @return Настроенный экземпляр RestTemplate.
     **/
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}