package controller;

import model.Points;

public class Main {

    public static void main(String[] args) {
        PatternRecognition patternRecognition = new PatternRecognition();
        Points point1 = new Points(2, 2);
        Points point2 = new Points(3, 1);
        Points point3 = new Points(1, 3);
        Points point4 = new Points(2, 6);
        Points point5 = new Points(4, 12);
        Points point6 = new Points(4, 0);


        patternRecognition.isPattern(3, point1, point2, point3, point4,point5,point6);

    }

}

