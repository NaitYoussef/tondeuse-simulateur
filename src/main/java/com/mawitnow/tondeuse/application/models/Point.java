package com.mawitnow.tondeuse.application.models;

import java.util.Objects;

public class Point {
    private int positionX;
    private int positionY;

    public Point(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;
        if(obj instanceof Point){
            Point otherPoint = (Point)obj;
            return Objects.equals(this.getPositionX(), otherPoint.getPositionX())
                    && Objects.equals(this.getPositionY(), otherPoint.getPositionY());

        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getPositionX(), this.getPositionY());
    }
}
