package com.bogdan.small_demo_2.mdm_service.interfaces;

import com.bogdan.small_demo_2.dto.InventoryDto;

// Interface for MDM Client operations / Mock for now
public interface MdmService {
    InventoryDto fetchInventory(String deviceId);
}
