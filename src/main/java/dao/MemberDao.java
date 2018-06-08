package dao;

import models.Member;

import java.util.List;

public interface MemberDao {

    // CREATE
    void add(Member member);

    // LIST
    List<Member> getAll();
    List<Member> getAllMembersByTeam(int teamId);

    // READ
    Member findById(int id);

    // UPDATE
    void update(int id, String name, int teamId);

    // DELETE
    void deleteById(int id);
    void clearAll();
}
