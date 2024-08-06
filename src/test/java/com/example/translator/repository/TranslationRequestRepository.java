package com.example.translator.repository;

import com.example.translator.model.TranslationRequests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class TranslationRequestRepositoryTest {

    @Autowired
    private TranslationRequestRepository translationRequestRepository;

    @Test
    void testSaveAndRetrieveTranslationRequest() {
        TranslationRequests request = new TranslationRequests();
        request.setIpAddress("127.0.0.1");
        request.setInputString("Hello");
        request.setTranslatedString("Hola");
        request.setRequestTime(LocalDateTime.now());

        translationRequestRepository.save(request);

        TranslationRequests retrievedRequest = translationRequestRepository.findById(request.getId()).orElse(null);

        assertEquals(request.getIpAddress(), retrievedRequest.getIpAddress());
        assertEquals(request.getInputString(), retrievedRequest.getInputString());
        assertEquals(request.getTranslatedString(), retrievedRequest.getTranslatedString());
        assertEquals(request.getRequestTime(), retrievedRequest.getRequestTime());
    }
}