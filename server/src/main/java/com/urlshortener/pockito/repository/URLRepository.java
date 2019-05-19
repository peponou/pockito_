package com.urlshortener.pockito.repository;

import com.urlshortener.pockito.model.URLEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface URLRepository  extends MongoRepository<URLEntity, String> {

    @Query("{ 'shortId' : {$regex: ?0, $options: 'i' }}")
    URLEntity findByShortId(String shortId);

    URLEntity findFirstByOrderBySeqDesc();

    boolean existsByOriginalUrl(String originalUrl);

    @Query("{ 'originalUrl' : {$regex: ?0, $options: 'i' }}")
    URLEntity findByOriginalUrl(String originalUrl);
}
