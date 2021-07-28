package com.InventoryApplication.InventoryAuditApplication.services;

import com.InventoryApplication.InventoryAuditApplication.entities.Entry;
import com.InventoryApplication.InventoryAuditApplication.entities.User;
import com.InventoryApplication.InventoryAuditApplication.exceptions.InvalidEntryException;
import com.InventoryApplication.InventoryAuditApplication.exceptions.InvalidUserException;
import com.InventoryApplication.InventoryAuditApplication.repositories.EntryRepository;
import com.InventoryApplication.InventoryAuditApplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

        // Save the changes
        userRepository.save(thisUser);
        return entryRepository.save(entry);
    }

    // Remove an entry from the repository
    public ResponseEntity deleteEntry(Long deviceBarcode) {
        // Search for the entry by barcode, then retrieve its user
        Entry foundEntry = findByDeviceBarcode(deviceBarcode);
        User thisUser = foundEntry.getUser();

        // Update the entry count
        thisUser.setEntryCount(thisUser.getEntryCount() - 1);

        // Update the user in the repository
        userRepository.save(thisUser);
        // Delete the entry
        entryRepository.delete(foundEntry);
        return ResponseEntity.ok().build();
    }

    // Return a list of all the entries
    public List<Entry> findAllEntries() {
        return entryRepository.findAll();
    }

    // Find an entry by its barcode
    public Entry findByDeviceBarcode(Long deviceBarcode) {
        if (entryRepository.findByDeviceBarcode(deviceBarcode) == null) {
            throw new InvalidEntryException("This entry does not exist");
        }
        return entryRepository.findByDeviceBarcode(deviceBarcode);
    }

    // Find an entry by its room number
    public Entry findByRoomNumber(Long roomNumber) {
        if (entryRepository.findByRoomNumber(roomNumber) == null) {
            throw new InvalidEntryException("This entry does not exist");
        }
        return entryRepository.findByRoomNumber(roomNumber);
    }

}
