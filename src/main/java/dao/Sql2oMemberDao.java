package dao;

import models.Member;
import org.sql2o.*;

import java.util.List;

public class Sql2oMemberDao implements MemberDao {
    private final Sql2o sql2o;

    public Sql2oMemberDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(Member member) {
        String sql = "INSERT into members (name, teamId) VALUES (:name, :teamId)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(member)
                    .executeUpdate()
                    .getKey();
            member.setId(id);
        }  catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Member> getAll() {
        String sql = "SELECT * FROM members";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Member.class);
        }
    }

    @Override
    public List<Member> getAllByTeamId(int teamId) {
        String sql = "SELECT * FROM members WHERE teamId = :teamId";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("teamId", teamId)
                    .executeAndFetch(Member.class);
        }
    }

    @Override
    public Member findById(int id) {
        String sql = "SELECT * FROM members WHERE id = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Member.class);
        }
    }

    @Override
    public void update(int id, String name, int teamId) {
        String sql = "UPDATE members SET (id, name, teamId) = (:id, :name, :teamId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", name)
                    .addParameter("teamId", teamId)
                    .executeUpdate();
        }  catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from members WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }  catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
