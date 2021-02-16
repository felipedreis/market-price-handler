package com.santanderefx.marketpricehandler.rest;

import com.santanderefx.marketpricehandler.persistence.MarketPrice;
import com.santanderefx.marketpricehandler.service.MarketPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/marketPrice")
public class MarketPriceRest {
    private MarketPriceService marketPriceService;

    @Autowired
    public MarketPriceRest(MarketPriceService marketPriceService) {
        this.marketPriceService = marketPriceService;
    }

    @PostMapping
    public ResponseEntity<MarketPrice> save(MarketPrice marketPrice) {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarketPrice> findById(long id) {
        return null;
    }

    public ResponseEntity<Page<MarketPrice>> findAll(Pageable pageable) {
        return null;
    }

}
