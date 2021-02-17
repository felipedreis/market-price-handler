package com.santanderefx.marketpricehandler.starter;

import com.santanderefx.marketpricehandler.listener.MarketPriceFeedListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("development")
public class MarketPriceStarter implements CommandLineRunner {

    private MarketPriceFeedListener feedListener;

    @Autowired
    public MarketPriceStarter(MarketPriceFeedListener feedListener) {
        this.feedListener = feedListener;
    }

    @Override
    public void run(String... args) throws Exception {
        handle("106,EUR/USD,1.1,1.2,01-06-2020 12:01:01:001");
        handle("107,EUR/JPY,1.1,1.2,01-06-2020 12:01:01:001");
        handle("108,EUR/USD,1.1,1.2,01-06-2020 12:01:01:001");
        handle("109,EUR/USD,1.1,1.2,01-06-2020 12:01:01:001");
        handle("110,EUR/USD,1.1,1.2,01-06-2020 12:01:01:001");
    }

    private void handle(String str) {
        feedListener.onMessage(str);
    }
}
