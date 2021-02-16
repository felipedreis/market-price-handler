package com.santanderefx.marketpricehandler.util;

import com.santanderefx.marketpricehandler.persistence.MarketPrice;

import java.util.Optional;

public interface MarketPriceConverter {
    Optional<MarketPrice> convert(String input);
}
