package com.mawitnow.tondeuse.application.executors;

import com.mawitnow.tondeuse.application.models.Point;

import java.util.ArrayList;
import java.util.List;

public class TondeuseSimulateur {
    private List<TondeuseExecutor> tondeuseExecutorList;
    private Point fieldDimentions;

    public TondeuseSimulateur(Point fieldDimentions){
        this.tondeuseExecutorList = new ArrayList<TondeuseExecutor>();
        this.fieldDimentions = fieldDimentions;
    }

    public void addTondeuseExecutor(TondeuseExecutor executor){
        this.tondeuseExecutorList.add(executor);
    }

    public void execute(){
        for (TondeuseExecutor tondeuseExecutor : tondeuseExecutorList){
            tondeuseExecutor.execute(fieldDimentions);
        }
    }
}
