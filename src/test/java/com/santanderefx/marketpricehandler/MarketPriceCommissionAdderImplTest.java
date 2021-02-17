package com.santanderefx.marketpricehandler;

import com.santanderefx.marketpricehandler.persistence.MarketPrice;
import com.santanderefx.marketpricehandler.util.MarketPriceCommissionAdder;
import com.santanderefx.marketpricehandler.util.MarketPriceCommissionAdderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class MarketPriceCommissionAdderImplTest {

    private MarketPriceCommissionAdder marketPriceCommissionAdder;

    Random rnd = new Random(System.currentTimeMillis());

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
        MarketPrice marketPrice = new MarketPrice();
        marketPrice.setAsk(rnd.nextDouble());
        marketPrice.setBid(rnd.nextDouble());

        MarketPrice result = marketPriceCommissionAdder.apply(marketPrice);
        assertNotEquals(result.getAsk(), marketPrice.getAsk());
        assertNotEquals(result.getBid(), marketPrice.getBid());

        assertEquals(result.getAsk(), marketPrice.getAsk() * (1 + MarketPriceCommissionAdderImpl.COMMISSION_TAX/100.0));
        assertEquals(result.getBid(), marketPrice.getBid() * (1 - MarketPriceCommissionAdderImpl.COMMISSION_TAX/100.0));
    }

}
