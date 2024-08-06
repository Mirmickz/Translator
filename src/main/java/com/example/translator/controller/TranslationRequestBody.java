package com.example.translator.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс, представляющий тело запроса для операции перевода.
 * Содержит поля для переводимого текста, исходного и целевого языков, а также формата текста.
 */
@Getter
@Setter
@AllArgsConstructor
public class TranslationRequestBody {
    private String q; // Переводимое слово текста
    private String source; // Исходный язык
    private String target; // Нужный язык
    private String format; // Формат текста
}