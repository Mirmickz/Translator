package com.example.translator.controller;

import com.example.translator.service.InternalTranslationService;
import com.example.translator.service.LanguageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TranslationControllerTest {

    @Mock
    private LanguageService languageService;

    @Mock
    private InternalTranslationService translationService;

    @InjectMocks
    private TranslationController translationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAvailableLanguages() {
        TreeMap<String, String> languages = new TreeMap<>();
        languages.put("en", "English");
        when(languageService.getSupportedLanguages()).thenReturn(languages);

        ResponseEntity<TreeMap<String, String>> response = translationController.getAvailableLanguages();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(languages, response.getBody());
    }

    @Test
    void testTranslate() {
        TranslationRequestBody request = new TranslationRequestBody("Hello", "en", "es", "text");
        when(languageService.isNotSupported(anyString())).thenReturn(false);
        when(translationService.translate(anyString(), anyString(), anyString(), anyString())).thenReturn("Hola");

        ResponseEntity<String> response = translationController.translate(request, "127.0.0.1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hola", response.getBody());
    }

    @Test
    void testTranslateSourceLanguageNotSupported() {
        TranslationRequestBody request = new TranslationRequestBody("Hello", "en", "es", "text");
        when(languageService.isNotSupported("en")).thenReturn(true);

        ResponseEntity<String> response = translationController.translate(request, "127.0.0.1");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Source language is not supported", response.getBody());
    }

    @Test
    void testTranslateTargetLanguageNotSupported() {
        TranslationRequestBody request = new TranslationRequestBody("Hello", "en", "es", "text");
        when(languageService.isNotSupported("en")).thenReturn(false);
        when(languageService.isNotSupported("es")).thenReturn(true);

        ResponseEntity<String> response = translationController.translate(request, "127.0.0.1");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Target language is not supported", response.getBody());
    }
}