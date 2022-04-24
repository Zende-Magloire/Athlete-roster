public class Athlete
{
    private int ID ;
    private String FirstName;
    private String LastName;
    private int age;
    private String Race;
    private String PB;
    private String SB;

    public Athlete(int ID, String firstName, String lastName, int age, String race, String PB, String SB) {
        this.ID = ID;
        FirstName = firstName;
        LastName = lastName;
        this.age = age;
        Race = race;
        this.PB = PB;
        this.SB = SB;
    }

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public int getAge() {
        return age;
    }

    public String getRace() {
        return Race;
    }

    public String getPB() {
        return PB;
    }

    public String getSB() {
        return SB;
    }


    @Override
    public String toString() {
        return
                "[ID: " + ID +
                "] First name: " + FirstName + "\n" +
                ", Last name: " + LastName + '\n' +
                ", Age: " + age + '\n' +
                ", Event: " + Race + '\n' +
                ", PB: " + PB + '\n' +
                ", SB: " + SB + '\n';
    }
}
