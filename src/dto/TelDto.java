package dto;

public class TelDto {
    public Long id;
    private String name;
    private int age;
    private String address;
    private String telNumber;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public TelDto(Long id, String name, int age, String address, String telNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.telNumber = telNumber;
    }
}
