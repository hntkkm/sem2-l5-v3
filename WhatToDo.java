package com.third;

import javafx.scene.shape.Shape;

public interface WhatToDo {
    void create (Shape shape);
    void resize(Shape shape);
    void move(Shape shape, double punktX, double punktY);
    void rotate(Shape shape);
}
