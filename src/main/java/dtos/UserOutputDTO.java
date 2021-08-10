package dtos;

public class UserOutputDTO {
    private String name;
    private String surname;
    private boolean gender;

    public UserOutputDTO() {

    }

    public UserOutputDTO(String name, String surname, boolean gender) {
        setName(name);
        setSurname(surname);
        setGender(gender);
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

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean getGender() {
        return gender;
    }
}
