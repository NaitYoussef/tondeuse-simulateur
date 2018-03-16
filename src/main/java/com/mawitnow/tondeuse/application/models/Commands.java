package com.mawitnow.tondeuse.application.models;

import java.util.ArrayList;
import java.util.List;

public class Commands {

    private List<Command> commandList;

    public Commands() {
        this.commandList = new ArrayList<Command>();
    }

    public Commands(List<Command> commandList) {
        this.commandList = commandList;
    }

    public List<Command> getCommandList() {
        return commandList;
    }

    public enum Command{
        A('A'),
        D('D'),
        G('G');

        private char value;

        private Command(char value){
            this.value = value;
        }
    }
}
