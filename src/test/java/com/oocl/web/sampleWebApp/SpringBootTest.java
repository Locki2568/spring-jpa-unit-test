package com.oocl.web.sampleWebApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@org.springframework.boot.test.context.SpringBootTest
public class SpringBootTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void should_return_200() throws Exception{
        final MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/hello"))
                .andReturn();
        final MockHttpServletResponse response = result.getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    public void should_assert_body() throws Exception{
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/hello")).andReturn();
        final MockHttpServletResponse response = result.getResponse();
        final String json = response.getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseMessage actualResponse = objectMapper.readValue(json, ResponseMessage.class);

        assertEquals("HI", actualResponse.getMessage());
    }
}
