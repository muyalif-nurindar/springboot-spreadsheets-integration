package com.zkcreatives.poc.springboot.spreadsheetsintegration.services.impl;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.zkcreatives.poc.springboot.spreadsheetsintegration.services.GoogleSpreadsheetsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GoogleSpreadsheetsServiceImpl implements GoogleSpreadsheetsService {

    private final Sheets sheetsService;

    @Value("${application.google-api.spreadsheets.identifier}")
    private String spreadsheetsId;

    @Value("${application.google-api.spreadsheets.data-range}")
    private String spreadsheetsDataRange;

    public GoogleSpreadsheetsServiceImpl(Sheets sheetsService) {
        this.sheetsService = sheetsService;
    }

    @Override
    public List<List<Object>> readSheetData() throws IOException {

        ValueRange response = sheetsService.spreadsheets().values()
                .get(spreadsheetsId, spreadsheetsDataRange)
                .execute();

        return response.getValues();
    }
}
