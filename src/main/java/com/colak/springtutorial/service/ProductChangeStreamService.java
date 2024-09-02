package com.colak.springtutorial.service;

import com.mongodb.client.model.changestream.OperationType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductChangeStreamService {

    private final MongoTemplate mongoTemplate;

    @PostConstruct
    public void startChangeStream() {
        mongoTemplate.getCollection("products")
                .watch()
                .forEach(changeStreamDocument -> {
                    OperationType operationType = changeStreamDocument.getOperationType();
                    Document document = changeStreamDocument.getFullDocument();

                    switch (operationType) {
                        case INSERT:
                            log.info("Inserted: {}", document.toJson());
                            break;
                        case UPDATE:

                            log.info("Updated: {}", document.toJson());
                            break;
                        case DELETE:
                            log.info("Deleted: {}", changeStreamDocument.getDocumentKey());
                            break;
                        default:
                            log.info("Other change: {}", operationType);
                            break;
                    }
                });
    }
}

