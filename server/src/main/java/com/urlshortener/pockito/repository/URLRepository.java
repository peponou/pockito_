package com.urlshortener.pockito.repository;

import com.urlshortener.pockito.model.URLEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface URLRepository  extends MongoRepository<URLEntity, String> {

    @Query("{ 'shortId' : {$regex: ?0, $options: 'i' }}")
    URLEntity findByShortId(String shortId);

    URLEntity findFirstByOrderBySeqDesc();

//    QURLEntity q person = new QPerson("person");
//    List<Person> result = repository.findAll(person.address.zipCode.eq("C0123"));

    boolean existsByOriginalUrl(String originalUrl);
}
