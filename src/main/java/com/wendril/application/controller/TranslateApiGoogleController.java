package com.wendril.application.controller;

import com.wendril.application.services.TranslateApiGoogleService;
import org.springframework.stereotype.Controller;

@Controller
public class TranslateApiGoogleController {
    private final TranslateApiGoogleService translateApiGoogleService;

    public TranslateApiGoogleController(TranslateApiGoogleService translateApiGoogleService) {
        this.translateApiGoogleService = translateApiGoogleService;
    }

    public String fechTranslate(String pais) {
        return translateApiGoogleService.translateToEnglish(pais);
    }
}
