package com.bogdan.small_demo_2.inventoryservice.service;

import org.springframework.stereotype.Service;

import com.bogdan.small_demo_2.dto.InventoryDto;
import com.bogdan.small_demo_2.inventoryservice.repository.InventoryRepository;
import com.bogdan.small_demo_2.mdm_service.interfaces.MdmService;

import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository repository;
    private final MdmService service;

    public InventoryService(InventoryRepository repository, MdmService service) {
        this.repository = repository;
        this.service = service;
    }

    public InventoryDto fetchAndStore(String deviceId) {
        InventoryDto inventory = service.fetchInventory(deviceId);
        repository.save(deviceId, inventory);
        return inventory;
    }

    public InventoryDto getInventory(String deviceId) {
        return repository.findByDeviceId(deviceId);
    }

    public List<InventoryDto> getAllInventories() {
        return repository.findAll();
    }
}
