package com.third;

import javafx.beans.property.Property;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;

/**
 * The type Hello controller.
 */
public class HelloController {
    /**
     * The Figura aktywna.
     */
    int figuraAktywna = 0;
    /**
     * The Paint.
     */
    final Paint[] paint = new Paint[1];
    /**
     * The Punkt przesuniecia x.
     */
    double punktPrzesunieciaX = 0;
    /**
     * The Punkt przesuniecia y.
     */
    double punktPrzesunieciaY = 0;
    /**
     * The Przesuniecie 0.
     */
    double przesuniecie0;
    /**
     * The Przesuniecie 1.
     */
    double przesuniecie1;
    /**
     * The Przesuniecie 2.
     */
    double przesuniecie2;
    /**
     * The Przesuniecie 3.
     */
    double przesuniecie3;
    /**
     * The Przesuniecie 4.
     */
    double przesuniecie4;
    /**
     * The Przesuniecie 5.
     */
    double przesuniecie5;

    @FXML
    private ColorPicker chooseColor;

    @FXML
    private MenuItem chooseDowolne;

    @FXML
    private MenuItem chooseKolo;

    @FXML
    private MenuItem chooseKwadrat;

    @FXML
    private MenuItem chooseLinia;

    @FXML
    private MenuItem chooseTrojkat;

    @FXML
    private Slider skalaObracania;

    @FXML
    private BorderPane oknoAplikacji;

    @FXML
    private Pane drawPlace;

    /**
     * The Chosen object.dsfafads
     */
    Shape chosenObject;

