package com.mawitnow.tondeuse.application.executors;

import com.mawitnow.tondeuse.application.models.Commands;
import com.mawitnow.tondeuse.application.models.Point;
import com.mawitnow.tondeuse.application.models.TondeuseLocationInfo;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static com.mawitnow.tondeuse.application.models.TondeuseLocationInfo.Direction.*;
import java.util.Arrays;

public class TondeuseExecutorTest {

    private TondeuseLocationInfo locationInfo1;
    private Point fieldDimentions;
    private int tondeusePositionX;
    private int tondeusePositionY;

    @Before
    public void setUp(){
        tondeusePositionX = 1;
        tondeusePositionY = 1;
        locationInfo1 = TondeuseLocationInfo.RespawnInfoBuilder.asRespawnInfo()
                .withRespawnLocationX(tondeusePositionX)
                .withRespawnLocationY(tondeusePositionY)
                .withDirection(TondeuseLocationInfo.Direction.E)
                .build();
        fieldDimentions = new Point(3,3);
    }

    @Test
    public void shouldRotateLeft(){
        TondeuseExecutor tondeuseExecutor = new TondeuseExecutor(locationInfo1
                , new Commands(Arrays.asList(Commands.Command.G)));
        tondeuseExecutor.execute(fieldDimentions);
        Assertions.assertThat(locationInfo1)
                .isEqualTo(TondeuseLocationInfo.RespawnInfoBuilder.asRespawnInfo()
                        .withRespawnLocationX(tondeusePositionX)
                        .withRespawnLocationY(tondeusePositionY)
                        .withDirection(TondeuseLocationInfo.Direction.N)
                        .build());
    }

    @Test
    public void shouldRotateRight(){
        TondeuseExecutor tondeuseExecutor = new TondeuseExecutor(locationInfo1
                , new Commands(Arrays.asList(Commands.Command.D)));
        tondeuseExecutor.execute(fieldDimentions);
        Assertions.assertThat(locationInfo1)
                .isEqualTo(TondeuseLocationInfo.RespawnInfoBuilder.asRespawnInfo()
                        .withRespawnLocationX(tondeusePositionX)
                        .withRespawnLocationY(tondeusePositionY)
                        .withDirection(TondeuseLocationInfo.Direction.S)
                        .build());
    }

    @Test
    public void shouldMoveNorth(){
        locationInfo1.setTondeuseDirection(N);
        TondeuseExecutor tondeuseExecutor = new TondeuseExecutor(locationInfo1
                , new Commands(Arrays.asList(Commands.Command.A)));
        tondeuseExecutor.execute(fieldDimentions);
        Assertions.assertThat(locationInfo1.getTondeuseLocation())
                .extracting("positionX", "positionY")
                .containsExactly(tondeusePositionX, tondeusePositionY + 1);
        Assertions.assertThat(locationInfo1.getTondeuseDirection()).isEqualTo(N);
    }

    @Test
    public void shouldMoveSouth(){
        locationInfo1.setTondeuseDirection(S);
        TondeuseExecutor tondeuseExecutor = new TondeuseExecutor(locationInfo1
                , new Commands(Arrays.asList(Commands.Command.A)));
        tondeuseExecutor.execute(fieldDimentions);
        Assertions.assertThat(locationInfo1.getTondeuseLocation())
                .extracting("positionX", "positionY")
                .containsExactly(tondeusePositionX, tondeusePositionY - 1);
        Assertions.assertThat(locationInfo1.getTondeuseDirection()).isEqualTo(S);
    }

    @Test
    public void shouldMoveEast(){
        locationInfo1.setTondeuseDirection(E);
        TondeuseExecutor tondeuseExecutor = new TondeuseExecutor(locationInfo1
                , new Commands(Arrays.asList(Commands.Command.A)));
        tondeuseExecutor.execute(fieldDimentions);
        Assertions.assertThat(locationInfo1.getTondeuseLocation())
                .extracting("positionX", "positionY")
                .containsExactly(tondeusePositionX + 1, tondeusePositionY);
        Assertions.assertThat(locationInfo1.getTondeuseDirection()).isEqualTo(E);
    }

    @Test
    public void shouldMoveWest(){
        locationInfo1.setTondeuseDirection(W);
        TondeuseExecutor tondeuseExecutor = new TondeuseExecutor(locationInfo1
                , new Commands(Arrays.asList(Commands.Command.A)));
        tondeuseExecutor.execute(fieldDimentions);
        Assertions.assertThat(locationInfo1.getTondeuseLocation())
                .extracting("positionX", "positionY")
                .containsExactly(tondeusePositionX - 1, tondeusePositionY);
        Assertions.assertThat(locationInfo1.getTondeuseDirection()).isEqualTo(W);
    }

    @Test
    public void shouldNotMoveBeyondEastBorders(){
        locationInfo1.setTondeuseDirection(E);
        TondeuseExecutor tondeuseExecutor = new TondeuseExecutor(locationInfo1
                , new Commands(Arrays.asList(Commands.Command.A
                , Commands.Command.A)));
        tondeuseExecutor.execute(fieldDimentions);
        Assertions.assertThat(locationInfo1.getTondeuseLocation())
                .extracting("positionX", "positionY")
                .containsExactly(tondeusePositionX + 1, tondeusePositionY);
        Assertions.assertThat(locationInfo1.getTondeuseDirection()).isEqualTo(E);
    }

    @Test
    public void shouldNotMoveBeyondWestBorders(){
        locationInfo1.setTondeuseDirection(W);
        TondeuseExecutor tondeuseExecutor = new TondeuseExecutor(locationInfo1
                , new Commands(Arrays.asList(Commands.Command.A
                , Commands.Command.A
                , Commands.Command.A)));
        tondeuseExecutor.execute(fieldDimentions);
        Assertions.assertThat(locationInfo1.getTondeuseLocation())
                .extracting("positionX", "positionY")
                .containsExactly(tondeusePositionX - 1, tondeusePositionY);
        Assertions.assertThat(locationInfo1.getTondeuseDirection()).isEqualTo(W);
    }

    @Test
    public void shouldNotMoveBeyondNorthBorders(){
        locationInfo1.setTondeuseDirection(N);
        TondeuseExecutor tondeuseExecutor = new TondeuseExecutor(locationInfo1
                , new Commands(Arrays.asList(Commands.Command.A
                , Commands.Command.A)));
        tondeuseExecutor.execute(fieldDimentions);
        Assertions.assertThat(locationInfo1.getTondeuseLocation())
                .extracting("positionX", "positionY")
                .containsExactly(tondeusePositionX, tondeusePositionY + 1);
        Assertions.assertThat(locationInfo1.getTondeuseDirection()).isEqualTo(N);
    }

    @Test
    public void shouldNotMoveBeyondSouthBorders(){
        locationInfo1.setTondeuseDirection(S);
        TondeuseExecutor tondeuseExecutor = new TondeuseExecutor(locationInfo1
                , new Commands(Arrays.asList(Commands.Command.A
                , Commands.Command.A
                , Commands.Command.A)));
        tondeuseExecutor.execute(fieldDimentions);
        Assertions.assertThat(locationInfo1.getTondeuseLocation())
                .extracting("positionX", "positionY")
                .containsExactly(tondeusePositionX, tondeusePositionY - 1);
        Assertions.assertThat(locationInfo1.getTondeuseDirection()).isEqualTo(S);
    }
}
