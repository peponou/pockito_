package com.urlshortener.pockito.controller;

import com.urlshortener.pockito.common.URLValidator;
import com.urlshortener.pockito.model.URLEntity;
import com.urlshortener.pockito.repository.URLRepository;
import com.urlshortener.pockito.service.URLConverterService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;

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
    String shortURL(@RequestBody String original) {
        return urlConverterService.shortenURL("https://pocki.to/", original);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin
    public RedirectView redirectUrl(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException, Exception {
        String redirectUrlString = urlConverterService.getLongURLFromID(id);
        RedirectView redirectView = new RedirectView();
        if (!URLValidator.INSTANCE.checkIfStartsWith(redirectUrlString)) {
            redirectView.setUrl("https://" + redirectUrlString);
        } else {
            redirectView.setUrl(redirectUrlString);
        }
        return redirectView;
    }
}
