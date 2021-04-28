package company.Observer;

public interface Database {
    void insertUser(Person person);
    void removeUser(String userName);
    void insertRoad(Roads roads);
    void removeRoad(String roads);
    void searchSomeRoad(String searchRoad);
}
