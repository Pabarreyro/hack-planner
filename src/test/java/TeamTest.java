import models.Team;
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
    public void setMembers_addsMember_janeDoe() throws Exception{
        Team testTeam = new Team("TestTeam", "John Doe", "Test Description");
        testTeam.setMembers("Jane Doe");
        assertEquals("Jane Doe", testTeam.getMembers().get(1));
    }

    @Test
    public void setName_updatesTeamName_teamTest() throws Exception{
        Team testTeam = new Team("TestTeam", "John Doe", "Test Description");
        testTeam.setName("models.Team Test");
        assertEquals("models.Team Test", testTeam.getName());
    }

    @Test
    public void getAll_returnsAllTeams_2() {
        Team.clearAll();
        Team testTeam = new Team("TestTeam", "John Doe", "Test Description");
        Team testTeam2 = new Team("TestTeam", "Jane Doe", "Test Description");
        assertEquals(2, Team.getAll().size());
    }

    @Test
    public void findById_returnsTeamByIdCorrectly_3() {
        Team.clearAll();
        Team testTeam = new Team("TestTeam", "John Doe", "Test Description");
        Team testTeam2 = new Team("TestTeam", "Jane Doe", "Test Description");
        Team testTeam3 = new Team("TestTeam", "Jose Doe", "Test Description");
        assertEquals(3, Team.findById(testTeam3.getId()).getId());
    }
}