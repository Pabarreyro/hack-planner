package dao;

import models.Team;
import org.sql2o.*;

import java.util.List;

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

    @Override
    public Team findById(int id) {
        String sql = "SELECT * FROM teams WHERE id = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Team.class);
        }
    }

    @Override
    public void update(int id, String name, String product, int memberCount) {
        String sql = "UPDATE teams SET (name, product, memberCount) = (:name, :product, :memberCount) where id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", name)
                    .addParameter("product", product)
                    .addParameter("memberCount", memberCount)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from teams WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from teams";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
