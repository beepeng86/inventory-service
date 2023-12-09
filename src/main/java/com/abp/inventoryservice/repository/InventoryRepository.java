package com.abp.inventoryservice.repository;

import com.abp.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "inventory", path = "inventory")
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
