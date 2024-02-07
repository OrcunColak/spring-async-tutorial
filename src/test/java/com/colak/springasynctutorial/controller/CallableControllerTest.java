package com.colak.springasynctutorial.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CallableController.class)
class CallableControllerTest {

    @Autowired
    private MockMvc mockMvc;


    /**
     * Example is from <a href="https://stackoverflow.com/questions/34343231/how-to-test-deferredresult-timeoutresult">...</a>
     */
    @Test
    void testRule() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ruleTheWorldAsync");

        ResultMatcher asyncStartedMatcher = MockMvcResultMatchers.request().asyncStarted();
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(asyncStartedMatcher)
                .andReturn();

        ResultMatcher statusOkMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().string("Ruling...");
        mockMvc
                .perform(MockMvcRequestBuilders.asyncDispatch(mvcResult))
                .andExpect(statusOkMatcher)
                .andExpect(contentMatcher);
    }
}