    /**
     * Obroc figure.
     *
     * @param event the event
     */
    @FXML
    void obrocFigure(MouseEvent event) {
        skalaObracania.setMin(0);
        skalaObracania.setMax(360);
        skalaObracania.setShowTickMarks(true);
        skalaObracania.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (skalaObracania.isValueChanging() && chosenObject != null) {
                    chosenObject.setRotate(skalaObracania.getValue());
                }
            }
        });
    }


    /**
     * Draw dowolne.
     *
     * @param event the event
     */
    @FXML
    void drawDowolne(ActionEvent event) {
        Canvas canvas = new Canvas(580,550);
        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        canvas.setOnMousePressed( new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                graphicsContext.setStroke(chooseColor.getValue());
                graphicsContext.beginPath();
                graphicsContext.moveTo(event.getX(), event.getY());
                graphicsContext.stroke();
            }
        });
        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                graphicsContext.lineTo(event.getX(), event.getY());
                graphicsContext.stroke();
            }
        });
        canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                canvas.setOnMousePressed(null);
                canvas.setOnMouseDragged(null);
                canvas.setOnMouseReleased(null);
            }
        });
        drawPlace.getChildren().add(canvas);
    }

    /**
     * Draw linia.
     *
     * @param event the event
     */
    @FXML
    void drawLinia(ActionEvent event) {
        Line line = new Line();
        drawPlace.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                line.setStroke(chooseColor.getValue());
                line.setVisible(false);
                line.setEndX(mouseEvent.getX());
                line.setEndY(mouseEvent.getY());
                drawPlace.setOnMousePressed(null);
            }
        });
        drawPlace.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                line.setVisible(true);
                line.setStartX(mouseEvent.getX());
                line.setStartY(mouseEvent.getY());
            }
        });
        drawPlace.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                drawPlace.setOnMouseDragged(null);
                line.setStartX(mouseEvent.getX());
                line.setStartY(mouseEvent.getY());
                drawPlace.setOnMouseReleased(null);
            }
        });
        drawPlace.getChildren().add(line);
    }

    /**
     * The Last clicked.
     */
    static long lastClicked = 0;

    /**
     * Draw kolo.
     *
     * @param event the event
     */
    @FXML
    void drawKolo(ActionEvent event) {
        Circle kolo = new Circle();
        drawPlace.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                chosenObject = null;
                kolo.setStroke(Color.BLACK);
                kolo.setFill(chooseColor.getValue());
                kolo.setCenterX(mouseEvent.getX());
                kolo.setCenterY(mouseEvent.getY());
                drawPlace.setOnMousePressed(null);
            }
        });
        drawPlace.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                kolo.setRadius(Math.sqrt(Math.pow(kolo.getCenterX() - mouseEvent.getX(),2) + Math.pow(kolo.getCenterY() - mouseEvent.getY(), 2)));
            }
        });
        drawPlace.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                kolo.setRadius(Math.sqrt(Math.pow(kolo.getCenterX() - mouseEvent.getX(),2) + Math.pow(kolo.getCenterY() - mouseEvent.getY(), 2)));
                drawPlace.setOnMouseDragged(null);
                drawPlace.setOnMouseReleased(null);
            }
        });
        drawPlace.getChildren().add(kolo);

        kolo.setOnMouseClicked(new EventHandler<MouseEvent>() {                         // choose object KOLO
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (chosenObject == null) {
                    kolo.setEffect(new Glow());
                    chosenObject = kolo;
                } else if (chosenObject == kolo){
                    kolo.setEffect(null);
                    chosenObject = null;
                }
            }
        });

        kolo.setOnMousePressed(new EventHandler<MouseEvent>() {                     // move KOLO
            @Override
            public void handle(MouseEvent mouseEvent) {
                punktPrzesunieciaX = mouseEvent.getX() - kolo.getCenterX();
                punktPrzesunieciaY = mouseEvent.getY() - kolo.getCenterY();
            }
        });
        kolo.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (chosenObject == kolo) {
                    Figury.trzyFigury.KOLO.move(kolo, punktPrzesunieciaX, punktPrzesunieciaY);
                }
            }
        });

        kolo.setOnScroll(new EventHandler<ScrollEvent>() {              // resize KOLO
            @Override
            public void handle(ScrollEvent scrollEvent) {
                if (chosenObject == kolo) {
                    Figury.trzyFigury.KOLO.resize(kolo);
                }
            }
        });
    }


    /**
     * Draw trojkat.
     *
     * @param event the event
     */
    @FXML
    void drawTrojkat(ActionEvent event) {
        Polygon trojkat = new Polygon();
        drawPlace.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                chosenObject = null;
                trojkat.setFill(chooseColor.getValue());
                trojkat.setStroke(Color.BLACK);
                trojkat.getPoints().addAll(mouseEvent.getX(), mouseEvent.getY(),
                        mouseEvent.getX(), mouseEvent.getY(),
                        mouseEvent.getX(), mouseEvent.getY());
                drawPlace.setOnMousePressed(null);
            }
        });
        drawPlace.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                trojkat.getPoints().set(3, mouseEvent.getY());
                if (mouseEvent.getX()>trojkat.getPoints().get(0)) {
                    trojkat.getPoints().set(2, mouseEvent.getX());
                    trojkat.getPoints().set(4, trojkat.getPoints().get(0) - Math.abs(mouseEvent.getX() - trojkat.getPoints().get(0)));
                } else {
                    trojkat.getPoints().set(2, trojkat.getPoints().get(0) + Math.abs(mouseEvent.getX() - trojkat.getPoints().get(0)));
                    trojkat.getPoints().set(4, mouseEvent.getX());
                }
                trojkat.getPoints().set(5, mouseEvent.getY());
            }
        });
        drawPlace.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                drawPlace.setOnMouseDragged(null);
                drawPlace.setOnMouseReleased(null);
            }
        });
        drawPlace.getChildren().add(trojkat);               //koniec rysowania figury

        final Paint[] paint = new Paint[1];
        trojkat.setOnMouseClicked(new EventHandler<MouseEvent>() {                         // choose object KOLO
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (chosenObject == null) {
                    trojkat.setEffect(new Glow());
                    chosenObject = trojkat;
                } else if (chosenObject == trojkat){
                    trojkat.setEffect(null);
                    chosenObject = null;
                }
            }
        });
        trojkat.setOnMousePressed(new EventHandler<MouseEvent>() {                          // move TROJKAT
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (chosenObject == trojkat) {
                    przesuniecie0 = mouseEvent.getX() - trojkat.getPoints().get(0);
                    przesuniecie1 = mouseEvent.getY() - trojkat.getPoints().get(1);
                    przesuniecie2 = mouseEvent.getX() - trojkat.getPoints().get(2);
                    przesuniecie3 = mouseEvent.getY() - trojkat.getPoints().get(3);
                    przesuniecie4 = mouseEvent.getX() - trojkat.getPoints().get(4);
                    przesuniecie5 = mouseEvent.getY() - trojkat.getPoints().get(5);
                }
            }
        });
        trojkat.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                trojkat.getPoints().set(0, mouseEvent.getX() - przesuniecie0);
                trojkat.getPoints().set(2, mouseEvent.getX() - przesuniecie2);
                trojkat.getPoints().set(4, mouseEvent.getX() - przesuniecie4);
                trojkat.getPoints().set(1, mouseEvent.getY() - przesuniecie1);
                trojkat.getPoints().set(3, mouseEvent.getY() - przesuniecie3);
                trojkat.getPoints().set(5, mouseEvent.getY() - przesuniecie5);
            }
        });
        trojkat.setOnScroll(new EventHandler<ScrollEvent>() {                      // resize TROJKAT
            @Override
            public void handle(ScrollEvent scrollEvent) {
                if (chosenObject == trojkat) {
                    Figury.trzyFigury.TROJKAT.resize(trojkat);
                }
            }
        });
    }

    /**
     * Draw kwadrat.
     *
     * @param event the event
     */
    @FXML                                                                           //KWADRAT
    void drawKwadrat(ActionEvent event) {
        Rectangle kwadrat = new Rectangle();
        drawPlace.setOnMousePressed(new EventHandler<MouseEvent>(){                 //poczatek tworzenia figury
            @Override
            public void handle(MouseEvent mouseEvent) {
                chosenObject = null;
                kwadrat.setFill(chooseColor.getValue());
                kwadrat.setStroke(Color.BLACK);
                kwadrat.setX(mouseEvent.getX());
                kwadrat.setY(mouseEvent.getY());
                punktPrzesunieciaX = kwadrat.getX();
                punktPrzesunieciaY = kwadrat.getY();
                drawPlace.setOnMousePressed(null);
            }
        });
        drawPlace.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getX() > punktPrzesunieciaX){
                    kwadrat.setWidth(Math.abs(kwadrat.getX() - mouseEvent.getX()));
                } else {
                    kwadrat.setX(mouseEvent.getX());
                    kwadrat.setWidth(Math.abs(kwadrat.getX() - punktPrzesunieciaX));
                }
                if (mouseEvent.getY() > punktPrzesunieciaY){
                    kwadrat.setHeight(Math.abs(kwadrat.getY() - mouseEvent.getY()));
                } else {
                    kwadrat.setY(mouseEvent.getY());
                    kwadrat.setHeight(Math.abs(kwadrat.getY() - punktPrzesunieciaY));
                }
            }
        });
        drawPlace.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                drawPlace.setOnMouseDragged(null);
                drawPlace.setOnMouseReleased(null);                              //koniec tworzenia figury
            }
        });
        drawPlace.getChildren().add(kwadrat);

        kwadrat.setOnMouseDragEntered(new EventHandler<MouseEvent>() {        // get active PROSTOKAT
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (chosenObject == null){
                    chosenObject = kwadrat;
                    kwadrat.setEffect(new Glow());
                    punktPrzesunieciaX = Math.abs( mouseEvent.getX() - kwadrat.getX());
                    punktPrzesunieciaY = Math.abs( mouseEvent.getY() - kwadrat.getY());
                } else if (chosenObject == kwadrat){
                    kwadrat.setEffect(null);
                    chosenObject = null;
                }
            }
        });
        kwadrat.setOnMouseDragged(new EventHandler<MouseEvent>() {          //przesuniecie PROSTOKAT
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (chosenObject == kwadrat) {
                    Figury.trzyFigury.PROSTOKAT.move(kwadrat, punktPrzesunieciaX, punktPrzesunieciaY);
                }
            }
        });
        kwadrat.setOnScroll(new EventHandler<ScrollEvent>() {               // powiekszenie PROSTOKAT
            @Override
            public void handle(ScrollEvent scrollEvent) {
                if (chosenObject == kwadrat) {
                    Figury.trzyFigury.PROSTOKAT.resize(kwadrat);
                }
            }
        });
    }
}

//todo border dla drawplace
//todo dokumentacja
//todo instrukcja
//todo move kolo, prostokat
// todo zrobić create dla figur ?
// todo zeby nie zmieniało kolor jak sie rysuje inna figure na poprzedniej
//todo punkty przesuniecia jako tablica

