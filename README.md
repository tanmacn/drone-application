# Getting Started

### Clone the project into an IDE of your choice
### build and run the project: it runs on http://localhost:8080 , follow the steps below for API interaction

### Run the tests


## Using Postman you can interact with the API using below end points
### 1. To get All drones inserted

GET : ```http://localhost:8080/drone```

#### Response

```
{
    "content": [
        {
            "id": "b9156122-c619-44b0-8774-ca3912d467af",
            "created": "2022-08-17T17:26:19.759267+02:00",
            "updated": "2022-08-17T17:26:19.759267+02:00",
            "deleted": null,
            "serialNumber": "1234abc4d",
            "model": "Lightweight",
            "weight": 100,
            "batteryCapacity": 90,
            "state": "IDLE",
            "medications": []
        },
        {
            "id": "b9156122-c619-44b0-8774-ca3912d467aa",
            "created": "2022-08-17T17:26:19.759267+02:00",
            "updated": "2022-08-17T17:26:19.759267+02:00",
            "deleted": null,
            "serialNumber": "1234abc4e",
            "model": "Middleweight",
            "weight": 200,
            "batteryCapacity": 90,
            "state": "LOADING",
            "medications": []
        },
        {
            "id": "b9156122-c619-44b0-8774-ca3912d467ab",
            "created": "2022-08-17T17:26:19.759267+02:00",
            "updated": "2022-08-17T17:26:19.759267+02:00",
            "deleted": null,
            "serialNumber": "1234abc4f",
            "model": "Cruiserweight",
            "weight": 400,
            "batteryCapacity": 90,
            "state": "LOADED",
            "medications": []
        },
        {
            "id": "b9156122-c619-44b0-8774-ca3912d467ac",
            "created": "2022-08-17T17:26:19.759267+02:00",
            "updated": "2022-08-17T17:26:19.759267+02:00",
            "deleted": null,
            "serialNumber": "1234abc4g",
            "model": "Heavyweight",
            "weight": 500,
            "batteryCapacity": 50,
            "state": "DELIEVERING",
            "medications": []
        },
        {
            "id": "b9156122-c619-44b0-8774-ca3912d467ad",
            "created": "2022-08-17T17:26:19.759267+02:00",
            "updated": "2022-08-17T17:26:19.759267+02:00",
            "deleted": null,
            "serialNumber": "1234abc4h",
            "model": "Heavyweight",
            "weight": 500,
            "batteryCapacity": 80,
            "state": "DELIVERED",
            "medications": []
        },
        {
            "id": "b9156122-c619-44b0-8774-ca3912d467ae",
            "created": "2022-08-17T17:26:19.759267+02:00",
            "updated": "2022-08-17T17:26:19.759267+02:00",
            "deleted": null,
            "serialNumber": "1234abc4i",
            "model": "Lightweight",
            "weight": 100,
            "batteryCapacity": 10,
            "state": "IDLE",
            "medications": []
        },
        {
            "id": "b9156122-c619-44b0-8774-ca3912d467ag",
            "created": "2022-08-17T17:26:19.759267+02:00",
            "updated": "2022-08-17T17:26:19.759267+02:00",
            "deleted": null,
            "serialNumber": "1234abc4j",
            "model": "Cruiserweight",
            "weight": 400,
            "batteryCapacity": 90,
            "state": "LOADED",
            "medications": []
        },
        {
            "id": "b9156122-c619-44b0-8774-ca3912d467ah",
            "created": "2022-08-17T17:26:19.759267+02:00",
            "updated": "2022-08-17T17:26:19.759267+02:00",
            "deleted": null,
            "serialNumber": "1234abc4k",
            "model": "Heavyweight",
            "weight": 500,
            "batteryCapacity": 46,
            "state": "DELIEVERING",
            "medications": []
        },
        {
            "id": "b9156122-c619-44b0-8774-ca3912d467ai",
            "created": "2022-08-17T17:26:19.759267+02:00",
            "updated": "2022-08-17T17:26:19.759267+02:00",
            "deleted": null,
            "serialNumber": "1234abc4l",
            "model": "Heavyweight",
            "weight": 500,
            "batteryCapacity": 78,
            "state": "DELIVERED",
            "medications": []
        },
        {
            "id": "b9156122-c619-44b0-8774-ca3912d467aj",
            "created": "2022-08-17T17:26:19.759267+02:00",
            "updated": "2022-08-17T17:26:19.759267+02:00",
            "deleted": null,
            "serialNumber": "1234abc4m",
            "model": "Heavyweight",
            "weight": 500,
            "batteryCapacity": 75,
            "state": "RETURNING",
            "medications": []
        }
    ],
    "number": 0,
    "numberOfElements": 10,
    "size": 10,
    "totalElements": 10,
    "totalPages": 1
}
```

### 2. To add a drone

POST : ```http://localhost:8080/drone```

#### Request

```
{
  "serialNumber": "1234abc4der",
  "model": "Lightweight",
  "weight": 100,
  "batteryCapacity": 90
}
```

#### Response

```
{
    "id": "7784ab70-6176-4416-856e-bab086148a59",
    "created": "2023-02-07T21:52:41.3703785+02:00",
    "updated": "2023-02-07T21:52:41.3703785+02:00",
    "deleted": null,
    "serialNumber": "1234abc4der",
    "model": "Lightweight",
    "weight": 100,
    "batteryCapacity": 90,
    "state": "IDLE",
    "medications": null
}
```

### 3. To load a drone

POST : ```http://localhost:8080/dispatch/load-drone/{droneId}```

### Example

```
http://localhost:8080/dispatch/load-drone/b9156122-c619-44b0-8774-ca3912d467af
```

#### Request
```
{
  "name": "mashdhdfd2",
  "code": "KUNDGDGDD",
  "weight":100
}
```
#### Response
```
{
    "message": "Saved successfully"
}
```

### 4. To get drone items

GET : ```http://localhost:8080/dispatch/check-drone-items/{droneId}/delivery-state
/{delieveryState}```

Delivery state is added to return items that are currently under delivery for a particular drone, since with repeated 
operations, there will be many items associated with the drone, of which some items would have been delivered.

### Example
```
http://localhost:8080/dispatch/check-drone-items/b9156122-c619-44b0-8774-ca3912d467af/delivery-state/PENDING
```

#### Response
```
[
    {
        "id": "8bcee9f6-49c5-4d07-98d6-addcbfe59bbe",
        "created": "2023-02-07T21:52:53.444634+02:00",
        "updated": "2023-02-07T21:52:53.444634+02:00",
        "deleted": null,
        "name": "mashdhdfd2",
        "weight": 100,
        "code": "KUNDGDGDD",
        "image": null,
        "state": "PENDING"
    }
]
```

### 4. To get drone battery capacity

GET : ```http://localhost:8080/dispatch/check-drone-battery-level/{droneId}```

### Example
```http://localhost:8080/dispatch/check-drone-battery-level/b9156122-c619-44b0-8774-ca3912d467aa```

#### Response
```
{
    "batteryCapacity": 90
}
```

### 5. To get available drones

GET : ```http://localhost:8080/dispatch/check-available-drones```

#### Response
```
{
    "batteryCapacity": 90
}
```