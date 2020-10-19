package ro.tefacprogramator;

public class CityMap {
    private int mapX;
    private int mapY;
    private char [][] map = new char[mapX][mapY];

    public int getMapX() {
        return mapX;
    }

    public void setMapX(int mapX) {
        this.mapX = mapX;
    }

    public int getMapY() {
        return mapY;
    }

    public void setMapY(int mapY) {
        this.mapY = mapY;
    }

    public char[][] getMap() {
        return map;
    }

    public void setMap(char[][] matrix2d) {
        this.map = matrix2d;
    }

}
