package com.octopusthu.ejw.sample.springrestdocs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class HelloJavaRestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void hello() throws Exception {
        this.mockMvc.perform(get("/api/hello").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(document("hello"));
    }

    @Test
    void helloWhenPostThenError() throws Exception {
        this.mockMvc.perform(post("/api/hello").accept(MediaType.ALL))
            .andExpect(status().is4xxClientError());
    }
}
