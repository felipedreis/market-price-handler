package com.santanderefx.marketpricehandler.rest;

import com.santanderefx.marketpricehandler.persistence.MarketPrice;
import com.santanderefx.marketpricehandler.service.MarketPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class MarketPriceRest {

    private static final Logger LOG = LoggerFactory.getLogger(MarketPriceRest.class);

    private MarketPriceService marketPriceService;

    @Autowired
    public MarketPriceRest(MarketPriceService marketPriceService) {
        this.marketPriceService = marketPriceService;
    }

    @GetMapping("/marketPrice/{id}")
    public ResponseEntity<MarketPrice> findById(@PathVariable Long id) {
        LOG.info("request for MarketPrice id {}", id);
        Optional<MarketPrice> priceOptional = marketPriceService.find(id);

        if (priceOptional.isPresent()) {
            MarketPrice marketPrice = priceOptional.get();
            return ResponseEntity.ok(marketPrice);
        } else {
            LOG.info("id {} not found", id);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/marketPrice/latest")
    public ResponseEntity<Page<MarketPrice>> findAll(
            @PageableDefault(sort = "timestamp", direction = Sort.Direction.DESC) Pageable pageable) {
        LOG.info("Requested all latest MarketPrices");
        Page<MarketPrice> marketPrices = marketPriceService.findAll(pageable);
        return ResponseEntity.ok(marketPrices);
    }

}
