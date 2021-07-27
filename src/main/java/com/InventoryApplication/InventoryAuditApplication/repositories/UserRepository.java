package com.InventoryApplication.InventoryAuditApplication.repositories;

import com.InventoryApplication.InventoryAuditApplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
