package com.abp.inventoryservice.service;

import com.abp.inventoryservice.model.Inventory;
import com.abp.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

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
