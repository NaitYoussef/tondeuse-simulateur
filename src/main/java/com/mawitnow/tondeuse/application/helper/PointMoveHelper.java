package com.mawitnow.tondeuse.application.helper;

import com.mawitnow.tondeuse.application.models.Point;

public class PointMoveHelper {
    public static void moveXUpWards(Point point){
        point.setPositionX(point.getPositionX() + 1);
    }

    public static void moveXBackWards(Point point){
        point.setPositionX(point.getPositionX() - 1);
    }

    public static void moveYUpWards(Point point){
        point.setPositionY(point.getPositionY() + 1);
    }

    public static void moveYBackWards(Point point){
        point.setPositionY(point.getPositionY() - 1);
    }

}
