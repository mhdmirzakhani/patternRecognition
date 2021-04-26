package com.patternRecognition.service.Impl;

import com.patternRecognition.Main;
import com.patternRecognition.model.Point;
import com.patternRecognition.service.PointService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PointServiceImpl implements PointService {
    @Override
    public boolean save(Point point) {
        return Main.points.add(point);
    }

    @Override
    public ArrayList<Point> findAll() {
        return Main.points;
    }

    @Override
    public void delete() {
        Main.points.clear();
    }
}
