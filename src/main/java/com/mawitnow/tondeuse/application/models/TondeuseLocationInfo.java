package com.mawitnow.tondeuse.application.models;

import java.util.Objects;

public class TondeuseLocationInfo {
    private Point tondeuseLocation;
    private Direction tondeuseDirection;

    public TondeuseLocationInfo(Point respawnTondeuseLocation, Direction respawnDirection) {
        this.tondeuseLocation = respawnTondeuseLocation;
        this.tondeuseDirection = respawnDirection;
    }

    public Point getTondeuseLocation() {
        return tondeuseLocation;
    }

    public Direction getTondeuseDirection() {
        return tondeuseDirection;
    }

    public void setTondeuseLocation(Point tondeuseLocation) {
        this.tondeuseLocation = tondeuseLocation;
    }

    public void setTondeuseDirection(Direction tondeuseDirection) {
        this.tondeuseDirection = tondeuseDirection;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(obj == this) return true;
        if(obj instanceof TondeuseLocationInfo){
            TondeuseLocationInfo otherTondeuse = (TondeuseLocationInfo) obj;
            return Objects.equals(this.getTondeuseDirection(), otherTondeuse.getTondeuseDirection())
                    && Objects.equals(this.getTondeuseLocation(), otherTondeuse.getTondeuseLocation());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getTondeuseDirection(),
                this.getTondeuseLocation());
    }

    public enum Direction{
        E('E'),
        S('S'),
        N('N'),
        W('W');

        private char value;

        private Direction(char value){
            this.value = value;
        }
    }

    public static class RespawnInfoBuilder{
        private int respawnLocationX;
        private int respawnLocaitonY;
        private Direction respawnDirection;

        private RespawnInfoBuilder(){

        }

        public static RespawnInfoBuilder asRespawnInfo(){
            return new RespawnInfoBuilder();
        }

        public RespawnInfoBuilder withRespawnLocationX(int respawnLocationX){
            this.respawnLocationX = respawnLocationX;
            return  this;
        }

        public RespawnInfoBuilder withRespawnLocationY(int respawLocationY){
            this.respawnLocaitonY = respawLocationY;
            return this;
        }

        public RespawnInfoBuilder withDirection(Direction direction){
            this.respawnDirection = direction;
            return this;
        }

        public TondeuseLocationInfo build(){
            return new TondeuseLocationInfo(new Point(respawnLocationX, respawnLocaitonY)
                    , respawnDirection);
        }
    }
}


