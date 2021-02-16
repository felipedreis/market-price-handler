package com.santanderefx.marketpricehandler.util;

import com.santanderefx.marketpricehandler.persistence.MarketPrice;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class MarketPriceConverterImpl implements MarketPriceConverter {
    private static final Logger LOGGER = Logger.getLogger(MarketPriceConverterImpl.class.getSimpleName());

    @Override
    public Optional<MarketPrice> convert(String input) {
        if (input == null || input.isEmpty())
            return  Optional.empty();

        String [] tokens = input.split(",");

        MarketPrice marketPrice = fromTokens(tokens);

        return Optional.ofNullable(marketPrice);
    }

    private MarketPrice fromTokens(String [] tokens) {
        if (tokens.length < 5)
            return null;

        try {
            MarketPrice  marketPrice = new MarketPrice();
            marketPrice.setId(Long.parseLong(tokens[0]));
            marketPrice.setInstrumentName(tokens[1]);
            marketPrice.setBid(Double.parseDouble(tokens[2]));
            marketPrice.setAsk(Double.parseDouble(tokens[3]));
            marketPrice.setTimestamp(LocalDateTime.parse(tokens[4], DateTimeFormatter.ofPattern("dd-MM-uuuu\' \'HH:mm:ss:SSS")));
            return marketPrice;
        } catch (NumberFormatException ex) {
            LOGGER.warning("Could not convert one of the values for the given input");
        }

        return null;
    }

}
