package com.mawitnow.tondeuse.application.executors;

import com.mawitnow.tondeuse.application.helper.PointMoveHelper;
import com.mawitnow.tondeuse.application.models.Commands;
import com.mawitnow.tondeuse.application.models.Point;
import com.mawitnow.tondeuse.application.models.TondeuseLocationInfo;
import static com.mawitnow.tondeuse.application.models.TondeuseLocationInfo.Direction.*;

import static com.mawitnow.tondeuse.application.models.Commands.Command;


public class TondeuseExecutor {
    private TondeuseLocationInfo respawnInfo;
    private Commands commandList;

    public TondeuseExecutor(TondeuseLocationInfo respawnInfo, Commands commandList) {
        this.respawnInfo = respawnInfo;
        this.commandList = commandList;

    }

    public void execute(Point fieldDimentions){
        for(Command command : commandList.getCommandList() ){
            switch(command){
                case A:
                        move(fieldDimentions);
                    break;
                case D: rotateRight(fieldDimentions);
                    break;
                case G: rotateLeft(fieldDimentions);
                    break;
            }
        }
    }

    private void move(Point fieldDimentions) {
        switch (respawnInfo.getRespawnDirection()) {
            case E:
                if(respawnInfo.getTondeuseLocation().getPositionX()<fieldDimentions.getPositionX()) {
                    PointMoveHelper.moveXUpWards(respawnInfo.getTondeuseLocation());
                }
                break;
            case S:
                if(respawnInfo.getTondeuseLocation().getPositionY()>0) {
                    PointMoveHelper.moveYBackWards(respawnInfo.getTondeuseLocation());
                }
                break;
            case N:
                if (respawnInfo.getTondeuseLocation().getPositionY() < fieldDimentions.getPositionY()) {
                    PointMoveHelper.moveYUpWards(respawnInfo.getTondeuseLocation());
                }
                break;
            case W:
                if(respawnInfo.getTondeuseLocation().getPositionX()>0) {
                    PointMoveHelper.moveXBackWards(respawnInfo.getTondeuseLocation());
                }
                break;
        }
    }

    private void rotateRight(Point fieldDimentions) {
        switch (respawnInfo.getRespawnDirection()) {
            case E: respawnInfo.setRespawnDirection(S);
                break;
            case S: respawnInfo.setRespawnDirection(W);
                break;
            case N: respawnInfo.setRespawnDirection(E);
                break;
            case W: respawnInfo.setRespawnDirection(N);
                break;
        }

    }

    private void rotateLeft(Point fieldDimentions) {
        switch (respawnInfo.getRespawnDirection()) {
            case E: respawnInfo.setRespawnDirection(N);
                break;
            case S: respawnInfo.setRespawnDirection(E);
                break;
            case N: respawnInfo.setRespawnDirection(W);
                break;
            case W: respawnInfo.setRespawnDirection(S);
                break;
        }
    }

}
