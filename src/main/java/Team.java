import java.util.ArrayList;

public class Team {
    private String name;
    private ArrayList<String> members;
    private String product;

    public Team(String name, String members, String product) {
        this.name = name;
        this.members = new ArrayList<String>(){{
            add(members);
        }};
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public String getProduct() {
        return product;
    }

    public void setMembers(String memberName) {
        this.members.add(memberName);
    }

    public void setName(String teamName) {
        this.name = teamName;
    }
}
