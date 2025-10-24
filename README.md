# Inventory Microservice PoC

This is a Proof-of-Concept (PoC) microservice system for fetching and storing device inventory via a mock MDM client.
It demonstrates Spring Boot microservices, shared DTOs, in-memory storage, and REST endpoints.

---

## Project Structure

```
inventory-poc/
 ├─ mdm-client-service/      # Mock MDM client microservice
 ├─ inventory-service/       # Inventory microservice
 ├─ shared-dto/              # Shared DTO library for both services
 └─ README.md
```

---

## Features

### MDM Client Service

* Single endpoint: `/mdm-client/fetch-apps?uuid=DEVICE_UUID`
* Returns mock inventory data for a device
* Designed to simulate external MDM API calls

### Inventory Service

* Stores inventory in memory (`ConcurrentHashMap`)
* Endpoints:

  * `POST /inventory/fetch?deviceId=DEVICE_UUID` → fetch inventory from MDM Client and store it
  * `GET /inventory?deviceId=DEVICE_UUID` → retrieve stored inventory for a device
  * `GET /inventory/all` → retrieve all stored device inventories
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
[ Swift App / Admin Portal ]
             |
             | POST /inventory/fetch?deviceId=UUID
             v
   [ InventoryController.fetchInventory() ]
             |
             | calls
             v
       [ InventoryService.fetchAndStore() ]
             |
             | calls
             v
       [ MDM Client Service (mock) ]
             |  (returns InventoryDto)
             v
   [ InventoryRepository (in-memory) ]
             | stores InventoryDto
             v
[ InventoryController responds with InventoryDto ]

[ Swift App / Admin Portal ]
             |
             | GET /inventory?deviceId=UUID
             v
   [ InventoryController.getInventory() ]
             |
             | reads from repository
             v
[ InventoryDto returned ]

[ Swift App / Admin Portal ]
             |
             | GET /inventory/all
             v
   [ InventoryController.getAllInventories() ]
             |
             | reads all from repository
             v
[ List<InventoryDto> returned ]
```

---

## How to Run

1. Start **MDM Client Service**:

```bash
./gradlew :mdm-client-service:bootRun
```

2. Start **Inventory Service**:

```bash
./gradlew :inventory-service:bootRun
```

3. Test the endpoints:

* **Fetch inventory for a device**

```bash
curl -X POST "http://localhost:8080/inventory/fetch?deviceId=123456"
```

* **Get inventory for a single device**

```bash
curl "http://localhost:8080/inventory?deviceId=123456"
```

* **Get inventory for all devices**

```bash
curl "http://localhost:8080/inventory/all"
```

---

## Notes

* Repository is **in-memory** for PoC purposes; can be replaced with H2 or another DB for persistence.
* Shared DTOs ensure consistency across services.
* Fetching is **device-based**; `/all` returns all previously fetched inventories.
* `/fetch` is a POST endpoint because it triggers an external call and updates the repository.
* `/inventory` and `/all` are GET endpoints for reading stored data only.

---

## Future Improvements

* Add **persistent storage** (H2, PostgreSQL, etc.)
* Add **batch fetch** for multiple devices at once
* Add **error handling and retries** for MDM Client calls
* Introduce **asynchronous fetch** with timeouts and background processing
* Add **integration tests** for end-to-end validation
* Extend DTOs to include more detailed inventory information (e.g., installed profiles, device type)
* Add **logging and monitoring** for microservice interactions
* Consider **security/authentication** for the endpoints
* Provide **OpenAPI / Swagger documentation** for easy testing

---

## License

MIT License. Free to use for internal PoC and learning purposes.
