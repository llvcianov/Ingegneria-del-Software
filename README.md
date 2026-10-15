# Biglietteria Compagnia di Navigazione

Sistema di prenotazione biglietti per una compagnia di navigazione, sviluppato come progetto d'esame per il corso di **Ingegneria del Software** (Laurea Triennale in Ingegneria Informatica, Università di Napoli Federico II).

Il progetto implementa il flusso di prenotazione di un biglietto (passeggero o autoveicolo) per una corsa via mare, con calcolo del prezzo, verifica della disponibilità posti e generazione della ricevuta di prenotazione.

> Progetto nato come lavoro di gruppo a 3 persone; portato avanti e completato da solo dopo l'abbandono degli altri due componenti del gruppo.

> **Nota**: questa è una demo universitaria piuttosto datata (a.a. 2022/2023), realizzata a scopo didattico per dimostrare l'applicazione del pattern Boundary-Control-Entity e del processo di analisi/progettazione OO in un contesto d'esame. Non rispecchia le pratiche di sviluppo moderne (niente framework, gestione basilare delle credenziali, nessuna validazione production-ready): oggi, per un progetto simile, si userebbero soluzioni più moderne (es. Spring Boot, un ORM, autenticazione vera). Il codice applicativo è rimasto fedele alla versione originale d'esame.

## Stack tecnico

- **Java** (JDBC)
- **MySQL 8**
- Pattern architetturale **Boundary-Control-Entity (BCE)**
- **Docker** / **Docker Compose** per l'esecuzione (aggiunti successivamente alla versione d'esame, per rendere il progetto eseguibile da chiunque senza installare nulla)

## Come avviare il progetto

Serve solo **Docker** installato. Dalla cartella del progetto:

```bash
docker compose up -d db
docker compose run --rm app
```

Il primo comando avvia MySQL e inizializza automaticamente lo schema del database (tabelle + dati di esempio) tramite [`schema.sql`](schema.sql). Il secondo compila ed esegue l'applicazione in un container, con il menu a console direttamente nel terminale.

Per fermare tutto:

```bash
docker compose down
```

Aggiungi `-v` per cancellare anche i dati del database (utile per ripartire da zero con i dati di esempio "freschi", dato che la data delle corse seminate è quella del giorno in cui il database viene creato).

### Provare una prenotazione

Esempio di flusso con i dati di esempio inclusi:

```
1                 -> PrenotaBiglietto
passeggero        -> (oppure autoveicolo)
<data odierna>    -> formato aaaa-MM-gg
10:00             -> orario corsa seminata (Napoli -> Palermo)
Napoli
Palermo
S                 -> conferma pagamento
```

È presente anche una seconda corsa di esempio (Napoli → Capri, ore 15:30, aliscafo).

### Esplorare il database con Adminer

Il `docker-compose.yml` include anche [Adminer](https://www.adminer.org/), un client MySQL via browser, per ispezionare tabelle e dati senza installare nulla:

```bash
docker compose up -d db adminer
```

Poi apri [http://localhost:8081](http://localhost:8081) e accedi con:

| Campo | Valore |
|---|---|
| Sistema | MySQL |
| Server | `db` |
| Utente | `root` |
| Password | `root` |
| Database | `gestionebiglietteria` |

## Struttura del progetto

```
BiglietteriaCompagniaNavigazione/src/
├── boundary/   interfaccia utente a console
├── control/    logica applicativa
├── database/   DAO, accesso al database
├── entity/     entità di dominio
└── exception/  eccezioni applicative
schema.sql          schema del database + dati di esempio
docker-compose.yml  orchestrazione MySQL + app + Adminer
Dockerfile           build multi-stage dell'applicazione Java
TreUominiEUnProgetto.pdf   relazione del progetto (analisi, progettazione, diagrammi UML)
```

## Fix applicati rispetto alla versione d'esame

La versione consegnata per l'esame **non includeva Docker**: era un semplice progetto Eclipse che si aspettava un database MySQL già creato manualmente in locale, senza alcuno script di setup né un modo rapido per essere eseguito da altri. Per renderlo eseguibile da chiunque con un singolo comando, sono stati aggiunti:

- `Dockerfile` e `docker-compose.yml`, per containerizzare applicazione e database (build multi-stage, non serve Java installato localmente)
- `schema.sql`, lo schema del database ricostruito analizzando le query nelle classi DAO (il progetto originale non ne includeva uno) con relativi dati di esempio
- il servizio **Adminer**, per ispezionare il database da browser senza installare client esterni

Non sono state apportate altre modifiche alla logica applicativa o al design originale del progetto.

## Documentazione

L'analisi completa del progetto (casi d'uso, diagrammi delle classi, di sequenza, E-R) è disponibile nella relazione: [`TreUominiEUnProgetto.pdf`](TreUominiEUnProgetto.pdf).
