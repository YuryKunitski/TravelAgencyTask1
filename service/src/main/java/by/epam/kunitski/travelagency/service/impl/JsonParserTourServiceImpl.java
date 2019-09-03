package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.entity.Tour;
import by.epam.kunitski.travelagency.service.JsonParserTourService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class JsonParserTourServiceImpl implements JsonParserTourService {

    @Override
    public Tour[] parseJsonTours(InputStream fileInputStream) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper.readValue(fileInputStream, Tour[].class);
    }
}
