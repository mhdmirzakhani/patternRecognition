# Pattern Recognition

### Short Glance
Computer vision involves analyzing patterns in visual images and reconstructing the real world objects that produced them. The process in often broken up into two phases:
- feature detection
- pattern recognition.

Feature detection involves selecting important features of the image; pattern recognition involves discovering patterns in the features.
We will investigate a particularly clean pattern recognition problem involving points and line segments.
This kind of pattern recognition arises in many other applications, for example statistical data analysis.

- ✨Magic ✨

## Problem
Given a set of N feature points in the plane, determine every line that contains N or more of the points, and
return all points involved.

## ✨ Our Solution ✨
At the first, we consider a point(first point inserted) as a pivot.
Then we calculate the angle between this pivot point and all other points.
If we find a set of points that make a same angle with our pivot, this means that this set of points can be a line!
Also there is parameter called N. This integer parameter specify the length of our lines. E.g if we conside N = 3 it means  we need lines including at least 3 points or more.


## How to use?
Here we have a simple REST API that you can do some simple things with:

```sh
Add a point to the space
POST /point with body { "x": ..., "y": ... } 
```

```sh
Get all points in the space
GET /space
```

```sh
Get all line segments passing through at least N point
GET /lines/{n}
```

```sh
Remove all points from the space
DELETE /space
```

##  Used Tech

| TITLE | INFO |
| ------ | ------ |
| JAVA | [http://java.com][jlu] |
| Spring Boot | [http://spring.io/projects/spring-boot][spfu] |
| Maven | [https://maven.apache.org/][mvu] |
| Tea | [https://en.wikipedia.org/wiki/Tea][teu] |


## License


**Free Software, Hell Yeah!**

   [spfu]: <http://spring.io/projects/spring-boot>
   [jlu]: <https://java.com>
   [mvu]: <https://maven.apache.org/>
   [teu]: <https://en.wikipedia.org/wiki/Tea>
  