package com.s22048;

public class Position {

    private Integer x;
    private Integer y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position position) {
        this.x = position.x;
        this.y = position.y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        return 31 * getX() + getY();
    }

    @Override
    public boolean equals(Object obj) {
        if(getClass() == obj.getClass()) {
            Position compared = (Position) obj;
            return getX().equals(compared.getX())
                    && getY().equals(compared.getY());
        }
        return false;
    }
}
