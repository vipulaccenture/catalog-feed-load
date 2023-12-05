package com.accenture.quicksilver.catalogfeedload.integration;

import java.util.List;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import com.accenture.quicksilver.catalogfeedload.db.CatalogEntry;

@MessagingGateway(name = "catalogEntryGateway", defaultRequestChannel = "catalogReadChannel")
public interface CatalogEntryGateway {
    @Gateway
    List<CatalogEntry> getAllEntries(boolean getAll);
}
