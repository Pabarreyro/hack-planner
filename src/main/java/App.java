import java.util.HashMap;
import java.util.List;

import dao.MemberDao;
import dao.Sql2oMemberDao;
import dao.Sql2oTeamDao;
import dao.TeamDao;
import models.Member;
import models.Team;
import org.sql2o.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        TeamDao teamDao = new Sql2oTeamDao(sql2o);
        MemberDao memberDao = new Sql2oMemberDao(sql2o);
        Connection conn = sql2o.open();


    }
}
