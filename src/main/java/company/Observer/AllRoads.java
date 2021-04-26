package company.Observer;

public class AllRoads implements Observed{

    @Override
    public void addObserver() {
        System.out.println("NEW USER");
    }

    @Override
    public void removeObserver() {
        System.out.println("REMOVE USER");
    }

    @Override
    public void addRoad() { System.out.println("NEW ROAD"); }

    @Override
    public void removeRoad() {
        System.out.println("REMOVE ROAD");
    }
}