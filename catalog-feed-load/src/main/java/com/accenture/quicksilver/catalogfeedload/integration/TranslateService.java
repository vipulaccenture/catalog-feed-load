package com.accenture.quicksilver.catalogfeedload.integration;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import com.accenture.quicksilver.catalogfeedload.db.CatalogEntry;
import com.accenture.quicksilver.catalogfeedload.integration.messages.PersistRequest;
import com.accenture.quicksilver.catalogfeedload.integration.messages.TranslateRequest;

@MessageEndpoint
public class TranslateService {
    
    
    @ServiceActivator(inputChannel = "translationChannel", outputChannel = "persistenceChannel")
    public PersistRequest translateCatalogEntries(TranslateRequest request) {
        var result = new StringBuilder();
        for(CatalogEntry entry : request.catalogEntries()) {
            result.append(entry.toString());
        }
        return new PersistRequest(result.toString());
    }
    
}
