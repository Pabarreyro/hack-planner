package models;

import java.util.ArrayList;

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
}
