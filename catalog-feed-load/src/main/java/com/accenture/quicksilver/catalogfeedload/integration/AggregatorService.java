package com.accenture.quicksilver.catalogfeedload.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import com.accenture.quicksilver.catalogfeedload.integration.messages.AggregateRequest;
import com.accenture.quicksilver.catalogfeedload.integration.messages.TranslateRequest;

@MessageEndpoint
public class AggregatorService {
    
    @Autowired
    private CatalogEntryGateway catalogEntryGateway;

    @ServiceActivator(inputChannel = "aggregationChannel", outputChannel = "translationChannel")
    public TranslateRequest aggregateCatalogEntries(AggregateRequest request) {
        var catalogEntries = catalogEntryGateway.getAllEntries(true);
        return new TranslateRequest(catalogEntries);
    }
    
}
