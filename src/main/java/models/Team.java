package models;

public class Team {
    private String name;
    private int memberCount;
    private String product;
    private int id;

    public Team(String name, String product) {
        this.name = name;
        this.product = product;
        this.memberCount = 0;
    }

    public String getName() {
        return name;
    }

    public String getProduct() {
        return product;
    }

    public void setName(String teamName) {
        this.name = teamName;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getId() {
        return this.id;
    }

    public int getMemberCount() { return memberCount; }

    public void setMemberCount(int memberCount) { this.memberCount = memberCount; }
}
