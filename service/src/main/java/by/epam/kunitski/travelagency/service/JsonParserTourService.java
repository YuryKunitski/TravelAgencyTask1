package by.epam.kunitski.travelagency.service;

import by.epam.kunitski.travelagency.dao.entity.Tour;

import java.io.IOException;
import java.io.InputStream;

public interface JsonParserTourService {
    Tour[] parseJsonTours(InputStream fileInputStream) throws IOException;
}
