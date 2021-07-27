package com.InventoryApplication.InventoryAuditApplication.services;

import com.InventoryApplication.InventoryAuditApplication.entities.Entry;
import com.InventoryApplication.InventoryAuditApplication.entities.User;
import com.InventoryApplication.InventoryAuditApplication.exceptions.InvalidUserException;
import com.InventoryApplication.InventoryAuditApplication.repositories.EntryRepository;
import com.InventoryApplication.InventoryAuditApplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntryService {

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    UserRepository userRepository;

    // Save a new entry
    public Entry saveEntry(Long userId, Entry entry) {

        // Get the user
        Optional<User> findUser = userRepository.findById(userId);

        if (!findUser.isPresent()) {
            throw new InvalidUserException("This user is not found.");
        }
        User thisUser = findUser.get();

        // Set the user for the entry
        entry.setUser(thisUser);

        // Update the entry count
        thisUser.setEntryCount(thisUser.getEntryCount() + 1);
        // Add the entry
        thisUser.getEntryList().add(entry);
        userRepository.save(thisUser);
        return entryRepository.save(entry);
    }

    // Return a list of all the entries
    public List<Entry> findAllEntries() {
        return entryRepository.findAll();
    }

    // Find an entry by the barcode
    public Entry findByDeviceBarcode(Long deviceBarcode) {
        return entryRepository.findByDeviceBarcode(deviceBarcode);
    }
}
