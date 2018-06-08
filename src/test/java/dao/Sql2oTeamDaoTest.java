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

    @Test
    public void getAll_returnsAllExistingTeams_2() {
        Team testTeam = setUpTeam();
        Team testTeam2 = new Team("Test Team West", "New Test Product");
        teamDao.add(testTeam);
        teamDao.add(testTeam2);
        assertEquals(2, teamDao.getAll().size());
    }

    @Test
    public void getAll_returnsEmptyListWhenNoTeamsExist_0() {
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void findById_returnsCorrectTeam() {
        Team testTeam = setUpTeam();
        teamDao.add(testTeam);
        int assignedId = testTeam.getId();
        assertEquals(testTeam, teamDao.findById(assignedId));
    }

    @Test
    public void update_correctlyUpdatesFields() {
        Team testTeam = setUpTeam();
        String originalName = testTeam.getName();
        teamDao.add(testTeam);
        int assignedId = testTeam.getId();
        teamDao.update(assignedId, "Test Team East", "Test Product", 0);
        Team updatedTeam = teamDao.findById(assignedId);
        assertNotEquals(originalName, updatedTeam.getName());
    }

    @Test
    public void deleteById_removesCorrectTeam() {
        Team testTeam = setUpTeam();
        Team testTeam2 = new Team("Test Team West", "New Test Product");
        teamDao.add(testTeam);
        teamDao.add(testTeam2);
        int assignedId = testTeam.getId();
        teamDao.deleteById(assignedId);
        assertTrue(teamDao.getAll().contains(testTeam2));
        assertFalse(teamDao.getAll().contains(testTeam));
    }

    @Test
    public void clearAll_removesAllTeams() {
        Team testTeam = setUpTeam();
        Team testTeam2 = new Team("Test Team West", "New Test Product");
        teamDao.add(testTeam);
        teamDao.add(testTeam2);
        teamDao.clearAll();
        assertEquals(0, teamDao.getAll().size());
    }

    public Team setUpTeam() {
        return new Team("Test Team", "Test Product");
    }
}