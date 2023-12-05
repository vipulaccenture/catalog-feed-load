package com.accenture.quicksilver.catalogfeedload.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<CatalogEntry, Long>{
    
}
