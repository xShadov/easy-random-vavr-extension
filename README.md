# easy-random-vavr-extension

[![Build Status](https://travis-ci.com/xShadov/easy-random-vavr-extension.svg?branch=master)](https://travis-ci.org/xShadov/easy-random-vavr-extension)
[![Coverage Status](https://coveralls.io/repos/github/xShadov/easy-random-vavr-extension/badge.svg?branch=master)](https://coveralls.io/github/xShadov/easy-random-vavr-extension?branch=master)
[![GitHub Release](https://img.shields.io/github/release/xShadov/easy-random-vavr-extension.svg)](https://github.com/xShadov/easy-random-vavr-extension/releases)

Extension for [Easy-Random library](https://github.com/j-easy/easy-random) adding functionality of generating [VAVR](https://github.com/vavr-io/vavr) collection types (`Set`, `List`, `Map` etc.). Supports nested generic types, for example `Map<String, Map<String, List<String>>>`

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


