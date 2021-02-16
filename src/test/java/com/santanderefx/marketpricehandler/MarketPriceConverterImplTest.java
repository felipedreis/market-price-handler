package com.santanderefx.marketpricehandler;

import com.santanderefx.marketpricehandler.persistence.MarketPrice;
import com.santanderefx.marketpricehandler.util.MarketPriceConverter;
import com.santanderefx.marketpricehandler.util.MarketPriceConverterImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MarketPriceConverterImplTest {
    private MarketPriceConverter converter;

    public MarketPriceConverterImplTest(){
        converter = new MarketPriceConverterImpl();
    }

    @Test
    public void testNullParameter() {
        String line = null;
        Optional<MarketPrice> optionalMarketPrice = converter.convert(line);

        assertFalse(optionalMarketPrice.isPresent());
    }

    @Test
    public void testEmptyParameter() {
        Optional<MarketPrice> optionalMarketPrice = converter.convert("");

        assertFalse(optionalMarketPrice.isPresent());
    }

    @Test
    public void testIncompleteLine() {
        String line = "1,BRL/USD,1.0,5.0";
        Optional<MarketPrice> optionalMarketPrice = converter.convert(line);
        assertFalse(optionalMarketPrice.isPresent());
    }

    @Test
    public void testEmptyFields() {
        String line = "1,BRL/USD,1.0,,01-01-2020 00:00:00:000";
        Optional<MarketPrice> optionalMarketPrice = converter.convert(line);
        assertFalse(optionalMarketPrice.isPresent());
    }

    @Test
    public void testSuccess() {
        String line = "1,BRL/USD,1.0,5.0,01-01-2020 00:00:00:000";
        Optional<MarketPrice> optionalMarketPrice = converter.convert(line);
        MarketPrice marketPrice = optionalMarketPrice.get();

        assertEquals(marketPrice.getId(), 1);
        assertEquals(marketPrice.getInstrumentName(), "BRL/USD");
        assertEquals(marketPrice.getBid(), 1);
        assertEquals(marketPrice.getAsk(), 5);
        assertEquals(marketPrice.getTimestamp(), LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0));
    }
}
