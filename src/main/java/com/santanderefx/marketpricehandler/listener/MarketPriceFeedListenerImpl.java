package com.santanderefx.marketpricehandler.listener;

import com.santanderefx.marketpricehandler.persistence.MarketPrice;
import com.santanderefx.marketpricehandler.service.MarketPriceService;
import com.santanderefx.marketpricehandler.util.MarketPriceConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.logging.Logger;

@Component
public class MarketPriceFeedListenerImpl implements MarketPriceFeedListener {

    private static final Logger LOGGER = Logger.getLogger(MarketPriceFeedListenerImpl.class.getSimpleName());

    private MarketPriceService marketPriceService;

    private MarketPriceConverter converter;

    @Autowired
    public MarketPriceFeedListenerImpl(MarketPriceService marketPriceService, MarketPriceConverter converter) {
        this.marketPriceService = marketPriceService;
        this.converter = converter;
    }

    @Override
    public void onMessage(String feedLine) {
        LOGGER.info("Handling feed line " + feedLine);

        Optional<MarketPrice> marketPriceOptional = converter.convert(feedLine);

        marketPriceOptional.ifPresent(marketPriceService::save);
    }
}
