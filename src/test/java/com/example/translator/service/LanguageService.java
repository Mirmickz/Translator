package com.example.translator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LanguageServiceTest {

    @InjectMocks
    private LanguageService languageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSupportedLanguages() {
        TreeMap<String, String> languages = languageService.getSupportedLanguages();
        assertFalse(languages.isEmpty());
    }

    @Test
    void testIsNotSupported() {
        assertFalse(languageService.isNotSupported("en"));
        assertTrue(languageService.isNotSupported("unknown"));
    }
}