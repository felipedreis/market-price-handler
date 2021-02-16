package com.santanderefx.marketpricehandler.util;

import com.santanderefx.marketpricehandler.persistence.MarketPrice;
import org.springframework.stereotype.Component;

@Component
public class MarketPriceCommissionAdderImpl implements MarketPriceCommissionAdder {

    private double commissionTax = 0.1;
    @Override
    public MarketPrice apply(MarketPrice marketPrice) {
        if (marketPrice == null)
            return null;

        MarketPrice commissioned = new MarketPrice();
        marketPrice.setInstrumentName(marketPrice.getInstrumentName());
        marketPrice.setTimestamp(marketPrice.getTimestamp());
        double commission = marketPrice.getBid() * commissionTax/100.0;
        marketPrice.setBid(marketPrice.getBid() - commission);
        marketPrice.setAsk(marketPrice.getAsk() + commission);
        return commissioned;
    }
}
