package models;

import java.util.Objects;

public class Member {
    private String name;
    private Team team;
    private int id;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {  this.name = name; }

    public Team getTeam() { return team; }

    public void setTeam(Team team) { this.team = team; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return id == member.id &&
                Objects.equals(name, member.name) &&
                Objects.equals(team, member.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, team, id);
    }
}
