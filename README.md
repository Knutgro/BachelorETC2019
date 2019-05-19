# Bacheloroppgave 2019 Salgsstøtte
Kildekode for bacheloroppgaven.
-Sindre Østrem
-Knut Grøstad

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



## Client

This client was generated with [Angular CLI](https://github.com/angular/angular-cli) version 7.2.2.



### Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

### Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

### Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

### Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

### Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

### Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).

## Trader
