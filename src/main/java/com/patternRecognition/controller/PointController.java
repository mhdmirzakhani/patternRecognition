package com.patternRecognition.controller;

import com.patternRecognition.model.Point;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class PointController {

    public double getAngle(Point point1, Point point2) {
        return Math.atan((point2.getY() - point1.getY()) / (point2.getX() - point1.getX()));
    }

    public void isPattern(int numberOfPoints, Point... points) {
        Map<String, Double> passedInput = new HashMap<>();

        if (numberOfPoints > points.length) {
            System.out.println("You Chose parameter N equal to "
                    + numberOfPoints
                    + " But passed "
                    + points.length
                    + " parameters as the input");
        } else {
            String pivot = "";
            for (int i = 0; i < points.length - 1; i++) {
                pivot = "Point(" + points[i].getX() + "," + points[i].getY() + ")";
                for (Point point : points) {
                    passedInput.put("Point(" + point.getX() + "," + point.getY() + ")", getAngle(points[i], point));
                }

                final List<List<Map.Entry<String, Double>>> groupedPoints =
                        passedInput.entrySet().stream().collect(Collectors.groupingBy(Map.Entry::getValue))
                                .entrySet().stream()
                                .map(Map.Entry::getValue).collect(Collectors.toList());
                for (List<Map.Entry<String, Double>> gp : groupedPoints) {
                    if (!gp.contains(Map.entry(pivot, gp.get(0).getValue()))) {
                        gp.add(Map.entry(pivot, gp.get(0).getValue()));
                    }
                }
                for (List<Map.Entry<String, Double>> groupedPoint : groupedPoints) {
                    if (groupedPoint.size() >= numberOfPoints) {
                        System.out.println(groupedPoint);
                    }
                }
            }
        }
    }
}

