package com.santanderefx.marketpricehandler.util;

import com.santanderefx.marketpricehandler.persistence.MarketPrice;
import org.springframework.stereotype.Component;

@Component
public class MarketPriceCommissionAdderImpl implements MarketPriceCommissionAdder {

    public static final double COMMISSION_TAX = 0.1;

    @Override
    public MarketPrice apply(MarketPrice marketPrice) {
        if (marketPrice == null)
            return null;

        MarketPrice commissioned = new MarketPrice();
        commissioned.setId(marketPrice.getId());
        commissioned.setInstrumentName(marketPrice.getInstrumentName());
        commissioned.setTimestamp(marketPrice.getTimestamp());
        commissioned.setBid(marketPrice.getBid() * (1 - COMMISSION_TAX /100.0));
        commissioned.setAsk(marketPrice.getAsk() * (1 + COMMISSION_TAX /100.0));
        return commissioned;
    }
}
