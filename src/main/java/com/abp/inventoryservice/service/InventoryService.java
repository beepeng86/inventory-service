package com.abp.inventoryservice.service;

public interface InventoryService {
    void reduceInventoryByAmount(Long inventoryId, Long count);
}
