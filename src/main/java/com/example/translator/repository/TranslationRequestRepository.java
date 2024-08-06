package com.example.translator.repository;

import com.example.translator.model.TranslationRequests;
import org.springframework.data.repository.CrudRepository;

/**
 * Репозиторий для управления данными запросов на перевод.
 * Расширяет CrudRepository, что предоставляет основные операции CRUD (создание, чтение, обновление, удаление).
 */
public interface TranslationRequestRepository extends CrudRepository<TranslationRequests, Long> {
}