package com.urlshortener.pockito.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.urlshortener.pockito.model.URLEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import com.urlshortener.pockito.common.URLValidator;
import com.urlshortener.pockito.service.URLConverterService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;


@RestController
public class URLController {
    private static final Logger LOGGER = LoggerFactory.getLogger(URLController.class);
    private final URLConverterService urlConverterService;

    public URLController(URLConverterService urlConverterService) {
        this.urlConverterService = urlConverterService;
    }

//    @RequestMapping(value = "/shortener", method = RequestMethod.POST, consumes = {"application/x-www-form-urlencoded"})
//    public String shortenUrl(@RequestBody @Valid final ShortenRequest shortenRequest, HttpServletRequest request) throws Exception {
//        LOGGER.info("Received url to shorten: " + shortenRequest.getUrl());
//        String longUrl = shortenRequest.getUrl();
//        if (URLValidator.INSTANCE.validateURL(longUrl)) {
//            String localURL = request.getRequestURL().toString();
//            String shortenedUrl = urlConverterService.shortenURL(localURL, shortenRequest.getUrl());
//            LOGGER.info("Shortened url to: " + shortenedUrl);
//            return shortenedUrl;
//        }
//        throw new Exception("Please enter a valid URL");
//    }


    @RequestMapping(value = "/shortener", method = RequestMethod.POST)
    public @ResponseBody
    String shortenUrl(@RequestParam(value = "originalUrl") String originalUrl) throws Exception {
//        String shortenedUrl = urlConverterService.shortenURL(url, url);

        LOGGER.info("Received url to shorten: " + originalUrl);
        String longUrl = originalUrl;
        if (URLValidator.INSTANCE.validateURL(longUrl)) {
            String localURL = "pocki.to";
            String shortenedUrl = urlConverterService.shortenURL(localURL, originalUrl);
            LOGGER.info("Shortened url to: " + shortenedUrl);
            return shortenedUrl;
        }
        throw new Exception("Please enter a valid URL");
    }


//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public RedirectView redirectUrl(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException, Exception {
//        LOGGER.info("Received shortened url to redirect: " + id);
//        String redirectUrlString = urlConverterService.getLongURLFromID(id);
//        LOGGER.info("Original URL: " + redirectUrlString);
//        RedirectView redirectView = new RedirectView();
//        redirectView.setUrl("http://" + redirectUrlString);
//        return redirectView;
//    }


    class ShortenRequest {
        private String url;

        @JsonCreator
        public ShortenRequest() {

        }

        @JsonCreator
        public ShortenRequest(@JsonProperty("url") String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
