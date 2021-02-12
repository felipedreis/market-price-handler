package com.santanderefx.marketpricehandler.util;

import com.santanderefx.marketpricehandler.persistence.MarketPrice;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MarketPriceConverter {
    public Optional<MarketPrice> convert(String input) {
        return Optional.empty();
    }

}
