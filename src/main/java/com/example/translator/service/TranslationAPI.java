package com.example.translator.service;

/**
 * Интерфейс для определения контракта перевода текста с использованием внешнего API.
 */
public interface TranslationAPI {

    /**
     * Метод для перевода слова с одного языка на другой.
     *
     * @param word Слово для перевода.
     * @param sourceLang Исходный язык.
     * @param targetLang Целевой язык.
     * @return Переведенное слово.
     */
    String translateWord(String word, String sourceLang, String targetLang);
}