package com.patternRecognition;

import com.patternRecognition.model.Point;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.ArrayList;

@SpringBootApplication
public class Main {
    public static ArrayList<Point> points = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

