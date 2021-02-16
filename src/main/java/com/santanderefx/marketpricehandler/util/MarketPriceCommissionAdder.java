package com.santanderefx.marketpricehandler.util;

import com.santanderefx.marketpricehandler.persistence.MarketPrice;

public interface MarketPriceCommissionAdder {

    MarketPrice apply(MarketPrice marketPrice);
}
