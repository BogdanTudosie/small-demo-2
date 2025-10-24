package com.bogdan.small_demo_2.inventoryservice.repository;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.bogdan.small_demo_2.dto.InventoryDto;

@Repository
public class InventoryRepoDefault implements InventoryRepository {
    private final Map<String, InventoryDto> storage = new ConcurrentHashMap<>();

    @Override
    public void save(String deviceId, com.bogdan.small_demo_2.dto.InventoryDto inventory) {
        storage.put(deviceId, inventory);
    }

    @Override
    public com.bogdan.small_demo_2.dto.InventoryDto findByDeviceId(String deviceId) {
        return storage.get(deviceId);
    }

    @Override
    public java.util.List<com.bogdan.small_demo_2.dto.InventoryDto> findAll() {
        return new ArrayList<>(storage.values());
    }
}
