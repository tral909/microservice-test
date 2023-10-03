package org.test.microservice.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Sql("/db/data.sql")
public class MessageControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/v1/message"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].from", is("tral909")))
                .andExpect(jsonPath("$.content[0].to", is("keepcodeDevs")))
                .andExpect(jsonPath("$.content[0].text", is("My test solution is the best!")))
                .andExpect(jsonPath("$.content[0].type", is("TELEGRAM")))
                .andExpect(jsonPath("$.content[1].from", is("keepcodeTeam")))
                .andExpect(jsonPath("$.content[1].to", is("tral909")))
                .andExpect(jsonPath("$.content[1].text", is("You are krasavchik, we wanna see u in our team!")))
                .andExpect(jsonPath("$.content[1].type", is("SMS")));
    }

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(get("/v1/message/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.from", is("tral909")))
                .andExpect(jsonPath("$.to", is("keepcodeDevs")))
                .andExpect(jsonPath("$.text", is("My test solution is the best!")))
                .andExpect(jsonPath("$.type", is("TELEGRAM")));
    }

    @Test
    public void testGetByType() throws Exception {
        mockMvc.perform(get("/v1/message?type=SMS"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].from", is("keepcodeTeam")))
                .andExpect(jsonPath("$[0].to", is("tral909")))
                .andExpect(jsonPath("$[0].text", is("You are krasavchik, we wanna see u in our team!")))
                .andExpect(jsonPath("$[0].type", is("SMS")));
    }
}
