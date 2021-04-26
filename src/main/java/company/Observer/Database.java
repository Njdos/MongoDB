package company.Observer;

public interface Database {
    void insertUser(String userName);
    void removeUser(String userName);
    void insert(String road);
    void remove(String road);
}
