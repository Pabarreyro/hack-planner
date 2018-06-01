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
}