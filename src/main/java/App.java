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

        // get: display all teams and members
        get("/", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            List<Team> teams = teamDao.getAll();
            List<Member> members = memberDao.getAll();
            model.put("teams", teams);
            model.put("members", members);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        // get: display new team form
        get("/teams/new", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        // post: submit category form (redirect to /)
        post("/teams", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            String inputName = req.queryParams("name");
            String inputProduct = req.queryParams("product");
            Team newTeam = new Team(inputName, inputProduct);
            teamDao.add(newTeam);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
    }
}
