# Warehouse Telemetry Log Parser

A Java console application that parses warehouse IoT security logs, classifies suspicious activity by severity, and prints a simple detection summary report.

## Why I Built This

I built this project to practice Java string parsing, input validation, and security-focused logic in a scenario modeled after warehouse and operational technology environments.

The project simulates how raw telemetry from devices such as access panels, sensors, cameras, and SCADA controllers could be processed into a readable detection report.

## Features

- Parses raw telemetry log strings entered through the console
- Extracts device ID, event type, and risk score
- Classifies activity into severity tiers
- Prints a formatted per-device report
- Displays a final summary of detected activity

## Log Format

Each log must follow this format:

`DEVICE:{id}|EVENT:{event_type}|RISKSCORE:{score}`

### Example

`DEVICE:ACC002|EVENT:ACCESS_DENIED|RISKSCORE:91`

## Supported Event Types

- ACCESS_DENIED
- ACCESS_GRANTED
- TAMPER_ALERT
- MOTION_DETECTED
- UNKNOWN

## Classification Rules

- **CRITICAL** -> `ACCESS_DENIED` with risk score `80-100`
- **HIGH RISK** -> `TAMPER_ALERT` with risk score `65-85`
- **INVESTIGATE** -> `MOTION_DETECTED` with risk score `45-65`
- **ACCEPTABLE** -> everything else

## Sample Input

Enter these logs one at a time when prompted:

```text
DEVICE:SENS001|EVENT:MOTION_DETECTED|RISKSCORE:82
DEVICE:ACC002|EVENT:ACCESS_DENIED|RISKSCORE:91
DEVICE:SCADA003|EVENT:TAMPER_ALERT|RISKSCORE:67
DEVICE:CAM004|EVENT:MOTION_DETECTED|RISKSCORE:44
DEVICE:ACC005|EVENT:ACCESS_GRANTED|RISKSCORE:23
```

## Example Output

```text
Device        : SENS001
Event         : MOTION_DETECTED
Risk Score    : 82
Classification: ACCEPTABLE

Device        : ACC002
Event         : ACCESS_DENIED
Risk Score    : 91
Classification: CRITICAL

Device        : SCADA003
Event         : TAMPER_ALERT
Risk Score    : 67
Classification: HIGH RISK

Device        : CAM004
Event         : MOTION_DETECTED
Risk Score    : 44
Classification: ACCEPTABLE

Device        : ACC005
Event         : ACCESS_GRANTED
Risk Score    : 23
Classification: ACCEPTABLE
```

## Expected Summary

```text
Critical: 1
High Risk: 1
Investigate: 0
Acceptable: 3
```

## How to Run

### Compile

```bash
javac WarehouseLogParser.java
```

### Run

```bash
java WarehouseLogParser
```

Then enter 5 log strings, one per line, using the required format.

## Technical Notes

- **Language:** Java
- **Input:** `Scanner`
- **Parsing:** `indexOf()` and `substring()`
- **Design:** procedural, single-file console application

## What This Project Demonstrates

- Java fundamentals
- String parsing and field extraction
- Conditional classification logic
- Console-based program flow
- Security-minded thinking in an IoT / OT context

## Future Improvements

- Add stronger malformed-input handling
- Move logic into separate classes
- Add JUnit tests
- Support file-based log ingestion
- Export results to CSV or JSON

## Author

Daniel Dalesandro

Aspiring software engineer with a strong interest in cybersecurity, detection engineering, and secure systems.