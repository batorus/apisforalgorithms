This project is meant to provide APIs for several interesting algorithms
involved in problem solving with the use of spring boot framework.

### 1. Find the shortest safe route in a field with sensors present:

Given a rectangular
field with few sensors present, cross it by taking the shortest safe route without
activating the sensors. The rectangular field is in the form of an M × N matrix,
and we need to find the shortest path from any cell in the first column
to any cell in the last column of the matrix. The sensors are marked by the
value 0 in the matrix, and all its eight adjacent cells can also activate the
sensors. The path can only be constructed out of cells having value 1,
and at any given moment, we can only move one step in one of the four directions.
The valid moves are:

Go Up: (x, y) -> (x – 1, y)\
Go Left: (x, y) —> (x, y – 1)\
Go Down: (x, y) —> (x + 1, y)\
Go Right: (x, y) —> (x, y + 1)

#### Endpoints:

#### SensorController:
The form of post request should be like this:\
{"arrayofints":\
[[0, 1, 1, 1],\
[1, 1, 0, 1],\
[1, 1, 1, 1],\
[1, 1, 1, 1]]}

POST /api/v1/sensors/findfieldpath

GET /api/v1/sensors/getfieldpath



