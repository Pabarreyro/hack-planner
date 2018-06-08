package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class MemberTest {

    @Test
    public void newMember_instantiatesProperly_name() {
        Member testMember = new Member("name");
        assertEquals("name", testMember.getName());
    }

    @Test
    public void setTeam_assignsTeamObjectCorrectly_teamName() {
        Member testMember = new Member("name");
        Team testTeam = new Team("Test Team", "test description");
        testMember.setTeam(testTeam);
        assertEquals("Test Team", testMember.getTeam().getName());
    }
}