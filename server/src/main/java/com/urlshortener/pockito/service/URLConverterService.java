package com.urlshortener.pockito.service;

import com.urlshortener.pockito.common.IDConverter;
import com.urlshortener.pockito.common.URLValidator;
import com.urlshortener.pockito.model.URLEntity;
import com.urlshortener.pockito.repository.URLRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

@Service
public class URLConverterService {
    private static final Logger LOGGER = LoggerFactory.getLogger(URLConverterService.class);

    @Autowired
    private final URLRepository urlRepository;

    @Autowired
    public URLConverterService(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenURL(String localURL, String originalUrl) {
        LOGGER.info("Shortening {}", originalUrl);
        URLEntity urlEntity = new URLEntity();
        String response = new String();
        String shortId = new String();
        if (!urlRepository.existsByOriginalUrl(originalUrl)) {
            try {
                URL url = new URL(originalUrl);
//                if (URLValidator.INSTANCE.validateURL(originalUrl)) {
                    Long seq = urlRepository.findFirstByOrderBySeqDesc().getSeq();
                    shortId = IDConverter.INSTANCE.createUniqueID(seq);
                    urlEntity.setOriginalUrl(url.toString());
//                    urlEntity.setOriginalUrl(originalUrl);
                    urlEntity.setShortId(shortId);
                    urlEntity.setSeq(seq + 1);
                    urlRepository.save(urlEntity);
//                } else {
//                    response = "Unable to shorten that link. It is not a valid url.";
//                }
            } catch (MalformedURLException e) {
                response = "Unable to shorten that link. It is not a valid url.";
            }
        }
        else {
            shortId = (urlRepository.findByOriginalUrl(originalUrl)).getShortId();
        }
//        String baseString = formatLocalURLFromShortener(localURL);
        if (response.equals("")) {
            response = localURL + shortId;
        }
        return response;
    }

    public String getLongURLFromID(String shortId) throws Exception {
        Long seq = IDConverter.getDictionaryKeyFromUniqueID(shortId);
        String originalUrl = (urlRepository.findByShortId(shortId)).getOriginalUrl();
        LOGGER.info("Converting shortened URL back to {}", originalUrl);
        return originalUrl;
    }

    private String formatLocalURLFromShortener(String localURL) {
        String[] addressComponents = localURL.split("/");
        // remove the endpoint (last index)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < addressComponents.length - 1; ++i) {
            sb.append(addressComponents[i]);
        }
        sb.append('/');
        return sb.toString();
    }}
