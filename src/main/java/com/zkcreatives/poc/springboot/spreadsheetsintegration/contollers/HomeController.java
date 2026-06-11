package com.zkcreatives.poc.springboot.spreadsheetsintegration.contollers;

import com.zkcreatives.poc.springboot.spreadsheetsintegration.services.GoogleSpreadsheetsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController("/")
public class HomeController {

    private final GoogleSpreadsheetsService googleSpreadsheetsService;

    public HomeController(GoogleSpreadsheetsService googleSpreadsheetsService) {
        this.googleSpreadsheetsService = googleSpreadsheetsService;
    }

    @GetMapping
    String index() throws IOException {
        List<List<Object>> sheetsData = googleSpreadsheetsService.readSheetData();
        return sheetsData.toString();
    }
}
