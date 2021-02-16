package com.santanderefx.marketpricehandler;


import com.santanderefx.marketpricehandler.listener.MarketPriceFeedListener;
import com.santanderefx.marketpricehandler.persistence.MarketPrice;
import com.santanderefx.marketpricehandler.service.MarketPriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
public class MarketPriceFeedListenerTest {

    @Autowired
    private MarketPriceFeedListener feedListener;

    @MockBean
    private MarketPriceService marketPriceService;

    private ArgumentCaptor<MarketPrice> captor;

    @BeforeEach
    public void init(){
        captor = ArgumentCaptor.forClass(MarketPrice.class);
    }

    @Test
    public void defensiveTest() {
        feedListener.onMessage("");
        verify(marketPriceService, never()).save(any());
    }

    @Test
    public void defensiveTest2() {
        feedListener.onMessage(null);
        verify(marketPriceService, never()).save(any());
    }

    @Test
    public void testSuccess() {
        feedListener.onMessage("1,BRL/USD,1.0,5.0,01-01-2020 00:00:00:000");
        verify(marketPriceService).save(captor.capture());
        MarketPrice marketPrice = captor.getValue();
        assertNotEquals(marketPrice.getBid(), 1.0);
        assertNotEquals(marketPrice.getBid(), 5.0);
    }
}
