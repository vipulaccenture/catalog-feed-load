package com.accenture.quicksilver.catalogfeedload.integration.messages;

import java.util.List;

import com.accenture.quicksilver.catalogfeedload.db.CatalogEntry;

public record TranslateRequest(List<CatalogEntry> catalogEntries) {
    
}
