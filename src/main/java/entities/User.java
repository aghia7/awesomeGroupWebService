package entities;

public class User {
    private int id;
    private String name;
    private String surname;
    private String username;
    private boolean gender; // true - male, false - female

    public User() {

    }

    public User(String name, String surname, String username, boolean gender) {
        setName(name);
        setSurname(surname);
        setUsername(username);
        setGender(gender);
    }

    public User(int id, String name, String surname, String username, boolean gender) {
        this(name, surname, username, gender);
        setId(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender=" + gender +
                '}';
    }
}
