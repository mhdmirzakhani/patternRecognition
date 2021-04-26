package com.patternRecognition.controller;

import com.patternRecognition.Main;
import com.patternRecognition.model.Point;
import com.patternRecognition.service.PointService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class PointController {
    private final PointService pointService;

    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @RequestMapping(value = "/point", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Point> addPoint(@Valid @RequestBody Point point, Errors errors) {
        pointService.save(point);

        return new ResponseEntity<Point>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/space", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ArrayList<Point>> getSpace() {
        return new ResponseEntity<ArrayList<Point>>((ArrayList<Point>) this.pointService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/space", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Point> deleteSpace() {
        this.pointService.delete();

        return new ResponseEntity<Point>(HttpStatus.OK);
    }

    @RequestMapping(value = "/lines/{n}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ArrayList<ArrayList<String>>> getLines(@PathVariable("n") int n) {
        return new ResponseEntity<ArrayList<ArrayList<String>>>(findAllLines(n, Main.points), HttpStatus.OK);
    }

    /**
     * @param point1
     * @param point2
     * @return the angle between this two point
     */
    public double getAngle(Point point1, Point point2) {
        return Math.atan((point2.getY() - point1.getY()) / (point2.getX() - point1.getX()));
    }

    /**
     * @param numberOfPoints or the parameter N
     *                       this parameter specify the minimum length of expected lines
     * @param points
     * @return
     */
    private ArrayList<ArrayList<String>> findAllLines(int numberOfPoints, ArrayList<Point> points) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        Map<String, Double> passedInput = new HashMap<>();

        if (numberOfPoints > points.size()) {
            System.out.println("You Chose parameter N equal to "
                    + numberOfPoints
                    + " But passed "
                    + points.size()
                    + " parameters as the input");
        } else {
            String pivot = "";
            for (int i = 0; i < points.size() - 1; i++) {
                pivot = "Point(" + points.get(i).getX() + "," + points.get(i).getY() + ")";
                for (Point point : points) {
                    passedInput.put("Point(" + point.getX() + "," + point.getY() + ")", getAngle(points.get(i), point));
                    passedInput.put("Point(" + points.get(i).getX() + "," + points.get(i).getY() + ")", getAngle(points.get(i), point));
                }

                /**
                 * here we made sets of point
                 * these sets include all points that made a same angle with pivot
                 */
                final List<List<Map.Entry<String, Double>>> groupedPoints =
                        passedInput.entrySet().stream().collect(Collectors.groupingBy(Map.Entry::getValue))
                                .entrySet().stream()
                                .map(Map.Entry::getValue).collect(Collectors.toList());

                /**
                 * Adding pivot to the list created at the previous step
                 */
                for (List<Map.Entry<String, Double>> gp : groupedPoints) {
                    if (!gp.contains(Map.entry(pivot, gp.get(0).getValue()))) {
                        gp.add(Map.entry(pivot, gp.get(0).getValue()));
                    }
                }

                /**
                 * Returning all founded lines
                 */
                for (List<Map.Entry<String, Double>> groupedPoint : groupedPoints) {
                    if (groupedPoint.size() >= numberOfPoints) {
                        ArrayList<String> temp = new ArrayList<>();
                        temp.addAll(groupedPoint.stream().map(Map.Entry::getKey).collect(Collectors.toList()));
                        result.add(temp);
                    }
                }
            }
        }

        return result;
    }
}