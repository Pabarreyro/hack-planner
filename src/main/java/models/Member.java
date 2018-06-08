package models;

import java.util.Objects;

public class Member {
    private String name;
    private int teamId;
    private int id;

    public Member(String name, int teamId) {
        this.name = name;
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {  this.name = name; }

    public int getTeamId() { return teamId; }

    public void setTeamId(int teamId) { this.teamId = teamId; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return teamId == member.teamId &&
                id == member.id &&
                Objects.equals(name, member.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, teamId, id);
    }
}
