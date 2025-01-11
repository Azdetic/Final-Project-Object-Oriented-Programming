public abstract class Member {
    private String phoneNumber;
    private String name;

    public Member(String phoneNumber, String name) {
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (" + phoneNumber + ")";
    }
}
