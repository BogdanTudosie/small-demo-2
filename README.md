# Inventory Microservice PoC

This Proof-of-Concept (PoC) demonstrates a microservice system for fetching and storing device inventory via a mock MDM client, using Spring Boot and shared DTOs.

---

## Project Structure

```
small-demo-2/
 ├─ src/main/java/com/bogdan/small_demo_2/mdm_service/    # Mock MDM client service
 ├─ src/main/java/com/bogdan/small_demo_2/inventoryservice/ # Inventory service and endpoints
 ├─ src/main/java/com/bogdan/small_demo_2/shared_dto/     # Shared DTOs (InventoryDto, DeviceDto, AppDto)
 └─ README.md
```

---

## Features

### MDM Client Service

* Endpoint: `/mdm-client/fetch-apps?deviceId=DEVICE_UUID`
* Returns mock inventory data per device
* Simulates external MDM API calls

### Inventory Service

* Stores inventory in memory using `ConcurrentHashMap`
* Endpoints:

  * `POST /inventory/fetch?deviceId=DEVICE_UUID` → fetch inventory from MDM Client and store it
  * `GET /inventory?deviceId=DEVICE_UUID` → retrieve stored inventory for a specific device
  * `GET /inventory/all` → retrieve all stored inventories
* Uses shared DTOs for consistent data representation

---

## Sample DTOs

**DeviceDto**

```json
{
  "uuid": "123456",
  "name": "Bogdan's iPhone",
  "osVersion": "iOS 26.0"
}
```

**AppDto**

```json
{
  "vendor": "Apple",
  "name": "Safari",
  "version": "16.0"
}
```

**InventoryDto**

```json
{
  "device": { ...DeviceDto... },
  "apps": [ ...AppDto... ],
  "collectedAt": "2025-10-24T12:34:56Z",
  "source": "MDM_SERVICE"
}
```

---

## Flow Diagram

```
[Client / Admin Portal]
          |
          | POST /inventory/fetch?deviceId=UUID
          v
[InventoryController.fetchInventory()]
          |
          | calls
          v
[InventoryService.fetchAndStore()]
          |
          | calls
          v
[MDM Client Service (mock)]
          |
          | returns InventoryDto
          v
[InventoryRepoDefault (in-memory)]
          |
          | stores InventoryDto
          v
[InventoryController responds with InventoryDto]

GET endpoints read stored data similarly:
- /inventory?deviceId=UUID → single device
- /inventory/all → all fetched devices
```

---

## How to Run

1. Start the application:

```bash
./gradlew bootRun
```

2. Test endpoints:

* Fetch inventory for a device

```bash
curl -X POST "http://localhost:8080/inventory/fetch?deviceId=123456"
```

* Retrieve inventory for a single device

```bash
curl "http://localhost:8080/inventory?deviceId=123456"
```

* Retrieve all stored devices

```bash
curl "http://localhost:8080/inventory/all"
```

* Fetch multiple devices to populate `/all`

```bash
curl -X POST "http://localhost:8080/inventory/fetch?deviceId=ABCDEF"
curl -X POST "http://localhost:8080/inventory/fetch?deviceId=XYZ123"
```

---

## Notes

* Repository is in-memory (`ConcurrentHashMap`) for PoC purposes; can be replaced with H2 or other databases.
* Shared DTOs ensure consistency across services.
* `/fetch` is POST because it triggers an external fetch and updates storage.
* `/inventory` and `/all` are GET endpoints for reading stored data only.
* `/all` shows **all devices that have been fetched**, not all possible devices.

---

## Future Improvements

* Replace in-memory storage with persistent DB (H2/PostgreSQL)
* Batch fetch multiple devices at once
* Add asynchronous fetch and background processing
* Add retry logic and error handling for MDM Client calls
* Integration tests for end-to-end validation
* Extend DTOs for richer inventory data (profiles, device type, etc.)
* Logging and monitoring for microservice interactions
* Endpoint security and authentication
* OpenAPI/Swagger documentation for testing and exploration

---

## License

MIT License. Free for internal PoC and learning purposes.
