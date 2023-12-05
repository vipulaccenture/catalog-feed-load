package com.accenture.quicksilver.catalogfeedload.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import com.accenture.quicksilver.catalogfeedload.integration.messages.PersistRequest;

@MessageEndpoint
public class PersistService {

    private static final Logger log = LoggerFactory.getLogger(PersistService.class);

    @ServiceActivator(inputChannel = "persistenceChannel")
    public void persistEntries(PersistRequest request) {
        log.info("Made it to persistence!");
        log.info("Message to persist:" + request.jsonRepresentation());

    }

}
