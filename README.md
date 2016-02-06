# Grafana-api
Grafana REST API library

## Grafana-api in public repository
To use library add Jitpack repository and grafana-api library to your configuration file
* For maven update your pom.xml:
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
        <version>v0.1.1</version>
    </dependency>
</dependencies>
```

* For gradle update your 
```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}

dependencies {
  compile 'com.github.s4980:grafana-api:v0.1.1'
}
```

Grafana-api availability on Jitpack: [![](https://jitpack.io/v/s4980/grafana-api.svg)](https://jitpack.io/#s4980/grafana-api)
