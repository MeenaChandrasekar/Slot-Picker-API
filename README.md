# Slot-Picker-API

This project is a Spring Boot-based API that helps select the best slot for a container in a yard. It calculates the optimal slot by evaluating various factors such as container size, cold unit requirement, and crane position.

## Task 1 - Basic API Implementation

### Step 1: Setup

**Dependencies**:  
- Spring Web  
- Spring Boot DevTools  
- Lombok (optional)

**Main Class**: `SlotpickerApplication.java`

### Step 2: Endpoint - POST `/pickSpot`

This endpoint receives the container info and current crane position, and returns the best available slot based on scoring logic.

### Step 3: Request Body

Example request:

```json
{
  "containerId": "C123",
  "targetX": 1,
  "targetY": 1
}
containerId: Unique ID of the container

targetX: Current X coordinate of the crane

targetY: Current Y coordinate of the crane

Step 4: Response Body
Example response:
=
{
  "containerId": "C123",
  "targetX": 1,
  "targetY": 2,
  "error": null
}
targetX, targetY: Coordinates of the selected slot

error: Message if no suitable slot found (e.g., "no suitable slot")

Step 5: Yard Map Format
Slots in the yard are represented as a list of objects:

json

[
  {
    "x": 1,
    "y": 2,
    "sizeCap": "small",  
    "hasColdUnit": false,  
    "occupied": false
  },
  {
    "x": 2,
    "y": 2,
    "sizeCap": "big",
    "hasColdUnit": true,
    "occupied": false
  }
]
sizeCap: "small" or "big"

hasColdUnit: true if slot has refrigeration

occupied: true if already in use

Step 6: Scoring Logic
The API selects the best slot using:

Manhattan Distance:
abs(slotX - targetX) + abs(slotY - targetY)

Slot Type Match: Checks if container fits size/cold unit

Availability: Slot must not be occupied

Step 7: How to Run
Clone the repository:
git clone https://github.com/MeenaChandrasekar/Slot-Picker-API.git

Go to the project folder:
cd Slot-Picker-API
Start the Spring Boot application:

mvn spring-boot:run
Open browser or Postman:
http://localhost:8080

Step 8: Sample cURL Request

curl -X POST http://localhost:8080/pickSpot \
  -H "Content-Type: application/json" \
  -d '{"containerId": "C1", "targetX": 1, "targetY": 1}'
Step 9: Sample Response

{
  "containerId": "C1",
  "targetX": 1,
  "targetY": 2,
  "error": null
}

Step 10: Notes
Uses Manhattan distance for scoring

Validates size and cold unit match

Skips slots that are already occupied

Returns the nearest valid and available slot
