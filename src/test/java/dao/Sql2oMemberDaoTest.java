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

    @Test
    public void getAllByTeamId_returnsOnlyMembersBelongingToTeam_1() {
        Member testMember = setUpMember();
        Member testMember2 = new Member("Test Woman");
        testMember2.setTeamId(1);
        memberDao.add(testMember);
        memberDao.add(testMember2);
        assertEquals(1, memberDao.getAllByTeamId(1).size());
    }

    @Test
    public void getAllByTeamId_returnsEmptyListIfTeamHasNoMembers_1() {
        Member testMember = setUpMember();
        memberDao.add(testMember);
        assertEquals(0, memberDao.getAllByTeamId(1).size());
    }

    @Test
    public void findById_returnsCorrectMember() {
        Member testMember = setUpMember();
        memberDao.add(testMember);
        int assignedId = testMember.getId();
        assertEquals(testMember, memberDao.findById(assignedId));
    }

    @Test
    public void update_correctlyUpdatesFields() {
        Member testMember = setUpMember();
        int originalTeamId = testMember.getTeamId();
        memberDao.add(testMember);
        int assignedId = testMember.getId();
        memberDao.update(assignedId, testMember.getName(), 1);
        Member updatedMember = memberDao.findById(assignedId);
        assertNotEquals(originalTeamId, updatedMember.getTeamId());
    }

    @Test
    public void deleteById_removesCorrectMember() {
        Member testMember = setUpMember();
        Member testMember2 = new Member("Test Woman");
        memberDao.add(testMember);
        memberDao.add(testMember2);
        int assignedId = testMember.getId();
        memberDao.deleteById(assignedId);
        assertTrue(memberDao.getAll().contains(testMember2));
        assertFalse(memberDao.getAll().contains(testMember));
    }

    @Test
    public void clearAll_removesAllMembers() {
        Member testMember = setUpMember();
        Member testMember2 = new Member("Test Woman");
        memberDao.add(testMember);
        memberDao.add(testMember2);
        memberDao.clearAll();
        assertEquals(0, memberDao.getAll().size());
    }

    public Member setUpMember() {
        return new Member("Test Man");
    }
}