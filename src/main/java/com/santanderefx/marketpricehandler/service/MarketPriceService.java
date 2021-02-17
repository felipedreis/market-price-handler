package com.santanderefx.marketpricehandler.service;

import com.santanderefx.marketpricehandler.persistence.MarketPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MarketPriceService {
    MarketPrice save(MarketPrice marketPrice);

    Optional<MarketPrice> find(long id);

    Page<MarketPrice> findAll(Pageable pageable);
}
