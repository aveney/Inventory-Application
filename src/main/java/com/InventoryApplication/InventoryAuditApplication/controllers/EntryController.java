package com.InventoryApplication.InventoryAuditApplication.controllers;

import com.InventoryApplication.InventoryAuditApplication.entities.Entry;
import com.InventoryApplication.InventoryAuditApplication.services.EntryService;
import com.InventoryApplication.InventoryAuditApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entry")
public class EntryController {

    @Autowired
    EntryService entryService;

    @Autowired
    UserService userService;

    @CrossOrigin
    @GetMapping("")
    public List<Entry> getAllEntries() {
        return entryService.findAllEntries();
    }

    @CrossOrigin
    @PostMapping("/{id}")
    public Entry addEntry(@PathVariable Long id, @RequestBody Entry entry) {
        return entryService.saveEntry(id, entry);
    }

    @CrossOrigin
    @GetMapping("/{deviceBarcode}")
    public Entry getByBarcode(@PathVariable Long deviceBarcode) {
        return entryService.findByDeviceBarcode(deviceBarcode);
    }
}
