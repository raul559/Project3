import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class ShapeCollection {
    private ArrayList<Line> lines;
    private ArrayList<Rectangle> rectangles;
    private ArrayList<Ellipse> ellipses;

    ShapeCollection() {
        lines = new ArrayList<>();
        rectangles = new ArrayList<>();
        ellipses = new ArrayList<>();
    }

    public void addShape(Shape shape) {
        if (shape instanceof Line) {
            lines.add((Line)shape);
        } else if (shape instanceof Rectangle) {
            rectangles.add((Rectangle) shape);
        } else if (shape instanceof Ellipse) {
            ellipses.add((Ellipse)shape);
        }
    }
    public ArrayList<Line> getLines(){
        return lines;
    }
    public ArrayList<Rectangle> getRectangles(){
        return  rectangles;
    }
    public ArrayList<Ellipse> getEllipses(){
        return ellipses;
    }
    public ArrayList<Shape> getAllShapes(){
        ArrayList<Shape> allShapes = new ArrayList<>();
        allShapes.addAll(lines);
        allShapes.addAll(rectangles);
        allShapes.addAll(ellipses);
        return allShapes;
    }


}
