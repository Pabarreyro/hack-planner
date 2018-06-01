package models;

import java.util.ArrayList;

public class Team {
    private String name;
    private ArrayList<String> members;
    private String product;
    private static ArrayList<Team> teams = new ArrayList<>();
    private int id;

    public Team(String name, String members, String product) {
        this.name = name;
        this.members = new ArrayList<String>(){{
            add(members);
        }};
        this.product = product;
        teams.add(this);
        this.id = teams.size();
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

    public void setProduct(String product) {
        this.product = product;
    }

    public static ArrayList<Team> getAll() {
        return teams;
    }

    public static void clearAll() {
        teams.clear();
    }

    public int getId() {
        return this.id;
    }

    public static Team findById(int id) {
        return teams.get(id - 1);
    }
}
