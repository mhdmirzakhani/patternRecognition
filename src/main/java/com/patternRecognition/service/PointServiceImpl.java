package com.patternRecognition.service;

import com.patternRecognition.Main;
import com.patternRecognition.model.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PointServiceImpl implements PointService{
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
