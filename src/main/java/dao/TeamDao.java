package dao;

import models.Team;

import java.util.List;

public interface TeamDao {

    // CREATE
    void add(Team team);

    // LIST
    List<Team> getAll();

    // READ
    Team findById(int id);

    // UPDATE
    void update(int id, String name, String product, int memberCount);

    // DELETE
    void deleteById(int id);
    void clearAll();
}
