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
        Collection<Double> temp = passedInput.values();
        Set<Set<String>> duplicates = new HashSet<Set<String>>();

        if (numberOfPoints > points.length) {
            System.out.println("You Chose parameter N equal to "
                    + numberOfPoints
                    + " But passed "
                    + points.length
                    + " parameters as the input");
        } else {
            for (int i = 0; i < points.length - 1; i++) {
                System.out.println("Now the PIVOT is " + "Point(" + points[i].getX() + "," + points[i].getY() + ")");
                for (Points point : points) {
                    passedInput.put("Point(" + point.getX() + "," + point.getY() + ")", getAngle(points[i], point));
                }
                for (Double t : temp) {
                    if (Collections.frequency(temp, t) > numberOfPoints - 1) {
                        duplicates.add(getKeysByValue(passedInput, t));
                    }
                }
                if (duplicates.isEmpty()) {
                    System.out.println("No pattern Founded!");
                } else {
                    System.out.println(duplicates);
                    duplicates.clear();
                }
            }
        }
    }

    public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
        return map.entrySet().stream().filter(e -> Objects.equals(e.getValue(), value)).map(Map.Entry::getKey).collect(Collectors.toSet());
    }
}

