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

    @Before
    public void setUp(){
        locationInfo1 = TondeuseLocationInfo.RespawnInfoBuilder.asRespawnInfo()
                .withRespawnLocationX(2)
                .withRespawnLocationY(2)
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
                        .withRespawnLocationX(locationInfo1.getTondeuseLocation().getPositionX())
                        .withRespawnLocationY(locationInfo1.getTondeuseLocation().getPositionY())
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
                        .withRespawnLocationX(locationInfo1.getTondeuseLocation().getPositionX())
                        .withRespawnLocationY(locationInfo1.getTondeuseLocation().getPositionY())
                        .withDirection(TondeuseLocationInfo.Direction.S)
                        .build());
    }

    @Test
    public void shouldMoveNorth(){
        locationInfo1.setRespawnDirection(N);
        TondeuseExecutor tondeuseExecutor = new TondeuseExecutor(locationInfo1
                , new Commands(Arrays.asList(Commands.Command.A)));
        tondeuseExecutor.execute(fieldDimentions);
        Assertions.assertThat(locationInfo1.getTondeuseLocation())
                .extracting("positionX", "positionY")
                .containsExactly(2, 3);
        Assertions.assertThat(locationInfo1.getRespawnDirection()).isEqualTo(N);
    }

    @Test
    public void shouldMoveSouth(){
        locationInfo1.setRespawnDirection(S);
        TondeuseExecutor tondeuseExecutor = new TondeuseExecutor(locationInfo1
                , new Commands(Arrays.asList(Commands.Command.A)));
        tondeuseExecutor.execute(fieldDimentions);
        Assertions.assertThat(locationInfo1.getTondeuseLocation())
                .extracting("positionX", "positionY")
                .containsExactly(2, 1);
        Assertions.assertThat(locationInfo1.getRespawnDirection()).isEqualTo(S);
    }

    @Test
    public void shouldMoveEast(){
        locationInfo1.setRespawnDirection(E);
        TondeuseExecutor tondeuseExecutor = new TondeuseExecutor(locationInfo1
                , new Commands(Arrays.asList(Commands.Command.A)));
        tondeuseExecutor.execute(fieldDimentions);
        Assertions.assertThat(locationInfo1.getTondeuseLocation())
                .extracting("positionX", "positionY")
                .containsExactly(3, 2);
        Assertions.assertThat(locationInfo1.getRespawnDirection()).isEqualTo(E);
    }

    @Test
    public void shouldMoveWest(){
        locationInfo1.setRespawnDirection(W);
        TondeuseExecutor tondeuseExecutor = new TondeuseExecutor(locationInfo1
                , new Commands(Arrays.asList(Commands.Command.A)));
        tondeuseExecutor.execute(fieldDimentions);
        Assertions.assertThat(locationInfo1.getTondeuseLocation())
                .extracting("positionX", "positionY")
                .containsExactly(1, 2);
        Assertions.assertThat(locationInfo1.getRespawnDirection()).isEqualTo(W);
    }

    @Test
    public void shouldNotMoveWhenBordersBeyondBorders(){
        locationInfo1.setRespawnDirection(E);
        TondeuseExecutor tondeuseExecutor = new TondeuseExecutor(locationInfo1
                , new Commands(Arrays.asList(Commands.Command.A
                , Commands.Command.A)));
        tondeuseExecutor.execute(fieldDimentions);
        Assertions.assertThat(locationInfo1.getTondeuseLocation())
                .extracting("positionX", "positionY")
                .containsExactly(3, 2);
        Assertions.assertThat(locationInfo1.getRespawnDirection()).isEqualTo(E);
    }
}
