# Ferry Company Ticketing System

Ticket booking system for a ferry company, developed as an exam project for the **Software Engineering** course (Bachelor's Degree in Computer Engineering, University of Naples Federico II).

The project implements the booking flow for a ferry crossing (passenger or vehicle ticket), including price calculation, seat/space availability checks, and booking receipt generation.

> Originally a 3-person group project; carried on and completed solo after the other two group members dropped out.

> **Note**: this is a fairly old university demo (academic year 2022/2023), built for teaching purposes to demonstrate the Boundary-Control-Entity pattern and the OO analysis/design process in an exam context. It does not reflect modern development practices (no framework, basic credential handling, no production-ready validation): today, a similar project would use more modern solutions (e.g. Spring Boot, an ORM, real authentication). The application code has been kept faithful to the original exam version.

## Tech stack

- **Java** (JDBC)
- **MySQL 8**
- **Boundary-Control-Entity (BCE)** architectural pattern
- **Docker** / **Docker Compose** to run it (added later, on top of the exam version, to make the project runnable by anyone without installing anything)

## How to run the project

You only need **Docker** installed. From the project folder:

```bash
docker compose up -d db
docker compose run --rm app
```

The first command starts MySQL and automatically initializes the database schema (tables + sample data) via [`schema.sql`](schema.sql). The second one builds and runs the application in a container, with the console menu available right in your terminal.

To stop everything:

```bash
docker compose down
```

Add `-v` to also wipe the database data (useful to start fresh with "today's" sample data, since the seeded rides are dated to the day the database was created).

### Trying out a booking

Example flow using the included sample data:

```
1                 -> PrenotaBiglietto (Book a ticket)
passeggero        -> (or autoveicolo for a vehicle)
<today's date>    -> format yyyy-MM-dd
10:00             -> time of the seeded ride (Naples -> Palermo)
Napoli
Palermo
S                 -> confirm payment
```

A second sample ride is also included (Naples → Capri, 15:30, hydrofoil).

### Exploring the database with Adminer

`docker-compose.yml` also includes [Adminer](https://www.adminer.org/), a browser-based MySQL client, to inspect tables and data without installing anything:

```bash
docker compose up -d db adminer
```

Then open [http://localhost:8081](http://localhost:8081) and log in with:

| Field | Value |
|---|---|
| System | MySQL |
| Server | `db` |
| Username | `root` |
| Password | `root` |
| Database | `gestionebiglietteria` |

## Project structure

```
BiglietteriaCompagniaNavigazione/src/
├── boundary/   console-based user interface
├── control/    application logic
├── database/   DAOs, database access
├── entity/     domain entities
└── exception/  application exceptions
schema.sql          database schema + sample data
docker-compose.yml  MySQL + app + Adminer orchestration
Dockerfile           multi-stage build of the Java application
TreUominiEUnProgetto.pdf   project report (analysis, design, UML diagrams)
```

## Fixes applied compared to the exam version

The version submitted for the exam **did not include Docker**: it was a plain Eclipse project that expected a MySQL database already created manually on the local machine, with no setup script and no quick way to run it elsewhere. To make it runnable by anyone with a single command, the following were added:

- `Dockerfile` and `docker-compose.yml`, to containerize the application and the database (multi-stage build, no local Java install needed)
- `schema.sql`, the database schema reconstructed by analyzing the queries in the DAO classes (the original project didn't include one), with sample data
- the **Adminer** service, to inspect the database from a browser without installing external clients

No other changes were made to the application logic or the original design of the project.

## Documentation

The full project analysis (use cases, class diagrams, sequence diagrams, ER diagram) is available in the report: [`TreUominiEUnProgetto.pdf`](TreUominiEUnProgetto.pdf).
