package com.urlshortener.pockito.service;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.urlshortener.pockito.common.IDConverter;
import com.urlshortener.pockito.model.URLEntity;
import com.urlshortener.pockito.repository.URLRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        URLEntity urlEntity = new URLEntity(localURL, originalUrl);
        String shortId;
        if (!urlRepository.existsByOriginalUrl(originalUrl)) {
            Long seq = urlRepository.findFirstByOrderBySeqDesc().getSeq();
            System.out.println(seq);
            shortId = IDConverter.INSTANCE.createUniqueID(seq);
            urlEntity.setOriginalUrl(originalUrl);
            urlEntity.setShortId(shortId);
            urlEntity.setSeq(seq+1);
            urlRepository.save(urlEntity);
            }
        else {
            shortId = urlEntity.getShortId();
        }
        String baseString = formatLocalURLFromShortener(localURL);
        String shortenedURL = baseString + shortId;

        return shortenedURL;
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
