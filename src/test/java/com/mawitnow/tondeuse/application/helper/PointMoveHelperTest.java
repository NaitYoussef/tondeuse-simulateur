package com.mawitnow.tondeuse.application.helper;

import com.mawitnow.tondeuse.application.models.Point;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class PointMoveHelperTest {

    private Point point;

    @Before
    public void setUp(){
        point = new Point(2, 2);
    }

    @Test
    public void shouldMoveXUpWards(){
        PointMoveHelper.moveXUpWards(point);
        assertThat(point).isEqualTo(new Point(3, 2));
    }

    @Test
    public void shouldMoveXBackWards(){
        PointMoveHelper.moveXBackWards(point);
        assertThat(point).isEqualTo(new Point(1, 2));
    }

    @Test
    public void shouldMoveYUpWars(){
        PointMoveHelper.moveYUpWards(point);
        assertThat(point).isEqualTo(new Point(2, 3));
    }

    @Test
    public void shouldMoveYBackWards(){
        PointMoveHelper.moveYBackWards(point);
        assertThat(point).isEqualTo(new Point(2, 1));
    }
}
