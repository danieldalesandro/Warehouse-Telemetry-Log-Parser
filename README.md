# Warehouse Telemetry Log Parser

A Java console application that simulates a security detection pipeline for warehouse IoT infrastructure. Ingests raw device telemetry log strings from sensors, access panels, SCADA controllers, and cameras, parses and validates each field, classifies threat severity, and generates a structured detection report.

---

## What It Does

- Accepts raw IoT device log strings via console input
- Parses device ID, event type, and risk score from each log using String methods
- Classifies each device event into a threat tier based on event type and risk score
- Prints a formatted detection report per device
- Outputs a summary count of threats by classification tier

---

## Threat Classifications

| Classification | Criteria |
|---|---|
| CRITICAL | ACCESS_DENIED event with risk score 80–100 |
| HIGH RISK | TAMPER_ALERT event with risk score 65–85 |
| INVESTIGATE | MOTION_DETECTED event with risk score 45–65 |
| ACCEPTABLE | Everything else |

---

## Log Format

Logs must follow this exact format:

```
DEVICE:{id}|EVENT:{event_type}|RISKSCORE:{score}
```

Example:

```
DEVICE:ACC002|EVENT:ACCESS_DENIED|RISKSCORE:91
```

Supported event types: `ACCESS_DENIED`, `ACCESS_GRANTED`, `TAMPER_ALERT`, `MOTION_DETECTED`, `UNKNOWN`

---

## Sample Output

```
Device        : ACC002
Event         : ACCESS_DENIED
Riskscore     : 91
Classification: CRITICAL

Critical risk: 2
High risk: 1
Investigate: 1
```

---

## Test Strings

Copy and paste these one at a time when prompted:

```
DEVICE:SENS001|EVENT:MOTION_DETECTED|RISKSCORE:82
DEVICE:ACC002|EVENT:ACCESS_DENIED|RISKSCORE:91
DEVICE:SCADA003|EVENT:TAMPER_ALERT|RISKSCORE:67
DEVICE:CAM004|EVENT:MOTION_DETECTED|RISKSCORE:44
DEVICE:ACC005|EVENT:ACCESS_GRANTED|RISKSCORE:23
```

Expected output: 1 CRITICAL, 1 HIGH RISK, 0 INVESTIGATE, 2 ACCEPTABLE.

---

## How to Run

1. Compile the file:

```
javac WarehouseLogParser.java
```

2. Run the program:

```
java WarehouseLogParser
```

3. When prompted, enter 5 log strings one per line following the format above.

---

## Technical Details

- Language: Java (procedural — no OOP)
- Input: Scanner-based console input fills a String array
- Parsing: Custom `extractField` method using `indexOf` and `substring`
- Validation: Event type and risk score range checked before classification
- Output: Formatted per-device report with summary counters

---

## About

Built by a self-taught developer interested in cybersecurity and detection engineering. The theme is modeled after real IoT/OT security environments — warehouse sensors, SCADA controllers, and access panels.
