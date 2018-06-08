package dao;

import models.Team;
import org.sql2o.*;

import java.util.List;
import java.util.Locale;

public class Sql2oTeamDao implements TeamDao{
    private final Sql2o sql2o;

    public Sql2oTeamDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(Team team) {
        String sql = "INSERT into teams (name, memberCount, product) VALUES (:name, :memberCount, :product)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true).bind(team).executeUpdate().getKey();
            team.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Team> getAll() {
        String sql = "SELECT * FROM teams";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Team.class);
        }
    }


}
