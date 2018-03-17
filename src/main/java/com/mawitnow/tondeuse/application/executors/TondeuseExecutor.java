package com.mawitnow.tondeuse.application.executors;

import com.mawitnow.tondeuse.application.helper.PointMoveHelper;
import com.mawitnow.tondeuse.application.models.Commands;
import com.mawitnow.tondeuse.application.models.Point;
import com.mawitnow.tondeuse.application.models.TondeuseLocationInfo;
import static com.mawitnow.tondeuse.application.models.TondeuseLocationInfo.Direction.*;

import static com.mawitnow.tondeuse.application.models.Commands.Command;


public class TondeuseExecutor {
    private TondeuseLocationInfo tondeuseLocationInfo;
    private Commands commandList;

    public TondeuseExecutor(TondeuseLocationInfo tondeuseLocationInfo, Commands commandList) {
        this.tondeuseLocationInfo = tondeuseLocationInfo;
        this.commandList = commandList;
    }

    public void execute(Point fieldDimentions){
        for(Command command : commandList.getCommandList() ){
            switch(command){
                case A : move(fieldDimentions);
                    break;
                case D : rotateRight();
                    break;
                case G : rotateLeft();
                    break;
            }
        }
    }

    private void move(Point fieldDimentions) {
        switch (tondeuseLocationInfo.getTondeuseDirection()) {
            case E:
                if(tondeuseLocationInfo.getTondeuseLocation().getPositionX()
                        <fieldDimentions.getPositionX() - 1) {
                    PointMoveHelper.moveXUpWards(tondeuseLocationInfo.getTondeuseLocation());
                }
                break;
            case S:
                if(tondeuseLocationInfo.getTondeuseLocation().getPositionY() > 0) {
                    PointMoveHelper.moveYBackWards(tondeuseLocationInfo.getTondeuseLocation());
                }
                break;
            case N:
                if (tondeuseLocationInfo.getTondeuseLocation().getPositionY()
                        < fieldDimentions.getPositionY() - 1) {
                    PointMoveHelper.moveYUpWards(tondeuseLocationInfo.getTondeuseLocation());
                }
                break;
            case W:
                if(tondeuseLocationInfo.getTondeuseLocation().getPositionX() > 0) {
                    PointMoveHelper.moveXBackWards(tondeuseLocationInfo.getTondeuseLocation());
                }
                break;
        }
    }

    private void rotateRight() {
        switch (tondeuseLocationInfo.getTondeuseDirection()) {
            case E: tondeuseLocationInfo.setTondeuseDirection(S);
                break;
            case S: tondeuseLocationInfo.setTondeuseDirection(W);
                break;
            case N: tondeuseLocationInfo.setTondeuseDirection(E);
                break;
            case W: tondeuseLocationInfo.setTondeuseDirection(N);
                break;
        }

    }

    private void rotateLeft() {
        switch (tondeuseLocationInfo.getTondeuseDirection()) {
            case E: tondeuseLocationInfo.setTondeuseDirection(N);
                break;
            case S: tondeuseLocationInfo.setTondeuseDirection(E);
                break;
            case N: tondeuseLocationInfo.setTondeuseDirection(W);
                break;
            case W: tondeuseLocationInfo.setTondeuseDirection(S);
                break;
        }
    }

}
