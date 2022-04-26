public class Officers
{
    private String full_name;
    private String position;

    public Officers(String full_name, String position) {
        this.full_name = full_name;
        this.position = position;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Officers: " + full_name + ": " + position;
    }
}
