## Build and Run using JVM

Uses the GluonFX maven plugin

```bash
$ mvn gluonfx:run
```

## Build and Run using native compile / desktop

Uses the GluonFX maven plugin/GraalVM

```bash
$ mvn gluonfx:build
$ mvn gluonfx:package
$ mvn gluonfx:nativerun

```
## Build and Run on connected ios device

```bash
$ mvn -Pios gluonfx:build
$ mvn -Pios gluonfx:package
$ mvn -Pios gluonfx:install
$ mvn -Pios gluonfx:nativerun
```

## Build and Run on connected Android device

```bash
$ mvn -Pandroid gluonfx:build
$ mvn -Pandroid gluonfx:package
$ mvn -Pandroid gluonfx:install
$ mvn -Pandroid gluonfx:nativerun
```
