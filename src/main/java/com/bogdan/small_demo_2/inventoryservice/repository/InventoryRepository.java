package com.bogdan.small_demo_2.inventoryservice.repository;

import java.util.List;
import com.bogdan.small_demo_2.dto.InventoryDto;

public interface InventoryRepository {
    void save(String deviceId, InventoryDto inventory);
    InventoryDto findByDeviceId(String deviceId);
    List<InventoryDto> findAll();
}
