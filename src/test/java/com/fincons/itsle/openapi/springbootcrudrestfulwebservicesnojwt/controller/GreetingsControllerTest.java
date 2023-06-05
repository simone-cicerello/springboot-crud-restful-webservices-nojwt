package com.fincons.itsle.openapi.springbootcrudrestfulwebservicesnojwt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fincons.itsle.openapi.springbootcrudrestfulwebservicesnojwt.entity.User;
import com.fincons.itsle.openapi.springbootcrudrestfulwebservicesnojwt.util.HandleStrings;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GreetingsController.class)
@Slf4j
class GreetingsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private HandleStrings handleStrings;

    @Test
    void test_get_Status200_Greetings() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/greetings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void test_get_String_Greetings() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/greetings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Greetings"));
    }

    @Test
    void test_post_Status200_Greetings() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/greetings")
                        .content(asJsonString(new User("Eden", "Blair")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void test_post_ResponseString_Greetings() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/greetings")
                        .content(asJsonString(new User("Lea", "Haas")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Greetings from Lea Haas"));
    }

    @Test
    void test_post_Status400_ReversedGreetings() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/reversed-greetings-error")
                        .content(asJsonString(new User("TestUser", "UriError")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //.andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void test_post_ResponseObject_ReversedGreetings() throws Exception {

        User inputUser = new User("John", "Doe");
        User expectedUser = new User("nhoJ", "eoD");

        mvc.perform(MockMvcRequestBuilders
                        .post("/greetings/reversed")
                        .content(asJsonString(inputUser))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(expectedUser.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(expectedUser.getLastName()));
    }

    @Test
    void test_post_ResponseObject_ReversedGreetings_Mock() throws Exception {

        User inputUser = new User("John", "Doe");
        User expectedUser = new User("nhoJ", "eoD");

        Mockito.when(handleStrings.reverse(inputUser.getFirstName())).thenReturn(expectedUser.getFirstName());
        Mockito.when(handleStrings.reverse(inputUser.getLastName())).thenReturn(expectedUser.getLastName());

        mvc.perform(MockMvcRequestBuilders
                        .post("/greetings/reversed")
                        .content(asJsonString(inputUser))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(expectedUser.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(expectedUser.getLastName()));
    }

    private String asJsonString(Object obj) {
        try {
            var retVal = new ObjectMapper().writeValueAsString(obj);
            log.debug(retVal);
            return retVal;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
