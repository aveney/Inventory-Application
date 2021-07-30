package com.InventoryApplication.InventoryAuditApplication;

import com.InventoryApplication.InventoryAuditApplication.controllers.EntryController;
import com.InventoryApplication.InventoryAuditApplication.entities.Entry;
import com.InventoryApplication.InventoryAuditApplication.entities.User;
import com.InventoryApplication.InventoryAuditApplication.repositories.EntryRepository;
import com.InventoryApplication.InventoryAuditApplication.repositories.UserRepository;
import com.InventoryApplication.InventoryAuditApplication.services.EntryService;
import com.InventoryApplication.InventoryAuditApplication.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EntryController.class)
public class EntryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EntryRepository repo;

    @MockBean
    private EntryService entryService;

    @MockBean
    private UserService userService;

    @Test
    void testAddEntry() throws Exception {
        String url = "/entry/1";

        mockMvc.perform(MockMvcRequestBuilders.post(url).content(asJsonString((new Entry("PC", "Sandra", 23322L, 202L))))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
