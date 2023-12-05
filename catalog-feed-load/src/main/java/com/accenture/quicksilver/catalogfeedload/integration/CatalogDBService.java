package com.accenture.quicksilver.catalogfeedload.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import com.accenture.quicksilver.catalogfeedload.db.CatalogEntry;
import com.accenture.quicksilver.catalogfeedload.db.CatalogRepository;

@MessageEndpoint
public class CatalogDBService {
    @Autowired
    private CatalogRepository repository;
    
    @ServiceActivator(inputChannel = "catalogReadChannel")
    public List<CatalogEntry> getAllEntries(boolean getAll) {
        return repository.findAll();
    }
}
