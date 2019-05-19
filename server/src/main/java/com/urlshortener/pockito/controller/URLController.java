package com.urlshortener.pockito.controller;

import com.urlshortener.pockito.model.URLEntity;
import com.urlshortener.pockito.repository.URLRepository;
import com.urlshortener.pockito.service.URLConverterService;
import org.springframework.web.bind.annotation.*;


@RestController
public class URLController {

    private URLConverterService urlConverterService;
    private URLRepository urlRepository;

    public URLController(URLConverterService urlConverterService) {
        this.urlConverterService = urlConverterService;
    }
    @CrossOrigin
    @GetMapping("/shortener")
    public URLEntity findByShortId(String shortId) {
        return urlRepository.findByShortId(shortId);
    }
    @CrossOrigin
    @PostMapping("/shortener")
    public @ResponseBody
    String shortURL(@RequestParam (value="originalUrl") String original) {
        return urlConverterService.shortenURL("https://pocki.to/", original);
    }
}
