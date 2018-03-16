package com.mawitnow.tondeuse.application.executors;

import com.mawitnow.tondeuse.application.models.Commands;
import com.mawitnow.tondeuse.application.models.Point;
import com.mawitnow.tondeuse.application.models.TondeuseLocationInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class TondeuseSimulateurTest {

    private TondeuseSimulateur tondeuseSimulateur;
    private TondeuseLocationInfo tondeuseLocationInfo1;
    private TondeuseLocationInfo tondeuseLocationInfo2;

    @Before
    public void setUp(){
        Point fieldDimentions = new Point(5,5);
        tondeuseSimulateur = new TondeuseSimulateur(fieldDimentions);
        tondeuseLocationInfo1 = TondeuseLocationInfo.RespawnInfoBuilder.asRespawnInfo()
                .withRespawnLocationX(1)
                .withRespawnLocationY(2)
                .withDirection(TondeuseLocationInfo.Direction.E)
                .build();
        Commands commandList1 =
                new Commands(Arrays.asList(Commands.Command.G
                        , Commands.Command.A, Commands.Command.G
                        , Commands.Command.A, Commands.Command.G
                        , Commands.Command.A, Commands.Command.G
                        , Commands.Command.A, Commands.Command.A));

        TondeuseExecutor executor1 = new TondeuseExecutor(tondeuseLocationInfo1
                , commandList1);



        tondeuseSimulateur.addTondeuseExecutor(executor1);

        tondeuseLocationInfo2 = TondeuseLocationInfo.RespawnInfoBuilder.asRespawnInfo()
                .withRespawnLocationX(3)
                .withRespawnLocationY(3)
                .withDirection(TondeuseLocationInfo.Direction.E)
                .build();
        Commands commandList2 =
                new Commands(Arrays.asList(Commands.Command.A, Commands.Command.A
                        , Commands.Command.D, Commands.Command.A
                        , Commands.Command.A, Commands.Command.D
                        , Commands.Command.A, Commands.Command.D
                        , Commands.Command.D, Commands.Command.A));
        TondeuseExecutor executor2 = new TondeuseExecutor(tondeuseLocationInfo2
                , commandList2);

        tondeuseSimulateur.addTondeuseExecutor(executor2);
    }

    @Test
    public void shouldReturnCorrectTondeuseLocationAfterExecute(){
        tondeuseSimulateur.execute();
        assertThat(tondeuseLocationInfo1).isEqualTo(
                TondeuseLocationInfo.RespawnInfoBuilder.asRespawnInfo()
                        .withRespawnLocationX(1)
                        .withRespawnLocationY(3)
                        .withDirection(TondeuseLocationInfo.Direction.N)
                        .build());

        assertThat(tondeuseLocationInfo2).isEqualTo(
                TondeuseLocationInfo.RespawnInfoBuilder.asRespawnInfo()
                        .withRespawnLocationX(5)
                        .withRespawnLocationY(1)
                        .withDirection(TondeuseLocationInfo.Direction.E)
                        .build());
    }




}
