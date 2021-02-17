package com.santanderefx.marketpricehandler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("development")
public class MarketPriceRestTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testNonExistingId() throws Exception{
        mockMvc.perform(get("/marketPrice/105")).andExpect(status().isBadRequest());
    }

    @Test
    public void testExistingId() throws Exception{
        mockMvc.perform(get("/marketPrice/106")).andExpect(status().isOk());
    }

    @Test
    public void testLatest() throws Exception {
        mockMvc.perform(get("/marketPrice/latest"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
