package com.abp.inventoryservice.service;

import com.abp.inventoryservice.model.Inventory;
import com.abp.inventoryservice.repository.InventoryRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @PostConstruct
    public void insertSampleData() {
        inventoryRepository.save(Inventory.builder().name("sample inventory 1").count(300L).build());
        inventoryRepository.save(Inventory.builder().name("sample inventory 2").count(3L).build());
        inventoryRepository.save(Inventory.builder().name("sample inventory 3").count(10L).build());
    }

    @Override
    public void reduceInventoryByAmount(Long inventoryId, Long count) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid inventory id"));

        if (inventory.getCount() >= count) {
            inventory.setCount(inventory.getCount() - count);
            inventoryRepository.save(inventory);
        } else {
            // TODO: consider dedicated exception and trigger restock event in future potentially, message
            String message = "Inventory low for " + inventory.getName() + "-" + inventory.getId();
            log.trace(message);
            throw new UnsupportedOperationException(message);
        }
    }
}
