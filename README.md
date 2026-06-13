# Spring Boot – Google Spreadsheets Integration (POC)

A proof-of-concept Spring Boot application that reads data from a Google Sheet via the Google Sheets API v4 and exposes it through a REST endpoint.

## Tech Stack

- Java 25 (Temurin)
- Spring Boot 4.1.0 (WebMVC)
- Google Sheets API v4 (`google-api-services-sheets`)
- OAuth 2.0 via `google-oauth-client-jetty`
- Maven 3.9

## Prerequisites

1. A Google Cloud project with the **Google Sheets API** enabled.
2. OAuth 2.0 credentials (Desktop app type) downloaded as a JSON file.
3. Java 25 and Maven 3.9 installed (or use [sdkman](https://sdkman.io/) — `.sdkmanrc` is included).

## Configuration

Edit `src/main/resources/application.yml`:

```yaml
application:
  google-api:
    spreadsheets:
      identifier: <your-spreadsheet-id>        # from the sheet URL
      data-range: <range>                       # e.g. Sheet1!A1:Z
      credential-path: <path-to-credentials.json>
      token-path: <directory-to-store-token>
```

On first run, a browser window will open for OAuth consent. The token is saved to `token-path` for subsequent runs.

## Running

```bash
./mvnw spring-boot:run
```

The app starts on port 8080. Hit `GET /` to retrieve the sheet data as a string.

## Project Structure

```
src/main/java/.../
├── Application.java                         # Entry point
├── configs/
│   └── GoogleSpreadsheetsApiConfig.java     # Sheets client bean + OAuth flow
├── contollers/
│   └── HomeController.java                  # GET / endpoint
└── services/
    ├── GoogleSpreadsheetsService.java        # Interface
    └── impl/GoogleSpreadsheetsServiceImpl.java
```

## References

- [Connecting Spring Boot to the Google Sheets API](https://medium.com/@AlexanderObregon/connecting-spring-boot-to-the-google-sheets-api-094b43712ba9)
- [Google Sheets API v4 docs](https://developers.google.com/sheets/api)