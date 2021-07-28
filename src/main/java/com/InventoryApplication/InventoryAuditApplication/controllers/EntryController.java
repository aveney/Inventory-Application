package com.InventoryApplication.InventoryAuditApplication.controllers;

import com.InventoryApplication.InventoryAuditApplication.entities.Entry;
import com.InventoryApplication.InventoryAuditApplication.exceptions.InvalidEntryException;
import com.InventoryApplication.InventoryAuditApplication.services.EntryService;
import com.InventoryApplication.InventoryAuditApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
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
    public ResponseEntity addEntry(@PathVariable Long id, @RequestBody Entry entry) throws URISyntaxException {
        Entry savedEntry = entryService.saveEntry(id, entry);
        return ResponseEntity.created(new URI("/entry/" + savedEntry.getId())).body(savedEntry);
    }

    @CrossOrigin
    @GetMapping("/{deviceBarcode}")
    public Entry getByBarcode(@PathVariable Long deviceBarcode) {
        return entryService.findByDeviceBarcode(deviceBarcode);
    }

    @CrossOrigin
    @DeleteMapping("/{deviceBarcode}")
    public ResponseEntity removeEntry(@PathVariable Long deviceBarcode) {
        return entryService.deleteEntry(deviceBarcode);
    }

    @CrossOrigin
    @GetMapping("/findRoom/{roomNumber}")
    public Entry getByRoomNumber(@PathVariable Long roomNumber) {
        return entryService.findByRoomNumber(roomNumber);
    }
}
