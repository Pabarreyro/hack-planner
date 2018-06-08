package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class MemberTest {

    @Test
    public void newMember_instantiatesProperly_name() {
        Member testMember = new Member("name", 0);
        assertEquals("name", testMember.getName());
    }

    @Test
    public void setTeamId_assignsTeamObjectCorrectly_teamName() {
        Member testMember = new Member("name", 0);
        Team testTeam = new Team("Test Team", "test description");
        testMember.setTeamId(1);
        assertEquals(1, testMember.getTeamId());
    }
}