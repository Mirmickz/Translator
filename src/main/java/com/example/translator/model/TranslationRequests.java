package com.example.translator.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Модель данных для хранения информации о запросах на перевод.
 * Используется для сохранения истории переводов и анализа активности пользователей.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("translation_requests")
public class TranslationRequests {

    @Id
    private Long id; // Уникальный идентификатор записи
    private String ipAddress; // IP-адрес пользователя, сделавшего запрос
    private String inputString; // Исходный текст, который был переведен
    private String translatedString; // Результат перевода
    private LocalDateTime requestTime; // Дата и время, когда был сделан запрос
}