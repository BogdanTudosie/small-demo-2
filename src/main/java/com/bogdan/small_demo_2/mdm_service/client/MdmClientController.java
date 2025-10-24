package com.bogdan.small_demo_2.mdm_service.client;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bogdan.small_demo_2.dto.InventoryDto;
import com.bogdan.small_demo_2.mdm_service.interfaces.MdmService;


@RestController
@RequestMapping("/mdm-client")
public class MdmClientController {
    private final MdmService service;

    public MdmClientController(MdmService service) {
        this.service = service;
    }

    @GetMapping("/fetch-apps")
    public InventoryDto fetchInventory(@RequestParam("device") String device) {
        return service.fetchInventory(device);
    }
}
