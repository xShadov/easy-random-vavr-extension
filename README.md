# easy-random-vavr-extension

[![Build Status](https://travis-ci.com/xShadov/easy-random-vavr-extension.svg?branch=master)](https://travis-ci.org/xShadov/easy-random-vavr-extension)
[![Coverage Status](https://coveralls.io/repos/github/xShadov/easy-random-vavr-extension/badge.svg?branch=master)](https://coveralls.io/github/xShadov/easy-random-vavr-extension?branch=master)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.xshadov/easy-random-vavr-extension.svg)](https://repo1.maven.org/maven2/io/github/xshadov/easy-random-vavr-extension/1.0.0/)
[![javadoc](https://javadoc.io/badge2/io.github.xshadov/easy-random-vavr-extension/javadoc.svg)](https://javadoc.io/doc/io.github.xshadov/easy-random-vavr-extension)
[![GitHub Release](https://img.shields.io/github/release/xShadov/easy-random-vavr-extension.svg)](https://github.com/xShadov/easy-random-vavr-extension/releases)

Extension for [Easy-Random library](https://github.com/j-easy/easy-random) adding functionality of generating [VAVR](https://github.com/vavr-io/vavr) collection types (`Set`, `List`, `Map` etc.). Supports nested generic types, for example `Map<String, Map<String, List<String>>>`

## Maven
```xml
<dependency>
    <groupId>io.github.xshadov</groupId>
    <artifactId>easy-random-vavr-extension</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Usage
```java
VavrRandomizerRegistry vavrRandomizerRegistry = new VavrRandomizerRegistry();

EasyRandomParameters parameters = new EasyRandomParameters()
        .randomizerRegistry(vavrRandomizerRegistry);

EasyRandom easyRandom = new EasyRandom(parameters);

vavrRandomizerRegistry.setEasyRandom(easyRandom); // registry needs a way to random subtypes

Person randomPerson = easyRandom.nextObject(Person.class);
```

### Building

```
$ git clone https://github.com/xShadov/easy-random-vavr-extension.git
$ cd easy-random-vavr-extension
$ mvn clean install
```


