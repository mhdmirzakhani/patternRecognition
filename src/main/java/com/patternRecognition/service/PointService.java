package com.patternRecognition.service;

import com.patternRecognition.model.Point;

public interface PointService {
    boolean save(Point point);
    Iterable<Point> findAll();
    void delete();
}
