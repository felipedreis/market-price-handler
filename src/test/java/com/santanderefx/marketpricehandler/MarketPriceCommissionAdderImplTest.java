package com.santanderefx.marketpricehandler;

import com.santanderefx.marketpricehandler.persistence.MarketPrice;
import com.santanderefx.marketpricehandler.util.MarketPriceCommissionAdder;
import com.santanderefx.marketpricehandler.util.MarketPriceCommissionAdderImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class MarketPriceCommissionAdderImplTest {
    private MarketPriceCommissionAdder marketPriceCommissionAdder;

    private static final double DELTA = 1e-6;

    @BeforeEach
    public void init(){
        marketPriceCommissionAdder = new MarketPriceCommissionAdderImpl();
    }

    @Test
    public void defensiveTest(){
        MarketPrice marketPrice = marketPriceCommissionAdder.apply(null);
        assertNull(marketPrice);
    }

    public void testEmptyFields() {
        MarketPrice marketPrice = marketPriceCommissionAdder.apply(new MarketPrice());
        assertEquals(marketPrice.getAsk(), 0);
        assertEquals(marketPrice.getBid(), 0);
    }

    @Test
    public  void testRandomValues() {
        Random rnd = new Random(System.currentTimeMillis());
        MarketPrice marketPrice = new MarketPrice();
        marketPrice.setAsk(rnd.nextDouble());
        marketPrice.setBid(rnd.nextDouble());

        MarketPrice result = marketPriceCommissionAdder.apply(marketPrice);
        double commissionTax = (result.getAsk() - result.getBid())/marketPrice.getBid();
        assertTrue(Math.abs(commissionTax - 0.1/100.0) < DELTA);
    }

}
