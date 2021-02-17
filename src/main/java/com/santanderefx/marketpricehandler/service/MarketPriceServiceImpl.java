package com.santanderefx.marketpricehandler.service;

import com.santanderefx.marketpricehandler.persistence.MarketPrice;
import com.santanderefx.marketpricehandler.persistence.MarketPriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MarketPriceServiceImpl implements MarketPriceService {

    private static final Logger LOG = LoggerFactory.getLogger(MarketPriceServiceImpl.class);

    private MarketPriceRepository repository;

    public MarketPriceServiceImpl(MarketPriceRepository repository) {
        this.repository = repository;
    }

    @Override
    public MarketPrice save(MarketPrice marketPrice) {
        LOG.info("Saving marketPrice {}", marketPrice);
        return repository.save(marketPrice);
    }

    @Override
    public Optional<MarketPrice> find(long id) {
        LOG.info("Looking for MarketPrice id {}", id);
        Optional<MarketPrice> marketPrice = repository.findById(id);
        return marketPrice;
    }

    @Override
    public Page<MarketPrice> findAll(Pageable pageable) {
        LOG.info("looking for all MarketPrice entities");
        return repository.findAll(pageable);
    }
}
