package com.tinqinacademy.comments.rest;

import com.tinqinacademy.comments.core.services.paths.CommentsURLPaths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.web.context.WebApplicationContext;


@AutoConfigureMockMvc
@SpringBootTest()
public class CommentsProjectAnnotation {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void givenRoomId_whenGetComments_thenReturnCommentsList() throws Exception {
        String roomId = "1";

        mockMvc.perform(get(CommentsURLPaths.ROOM_COMMENT, roomId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect( jsonPath("$").exists())
                .andExpect( jsonPath("$[0].firstName").value("Lacho"));
    }
}

