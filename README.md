 **Build**      | [![Build Status](https://travis-ci.org/s4980/grafana-api.svg)](https://travis-ci.org/s4980/grafana-api) 
 :--------- | :---: 
 **Repository** | [![Repository](https://jitpack.io/v/s4980/grafana-api.svg)](https://jitpack.io/#s4980/grafana-api)
 **Coverage**  | [![Coverage Status](https://coveralls.io/repos/github/s4980/grafana-api/badge.svg?branch=master)](https://coveralls.io/github/s4980/grafana-api?branch=master)
# Grafana-api
Grafana REST API library

## Grafana-api in public repository
To use library add Jitpack repository and grafana-api library to your configuration file
* For maven update your **pom.xml**:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.s4980</groupId>
        <artifactId>grafana-api</artifactId>
        <version>{latest_version}</version>
    </dependency>
</dependencies>
```

* For gradle update your **build.gradle**:
```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}

dependencies {
  compile 'com.github.s4980:grafana-api:{latest_version}'
}
```
