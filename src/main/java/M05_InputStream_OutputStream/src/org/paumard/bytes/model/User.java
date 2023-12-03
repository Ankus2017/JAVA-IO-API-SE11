package M05_InputStream_OutputStream.src.org.paumard.bytes.model;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -8174243983776052960L;

    private String firstName;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.firstName = name;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", age=" + age +
                '}';
    }
}
