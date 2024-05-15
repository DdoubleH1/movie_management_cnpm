package org.example.model;

public class Seat {
    private int id;
    private String row;
    private int col;

    public Seat() {
    }

    public Seat(int id, String row, int col) {
        this.id = id;
        this.row = row;
        this.col = col;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
