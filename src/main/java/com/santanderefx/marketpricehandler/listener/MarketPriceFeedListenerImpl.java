package com.santanderefx.marketpricehandler.listener;

import com.santanderefx.marketpricehandler.persistence.MarketPrice;
import com.santanderefx.marketpricehandler.service.MarketPriceService;
import com.santanderefx.marketpricehandler.util.MarketPriceCommissionAdder;
import com.santanderefx.marketpricehandler.util.MarketPriceConverterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.logging.Logger;

@Component
public class MarketPriceFeedListenerImpl implements MarketPriceFeedListener {

    private static final Logger LOGGER = Logger.getLogger(MarketPriceFeedListenerImpl.class.getSimpleName());

    private MarketPriceService marketPriceService;

    private MarketPriceConverterImpl converter;

    private MarketPriceCommissionAdder commissionAdder;

    @Autowired
    public MarketPriceFeedListenerImpl(MarketPriceService marketPriceService, MarketPriceConverterImpl converter,
                                       MarketPriceCommissionAdder commissionAdder) {
        this.marketPriceService = marketPriceService;
        this.converter = converter;
        this.commissionAdder = commissionAdder;
    }

    @Override
    public void onMessage(String feedLine) {
        LOGGER.info("Handling feed line " + feedLine);

        Optional<MarketPrice> marketPriceOptional = converter.convert(feedLine);

        if (marketPriceOptional.isPresent()) {
            MarketPrice parsed = marketPriceOptional.get();
            MarketPrice commissioned = commissionAdder.apply(parsed);
            marketPriceService.save(commissioned);
        }
    }
}
