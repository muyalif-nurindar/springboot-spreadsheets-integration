package com.zkcreatives.poc.springboot.spreadsheetsintegration.configs;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.util.Collections;

@Configuration
public class GoogleSpreadsheetsApiConfig {

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    @Value("${application.google-api.spreadsheets.credential-path}")
    private String credentialPath;

    @Value("${application.google-api.spreadsheets.token-path}")
    private String tokenPath;

    @Bean
    Sheets getSheetsService() throws IOException {

        GoogleClientSecrets clientSecrets;


        try (InputStream in = new FileInputStream(credentialPath)) {
            clientSecrets = GoogleClientSecrets.load(
                    JSON_FACTORY, new InputStreamReader(in)
            );
        }

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                JSON_FACTORY,
                clientSecrets,
                Collections.singletonList(SheetsScopes.SPREADSHEETS)
        )
                .setDataStoreFactory(new FileDataStoreFactory(new File(tokenPath)))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8080).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        return new Sheets.Builder(
                new NetHttpTransport(),
                JSON_FACTORY,
                credential
        )
                .setApplicationName("Spring Sheets App")
                .build();
    }
}
