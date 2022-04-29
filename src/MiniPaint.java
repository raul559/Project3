import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;


public class MiniPaint extends Application {

    //    ArrayList<ShapeCollection> shapeCollection = new ArrayList<>();
    ShapeCollection shapeCollection = new ShapeCollection();
    Slider greenSlider;
    Slider redSlider;
    Slider blueSlider;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        Label toolLabel = new Label("Tool");
        toolLabel.setId("Labels");

        Label colorLabel = new Label("Color");
        colorLabel.setId("Labels");

        Label redLabel = new Label("Red");
        redSlider = new Slider(0, 255, 0);
        Label greenLabel = new Label("Green");
        greenSlider = new Slider(0, 255, 0);
        Label blueLabel = new Label("Blue");
        blueSlider = new Slider(0, 255, 0);

        Ellipse currentColor = new Ellipse(20, 20);

        RadioButton drawButton = new RadioButton("Draw");
        RadioButton moveButton = new RadioButton("Move");
        RadioButton deleteButton = new RadioButton("Delete");
        ToggleGroup toggle = new ToggleGroup();
        drawButton.setToggleGroup(toggle);
        moveButton.setToggleGroup(toggle);
        deleteButton.setToggleGroup(toggle);

        ComboBox<String> shapeCombo = new ComboBox();
        shapeCombo.getItems().addAll("Rectangle", "Line", "Ellipse");
//        shapeCombo.setOnAction(event -> {
//            if (shapeCombo.getValue().equals("Rectangle")) {
//                Rectangle rectangle = new Rectangle();
//                shapeCollection.addShape(rectangle);
//            } else if (shapeCombo.getValue().equals("Line")) {
//                Line line = new Line();
//                shapeCollection.addShape(line);
//            } else {
//                Ellipse ellipse = new Ellipse();
//                shapeCollection.addShape(ellipse);
//            }
//        });


        VBox controlsBox = new VBox(10, toolLabel, drawButton, moveButton,
                deleteButton, shapeCombo, colorLabel, redLabel, redSlider, greenLabel, greenSlider, blueLabel, blueSlider, currentColor);

        controlsBox.setPadding(new Insets(20));
        controlsBox.setId("ControlBox");
        controlsBox.setPrefSize(200, 800);
        Pane pane = new Pane();
        pane.setPrefSize(600, 800);
        GridPane grid = new GridPane();
        grid.add(controlsBox, 0, 0);
        grid.add(pane, 1, 0);
        Scene scene = new Scene(grid);
        scene.getStylesheets().add("styleSheet.css");
        primaryStage.setScene(scene);
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);

        primaryStage.show();

        //Slider Event
        ChangeListener<Number> colorSlider = (observable, oldValue, endValue) -> {
            currentColor.setFill(getSelectedColor());
        };
        redSlider.valueProperty().addListener(colorSlider);
        greenSlider.valueProperty().addListener(colorSlider);
        blueSlider.valueProperty().addListener(colorSlider);


        EventHandler<MouseEvent> shapeEvents = event -> {
            Rectangle rectangle = new Rectangle(0, 0);
            rectangle.setFill(getSelectedColor());
            Line line = new Line();
            line.setFill(getSelectedColor());
            Ellipse ellipse = new Ellipse(0, 0);

            try {
                Shape currentShape;
                if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                    if (shapeCombo.getValue().equals("Rectangle")) {
//                        rectangle = new Rectangle(0,0);
//                        rectangle.setFill(getSelectedColor());
                        shapeCollection.addShape(rectangle);
                        pane.getChildren().add(rectangle);
                    } else if (shapeCombo.getValue().equals("Line")) {
//                        line = new Line(0,0,0,0);
//                        line.setFill(getSelectedColor());
                        shapeCollection.addShape(line);
                        pane.getChildren().add(line);
                    } else if (shapeCombo.getValue().equals("Ellipse")) {
//                        ellipse = new Ellipse(0,0,0,0);
//                        ellipse.setFill(getSelectedColor());
                        shapeCollection.addShape(ellipse);
                        pane.getChildren().add(ellipse);
                    }
                } else if (event.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
                    double movedX = event.getX();
//                    System.out.println(movedX);
                    double movedY = event.getY();
//                    System.out.println(movedY);
                    if (shapeCombo.getValue().equals("Rectangle")) {
                        rectangle.setOnMouseReleased(event1 -> System.out.println("made it here "));
                        rectangle.setX(movedX);
                        rectangle.setY(movedY);
                        rectangle.setWidth(movedX);
                        rectangle.setHeight(movedY);
                        //might work for moving or deleting an object
//                        rectangle.setOnMouseClicked(event1 -> System.out.println("test end ")
//                                );
                        pane.getChildren().add(rectangle);
                    } else if (shapeCombo.getValue().equals("Line")) {
                        line.setStartX(movedX);
                        line.setStartY(movedY);
                        line.setFill(getSelectedColor());
                        pane.getChildren().add(line);
                    } else if (shapeCombo.getValue().equals("Ellipse")) {

                    }

                }
            } catch (NullPointerException e) {
                return;
            }
        };

        EventHandler<ActionEvent> enableDisableEvent = event -> {
            if (drawButton.isSelected()) {

            }


        };

        pane.addEventHandler(MouseEvent.MOUSE_DRAGGED, shapeEvents);
        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, shapeEvents);
//        if (drawButton.isSelected()){
//            drawButton.addEventHandler(ActionEvent.ACTION, enableDisableEvent);
//        }
//        else if (!drawButton.isSelected() && deleteButton.isSelected() || moveButton.isSelected()){
//            drawButton.removeEventFilter(ActionEvent.ACTION,enableDisableEvent);
//        }
//        else if(moveButton.isSelected()){
//            moveButton.addEventHandler(ActionEvent.ACTION,enableDisableEvent);
//            drawButton.removeEventFilter(ActionEvent.ACTION,enableDisableEvent);
//            deleteButton.removeEventHandler(ActionEvent.ACTION,enableDisableEvent);
//        }
        drawButton.addEventHandler(ActionEvent.ACTION, enableDisableEvent);
        deleteButton.addEventHandler(ActionEvent.ACTION, enableDisableEvent);
        moveButton.addEventHandler(ActionEvent.ACTION, enableDisableEvent);

    }


    public Color getSelectedColor() {
        int redColor = (int) redSlider.getValue();
        int greenColor = (int) greenSlider.getValue();
        int blueColor = (int) blueSlider.getValue();

        return Color.rgb(redColor, greenColor, blueColor);
    }

    public void addShapeEvent(EventType<MouseEvent> type, EventHandler<MouseEvent> event) {
        for (int i = 0; i < shapeCollection.getAllShapes().size(); i++) {
            shapeCollection.getAllShapes().get(i).addEventHandler(type, event);
        }
    }

    public void removeShapeEvent(EventType<MouseEvent> type, EventHandler<MouseEvent> event) {
        for (int i = 0; i < shapeCollection.getAllShapes().size(); i++) {
            shapeCollection.getAllShapes().get(i).removeEventHandler(type, event);
        }
    }


}


