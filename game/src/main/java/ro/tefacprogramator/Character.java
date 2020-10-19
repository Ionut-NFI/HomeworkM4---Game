package ro.tefacprogramator;

public class Character {
    private String name;
    private int[] mapPosition;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getMapPosition() {
        return mapPosition;
    }

    public void setMapPosition(int[] mapPosition) {
        this.mapPosition = mapPosition;
    }
}
