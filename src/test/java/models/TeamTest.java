package models;

import models.Team;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeamTest {
    @Test
    public void newTeam_instantiatesProperly_teamDetails() throws Exception{
        Team testTeam = new Team("TestTeam", "Test App");
        assertEquals("TestTeam", testTeam.getName());
        assertEquals("Test App", testTeam.getProduct());
    }

    @Test
    public void setName_updatesTeamName_teamTest() throws Exception{
        Team testTeam = new Team("TestTeam", "Test App");
        testTeam.setName("Team Test");
        assertEquals("Team Test", testTeam.getName());
    }

    @Test
    public void setProduct_updatesTeamProduct_teamTest() throws Exception{
        Team testTeam = new Team("TestTeam", "Test App");
        testTeam.setProduct("Better Test Description");
        assertEquals("Better Test Description", testTeam.getProduct());
    }
}