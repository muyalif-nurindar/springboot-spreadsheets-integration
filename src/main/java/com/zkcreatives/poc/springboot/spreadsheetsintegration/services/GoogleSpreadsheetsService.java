package com.zkcreatives.poc.springboot.spreadsheetsintegration.services;

import java.io.IOException;
import java.util.List;

public interface GoogleSpreadsheetsService {
    List<List<Object>> readSheetData() throws IOException;
}
