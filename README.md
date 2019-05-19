# Bacheloroppgave 2019 Salgsstøtte
Kildekode for bacheloroppgaven.
Sindre Østrem
Knut Grøstad

Mappestruktur
```
+---client
|   +---dist
|   |   \---client
|   |       \---assets
|   +---src
|       +---app
|       |   +---footer
|       |   +---header
|       |   +---home
|       |   +---login
|       |   +---register
|       |   +---register-company
|       |   +---search-bar
|       |   +---search-filter
|       |   +---search-result
|       |   +---user-admin
|       |   +---user-profile
|       |   +---vehicle-detail
|       |   +---vehicle-edit
|       |   +---vehicle-gallery
|       |   +---vehicle-list
|       |   +---vehicle-listing
|       |   +---_components
|       |   +---_helper
|       |   +---_models
|       |   \---_services
|       +---assets
|       \---environments
\---trader
    +---.mvn
    |   \---wrapper
    +---src
        +---main
        |   +---java
        |   |   \---com
        |   |       \---etc
        |   |           \---trader
        |   |               +---model
        |   |               +---repository
        |   |               +---rest
        |   |               |   +---errors
        |   |               |   +---util
        |   |               |   \---vm
        |   |               +---security
        |   |               \---service
        |   |                   \---jwt
        |   \---resources
        \---test
            \---java
                \---com
                    \---etc
                        \---trader
```

## Forutsetninger
Ha disse programvarene installert
- Java 8
- Maven
- Node
- npm
- Angular-Cli 7.2.2

## Klient
### Utviklings server
Kjør `ng serve` i `client` mappen for utviklingsserveren til angular. Naviger til `http://localhost:4200/` i din nettleser. Webappen vil automatisk laste om ved endringer.

### Kjøre Unit Tester
Kjør `ng test` for å kjøre tester gjennom [Karma](https://karma-runner.github.io).

## Databasen
Vi brukte XAMPP Apache for å verte en MYSql server.

### Konfigurasjon
For databasekonfigurasjon i Java endre filen `trader/src/main/resources/application.properties`.

## Trader
### Kjøre serveren
Kjør `mvn clean install` i `trader` mappen. Kjør så `java -jar ./target/trader-0.0.1-SNAPSHOT.jar` for å kjøre serveren. For å konfigurere Angular appen til å peke på rett server, endre på filen `client/src/app/globals.ts` og endre variablen `apiUrl` til rett adresse.

### Brukere:
#### Admin bruker:

username: admin
passord: admin123

#### User bruker:
username: user
passord: user123

