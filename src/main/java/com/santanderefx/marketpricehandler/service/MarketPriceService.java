package com.santanderefx.marketpricehandler.service;

import com.santanderefx.marketpricehandler.persistence.MarketPrice;

import java.util.List;

public interface MarketPriceService {
    void save(MarketPrice marketPrice);

    MarketPrice find(long id);

    List<MarketPrice> findAll();
}
