package dao;

import models.Member;
import models.Team;
import org.junit.*;
import org.sql2o.*;

import static org.junit.Assert.*;

public class Sql2oTeamDaoTest {

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
    public void add_insertsTeamAndAssignsId_newId() throws Exception {
        Team testTeam = setUpTeam();
        int initialId = testTeam.getId();
        teamDao.add(testTeam);
        assertNotEquals(initialId, testTeam.getId());
    }

    public Team setUpTeam() {
        return new Team("TestTeam", "TestProduct");
    }

    public Member setUpMember() {
        return new Member("TestMan");
    }
}