import org.junit.Test;

import static org.junit.Assert.*;

public class TeamTest {
    @Test
    public void newTeam_instantiatesProperly_teamDetails() throws Exception{
        Team testTeam = new Team("TestTeam", "John Doe", "Test Description");
        assertEquals("TestTeam", testTeam.getName());
        assertEquals("John Doe", testTeam.getMembers().get(0));
        assertEquals("Test Description", testTeam.getProduct());
    }

    @Test
    public void setMembers_addsMember_janeDoe() {
        Team testTeam = new Team("TestTeam", "John Doe", "Test Description");
        testTeam.setMembers("Jane Doe");
        assertEquals("Jane Doe", testTeam.getMembers().get(1));
    }

    @Test
    public void setName_updatesTeamName_teamTest() {
        Team testTeam = new Team("TestTeam", "John Doe", "Test Description");
        testTeam.setName("Team Test");
        assertEquals("Team Test", testTeam.getName());
    }
}