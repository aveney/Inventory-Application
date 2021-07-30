package com.InventoryApplication.InventoryAuditApplication;

import com.InventoryApplication.InventoryAuditApplication.controllers.UserController;
import com.InventoryApplication.InventoryAuditApplication.entities.User;
import com.InventoryApplication.InventoryAuditApplication.repositories.UserRepository;
import com.InventoryApplication.InventoryAuditApplication.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository repo;

    @MockBean
    private UserService userService;

    @Test
    void testAddUser() throws Exception {
        String url = "/user";

        mockMvc.perform(MockMvcRequestBuilders.post(url).content(asJsonString((new User("Adriaan", 1L))))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void testGetAllUsers() throws Exception {

        mockMvc.perform( MockMvcRequestBuilders
                .get("/user")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testRemoveUser() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .delete("/user/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGetUser() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .get("/user/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGetUserEntries() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .get("/user/1/getEntries")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
