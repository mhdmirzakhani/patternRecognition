package controller;

import model.Points;

import java.util.*;
import java.util.Map;
import java.util.stream.Collectors;

public class PatternRecognition {

    public double getAngle(Points point1, Points point2) {
        return Math.atan((point2.getY() - point1.getY()) / (point2.getX() - point1.getX()));
    }
    public void isPattern(int numberOfPoints, Points... points) {
        Map<String, Double> passedInput = new HashMap<>();

        if (numberOfPoints > points.length) {
            System.out.println("You Chose parameter N equal to "
                    + numberOfPoints
                    + " But passed "
                    + points.length
                    + " parameters as the input");
        } else {
            for (int i = 0; i < points.length - 1; i++) {
                for (int j = i + 1; j < points.length; j++) {
                    passedInput.put(
                            "Point(" +
                                    points[j].getX() + "," + points[j].getY() + ")"
                            , getAngle(points[i], points[j]));
                }
            }
        }
        for(Map.Entry<String,Double> entry: passedInput.entrySet()){
           System.out.println("Points "+entry.getKey()+" angle "+entry.getValue());
        }
    }
}

