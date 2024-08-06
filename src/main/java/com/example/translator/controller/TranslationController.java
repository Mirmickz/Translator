package com.example.translator.controller;

import com.example.translator.service.InternalTranslationService;
import com.example.translator.service.LanguageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.TreeMap;

import static com.example.translator.service.ExternalTranslationService.logger;

/**
 * REST контроллер для управления операциями перевода и получения информации о поддерживаемых языках.
 */
@RestController
@RequestMapping("/api/v1/translation")
@AllArgsConstructor
public class TranslationController {

    private final LanguageService languageService;
    private final InternalTranslationService translationService;

    /**
     * Возвращает список поддерживаемых языков.
     * @return ResponseEntity с TreeMap, содержащей коды и названия языков, или HTTP статусом ошибки.
     */
    @GetMapping("/languages")
    public ResponseEntity<TreeMap<String, String>> getAvailableLanguages() {
        try {
            return ResponseEntity.ok(languageService.getSupportedLanguages());
        } catch (Exception e) {
            logger.error("Не удалось получить доступные языки", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Переводит текст с исходного языка на целевой.
     * @param request   Тело запроса с текстом для перевода и языками.
     * @param ipAddress IP-адрес клиента для мониторинга и логирования.
     * @return ResponseEntity<String> с переведенным текстом или сообщением об ошибке.
     */
    @PostMapping("/translate")
    public ResponseEntity<String> translate(@RequestBody TranslationRequestBody request,
                                            @RequestHeader(value = "X-Forwarded-For", defaultValue = "127.0.0.1") String ipAddress) {
        if (languageService.isNotSupported(request.getSource())) {
            return ResponseEntity.badRequest().body("Source language is not supported");
        }

        if (languageService.isNotSupported(request.getTarget())) {
            return ResponseEntity.badRequest().body("Target language is not supported");
        }

        try {
            String translatedText = translationService.translate(request.getQ(), request.getSource(), request.getTarget(), ipAddress);
            return ResponseEntity.ok(translatedText);
        } catch (Exception e) {
            logger.error("Translation failed", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Служба перевода в настоящее время недоступна");
        }
    }
}