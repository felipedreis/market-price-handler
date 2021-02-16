package com.santanderefx.marketpricehandler.service;

import com.santanderefx.marketpricehandler.persistence.MarketPrice;
import com.santanderefx.marketpricehandler.persistence.MarketPriceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketPriceServiceImpl implements MarketPriceService {

    private MarketPriceRepository repository;

    public MarketPriceServiceImpl(MarketPriceRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(MarketPrice marketPrice) {

    }

    @Override
    public MarketPrice find(long id) {
        return null;
    }

    @Override
    public List<MarketPrice> findAll() {
        return null;
    }
}
