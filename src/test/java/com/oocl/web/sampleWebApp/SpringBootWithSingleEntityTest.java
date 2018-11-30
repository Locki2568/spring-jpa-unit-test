package com.oocl.web.sampleWebApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@org.springframework.boot.test.context.SpringBootTest
public class SpringBootWithSingleEntityTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private SingleEntityRepository singleEntityRepository;

    @Test
    public void should_get_hello_message() throws Exception{
        //Given a single entity in DB
        final String singleEntityName = "Hi";
        final SingleEntity entity = new SingleEntity();
        final long thisIsNotImportant = 2L;
        entity.id = thisIsNotImportant;
        entity.name = singleEntityName;
        singleEntityRepository.save(entity);
        singleEntityRepository.flush();

        //When I call API /hello
        final String json = getJsonResponse("/hello");
        final ResponseMessage responseMessage = convertJsonToResponseMessage(json);

        assertEquals(singleEntityName, responseMessage.getMessage());
    }

    private ResponseMessage convertJsonToResponseMessage(String json) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseMessage actualResponse = objectMapper.readValue(json, ResponseMessage.class);
        return actualResponse;
    }

    private String getJsonResponse(String uri) throws Exception {
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get(uri)).andReturn();
        final MockHttpServletResponse response = result.getResponse();
        return response.getContentAsString();
    }

}
