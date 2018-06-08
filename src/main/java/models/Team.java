package models;

import java.util.Objects;

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

    public void setId(int id) { this.id = id; }

    public int getMemberCount() { return memberCount; }

    public void setMemberCount(int memberCount) { this.memberCount = memberCount; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return memberCount == team.memberCount &&
                id == team.id &&
                Objects.equals(name, team.name) &&
                Objects.equals(product, team.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, memberCount, product, id);
    }
}
