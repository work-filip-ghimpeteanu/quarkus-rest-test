# quarkus-rest-test project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
mvn quarkus:dev
```

## Packaging and running the application

The application can be packaged using `mvn package`.
It produces the `quarkus-rest-test-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/quarkus-rest-test-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `mvn package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `mvn package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/quarkus-rest-test-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.

## Database
The script from `src/main/scripts/quarkys_postgres.sh` takes care of creating a docker container with an postgres image.

The commands to interact with the container are
```bash
./src/main/scripts/quarkus_postgres.sh start # to start the Postgres container
./src/main/scripts/quarkus_postgres.sh stop # to stop the Postgres container
```
The configuration info is:
```
URL = jdbc:postgresql://localhost:25432/quarkus_db
Username/Password = admin/admin
```
