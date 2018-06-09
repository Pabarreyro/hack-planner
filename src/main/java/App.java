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

        // post: submit team form (redirect to /)
        post("/teams", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            String inputName = req.queryParams("name");
            String inputProduct = req.queryParams("product");
            Team newTeam = new Team(inputName, inputProduct);
            teamDao.add(newTeam);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        // post: delete all teams & members (redirect to /)
        get("/teams/delete", (req, res) -> {
            teamDao.clearAll();
            memberDao.clearAll();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        // post: delete all members (redirect to /)
        get("/members/delete", (req, res) -> {
            memberDao.clearAll();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        // post: delete team with its members (redirect to /)
        get("/teams/:id/delete", (req, res) -> {
            int teamId = Integer.parseInt(req.params("id"));
            teamDao.deleteById(teamId);
            List<Member> membersToDelete = memberDao.getAllByTeamId(teamId);
            for (Member member : membersToDelete) {
                memberDao.deleteById(member.getId());
            }
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        // post: delete member (redirect to /teams/:id)
        get("/members/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            memberDao.deleteById(id);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        // get: display single team with all related members
        get("/teams/:id", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(req.params("id"));
            model.put("team", teamDao.findById(teamId));
            model.put("members", memberDao.getAllByTeamId(teamId));
            return new ModelAndView(model, "team-details.hbs");
        }, new HandlebarsTemplateEngine());

        // get: display update form for single team
        get("/teams/:id/update", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            boolean editTeam = true;
            int teamId = Integer.parseInt(req.params("id"));
            Team team = teamDao.findById(teamId);
            model.put("editTeam", editTeam);
            model.put("team", team);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        // post: submit team update form (redirect to /teams/:id)
        post("/teams/:id/update", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.params("id"));
            String name = req.queryParams("name");
            String product = req.queryParams("product");
            int memberCount = memberDao.getAllByTeamId(id).size();
            teamDao.update(id, name, product, memberCount);
            String redirectPath = "/teams/" + id;
            res.redirect(redirectPath);
            return null;
        }, new HandlebarsTemplateEngine());

        // get: display new member form (display all Teams)
        get("/members/new", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());

        // post: submit new member form (redirect to /)
        post("/members", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            int teamId = Integer.parseInt(req.queryParams("teamId"));
            Member newMember = new Member(name, teamId);
            memberDao.add(newMember);
            teamDao.update(
                    teamId,
                    teamDao.findById(teamId).getName(),
                    teamDao.findById(teamId).getProduct(),
                    memberDao.getAllByTeamId(teamId).size()
            );
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        // get: display member update form (display all teams)
        get("/members/:id/update", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            boolean editMember = true;
            int id = Integer.parseInt(req.params("id"));
            Member member = memberDao.findById(id);
            List<Team> teams = teamDao.getAll();
            model.put("editMember", editMember);
            model.put("member", member);
            model.put("teams", teams);
            return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());

        // post: submit member update form (redirect to /teams/:id)
        post("/members/:id/update", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            int id = Integer.parseInt(req.queryParams("id"));
            int teamId = Integer.parseInt(req.queryParams("teamId"));
            memberDao.update(id, name, teamId);
            teamDao.update(
                    teamId,
                    teamDao.findById(teamId).getName(),
                    teamDao.findById(teamId).getProduct(),
                    memberDao.getAllByTeamId(teamId).size()
            );
            String redirectPath = "/teams/" + teamId;
            res.redirect(redirectPath);
            return null;
        }, new HandlebarsTemplateEngine());
    }
}
