# Perceptolab S.r.l. - DevOps Demo App

This demo service is designed to enable the DevOps Engineer to easily build, test and monitor it via the common CNCF/OSS
solutions available. The service is built around the Spring Boot framework, along with some modules for JPA, monitoring
and HTTP client usage.

Please remember that this is an overly simplified application designed just to expose a basic service and allow scraping
of health and metrics data from it. This app is completely **stateless** and therefore **safe to be replicated** as needed.

## Requisites
The application needs some basic requirements in order to work properly:
- Java **JRE 17** or greater
- **256MB** of RAM
- **PostgreSQL** Database access
- Internet access (for Feign clients)

## Quick Start
Run the application tests prior to any other operation:
```shell
$ ./gradlew test
```

Build the OCI image using the included Gradle Wrapper:
```shell
$ ./gradlew bootBuildImage
```
This will build and tag the service image under the following naming: `devopsdemo:<version>`

To run the image as a container using Docker or Podman:
```shell
$ docker run -p 8080:8080 -e DB_PASS=<value> devopsdemo:<version>
```
The only exposed port by the container is the `8080` and is mapped as an HTTP-only port (HTTP/1.1).
Remember that the service will try to connect to a PostgreSQL instance, and if it doesn't connect it will not work.

## Configuration
The app has some parameters which can be configured via environment variables while deploying it:

| Name      | Description              | Type   | Default     | Required |
|-----------|--------------------------|--------|-------------|----------|
| `DB_HOST` | PostgreSQL host endpoint | String | `localhost` | No       |
| `DB_PORT` | PostgreSQL host port     | Number | 5432        | No       |
| `DB_NAME` | PostgreSQL database name | String | `postgres`  | No       |
| `DB_USER` | PostgreSQL username      | String | `postgres`  | No       |
| `DB_PASS` | PostgreSQL user password | String | N/A         | Yes      |

## API Reference
| Endpoint               | Description                          | Method     | Accepts        | Type              |
|------------------------|--------------------------------------|------------|----------------|-------------------|
| `/api/counter`         | Add 1 and return a Long counter      | PUT        | N/A            | Long              |
| `/api/counter`         | Remove 1 and return a Long counter   | DELETE     | N/A            | Long              |
| `/api/status`          | Get current machine status           | GET        | N/A            | SystemInfo        |
| `/api/ext/users`       | Get list of users from GoRest        | GET        | N/A            | List(GoRestUser)  |
| `/api/ext/posts`       | Get list of posts from GoRest        | GET        | N/A            | List(GoRestPost)  |
| `/api/db/users`        | Get all app users                    | GET        | N/A            | List(AppUser)     |
| `/api/db/users`        | Save a new app user                  | PUT        | AddUserRequest | AppUser           |
| `/api/db/users/{id}`   | Get or delete a single app user      | GET/DELETE | N/A            | AppUser / None    |
| `/actuator/health`     | Get current health of the app        | GET        | N/A            | HealthResponse    |
| `/actuator/prometheus` | Get app metrics in Prometheus format | GET        | N/A            | Prometheus Format |

## Objects Reference
### SystemInfo
| Attribute     | Description               | Type   |
|---------------|---------------------------|--------|
| `hostName`    | Current machine hostname  | String |
| `hostAddress` | Current machine address   | String |
| `counter`     | Incremental counter value | Long   |

### GoRestUser
| Attribute | Description              | Type   |
|-----------|--------------------------|--------|
| `id`      | Current machine hostname | String |
| `name`    | User name                | String |
| `email`   | User email               | String |
| `gender`  | User gender              | String |
| `status`  | User status              | String |

### GoRestPost
| Attribute | Description              | Type   |
|-----------|--------------------------|--------|
| `id`      | Current machine hostname | String |
| `userId`  | User id                  | String |
| `title`   | Post title               | String |
| `body`    | Post body                | String |

### AppUser
| Attribute | Description             | Type   |
|-----------|-------------------------|--------|
| `id`      | Internal unique user id | Long   |
| `name`    | User name               | String |
| `email`   | User email              | String |
| `phone`   | User phone              | String |

### AddUserRequest
| Attribute | Description             | Type   |
|-----------|-------------------------|--------|
| `name`    | User name               | String |
| `email`   | User email              | String |
| `phone`   | User phone              | String |

### HealthResponse
| Attribute | Description                  | Type   |
|-----------|------------------------------|--------|
| `status`  | Current app status (UP/DOWN) | String |

## Metrics Reference
The metrics are exposed in Prometheus format over `/actuator/prometheus` endpoint, on the default HTTP port.
Following are the most relevant metrics which may be used while evaluating the performance of the app:

| Metric Name                           | Description                              | Type    |
|---------------------------------------|------------------------------------------|---------|
| `http_server_requests_seconds_max`    | HTTP Server max response time in seconds | Gauge   |
| `http_server_requests_seconds_bucket` | HTTP Server response times percentiles   | Summary |
| `http_server_requests_seconds_count`  | HTTP Server requests count               | Summary |
| `http_server_requests_seconds_sum`    | HTTP Server requests timing sum          | Summary |
| `feign_Client_seconds_max`            | HTTP Client requests max response time   | Gauge   |
| `feign_Client_seconds_count`          | HTTP Client requests count               | Summary |
| `feign_Client_seconds_sum`            | HTTP Client requests timing sum          | Summary |
