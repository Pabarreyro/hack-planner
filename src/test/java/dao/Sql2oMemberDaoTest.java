package dao;

import models.Member;
import models.Team;
import org.junit.*;
import org.sql2o.*;

import static org.junit.Assert.*;

public class Sql2oMemberDaoTest {
    private Sql2oTeamDao teamDao;
    private Sql2oMemberDao memberDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        teamDao = new Sql2oTeamDao(sql2o);
        memberDao = new Sql2oMemberDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add_insertsMemberAndAssignsId_newId() {
        Member testMember = setUpMember();
        int initialId = testMember.getId();
        memberDao.add(testMember);
        assertNotEquals(initialId, testMember.getId());
    }

    @Test
    public void getAll_returnsAllExistingMembers_2() {
        Member testMember = setUpMember();
        Member testMember2 = new Member("Test Woman");
        memberDao.add(testMember);
        memberDao.add(testMember2);
        assertEquals(2, memberDao.getAll().size());
    }

    @Test
    public void getAll_returnsEmptyListWhenNoMembersExist_0() {
        assertEquals(0, memberDao.getAll().size());
    }

    public Team setUpTeam() {
        return new Team("Test Team", "Test Product");
    }

    public Member setUpMember() {
        return new Member("Test Man");
    }
}