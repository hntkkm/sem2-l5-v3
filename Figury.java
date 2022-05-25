package com.third;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.*;

public class Figury {
    protected enum trzyFigury implements WhatToDo{
        KOLO(){
            @Override
            public void create(Shape shape) {

            }

            @Override
            public void resize(Shape shape) {
                shape.setOnScroll(new EventHandler<ScrollEvent>() {
                    @Override
                    public void handle(ScrollEvent scrollEvent) {
                        if (shape instanceof Circle circle ) {
                            double scale = 0;
                            if (scrollEvent.getDeltaY() < 0) {
                                scale = -0.1;
                            } else {
                                scale = 0.1;
                            }
                            circle.setScaleX(circle.getScaleX() + scale);
                            circle.setScaleY(circle.getScaleY() + scale);
                        } else {
                            throw new IllegalStateException();
                        }
                    }
                });

            }

            @Override
            public void move(Shape shape, double punktX, double punktY) {
                shape.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (shape instanceof Circle circle ) {
                            circle.setCenterX(mouseEvent.getX() - punktX);
                            circle.setCenterY(mouseEvent.getY() - punktY);
                        } else {
                            throw new IllegalStateException();
                        }
                    }
                });
            }

            @Override
            public void rotate(Shape shape) {

            }
        },
        TROJKAT(){
            @Override
            public void create(Shape shape) {

            }

            @Override
            public void resize(Shape shape) {
                shape.setOnScroll(new EventHandler<ScrollEvent>() {
                    @Override
                    public void handle(ScrollEvent scrollEvent) {
                        if (shape instanceof Polygon triangle ) {
                            double scale = 0;
                            if (scrollEvent.getDeltaY() < 0){
                                scale = -0.1;
                            } else {
                                scale = 0.1;
                            }
                            triangle.setScaleX(triangle.getScaleX() + scale);
                            triangle.setScaleY(triangle.getScaleY() + scale);
                        } else {
                            throw new IllegalStateException();
                        }
                    }
                });
            }

            @Override
            public void move(Shape shape, double punktX, double punktY) {

            }

            @Override
            public void rotate(Shape shape) {

            }
        },
        PROSTOKAT(){
            @Override
            public void create(Shape shape) {

            }

            @Override
            public void resize(Shape shape) {
                shape.setOnScroll(new EventHandler<ScrollEvent>() {
                    @Override
                    public void handle(ScrollEvent scrollEvent) {
                        if (shape instanceof Rectangle rectangle ) {
                            double scale = 0;
                            if (scrollEvent.getDeltaY() < 0){
                                scale = -0.1;
                            } else {
                                scale = 0.1;
                            }
                            rectangle.setScaleX(rectangle.getScaleX() + scale);
                            rectangle.setScaleY(rectangle.getScaleY() + scale);
                        } else {
                            throw new IllegalStateException();
                        }
                    }
                });
            }

            @Override
            public void move(Shape shape, double punktPrzesunieciaX, double punktPrzesunieciaY) {
                shape.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (shape instanceof Rectangle rectangle ) {
                            rectangle.setX(mouseEvent.getX() + rectangle.getWidth() -  punktPrzesunieciaX);
                                System.out.println("first");
                            rectangle.setY(mouseEvent.getY() - punktPrzesunieciaY);
                        } else {
                            throw new IllegalStateException();
                        }
                    }
                });
            }

            @Override
            public void rotate(Shape shape) {

            }
        }

    }
}
