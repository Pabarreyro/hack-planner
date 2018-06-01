import java.util.HashMap;

import models.Team;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("teams", Team.getAll());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/new", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/new", (req, res) -> {
            String inputName = req.queryParams("name");
            String inputProduct = req.queryParams("product");
            String inputMember = req.queryParams("member");
            Team newTeam = new Team(inputName, inputMember, inputProduct);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/teams/:id", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            int searchId = Integer.parseInt(req.params("id"));
            Team targetTeam = Team.findById(searchId);
            model.put("team", targetTeam);
            return new ModelAndView(model, "team-details.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/:id/update", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            int searchId = Integer.parseInt(req.params("id"));
            Team targetTeam = Team.findById(searchId);
            model.put("editTeam", targetTeam);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/:id/update", (req, res) -> {
            int searchId = Integer.parseInt(req.params("id"));
            Team updatedTeam = Team.findById(searchId);
            String redirectPath = "/teams/" + req.params("id");
            if (!req.queryParams("name").equals("")){
                String updatedName = req.queryParams("name");
                updatedTeam.setName(updatedName);
            }
            if (!req.queryParams("product").equals("")){
                String updatedProduct = req.queryParams("product");
                updatedTeam.setProduct(updatedProduct);
            }
            if (!req.queryParams("member").equals("")) {
                String updatedMember = req.queryParams("member");
                updatedTeam.setMembers(updatedMember);
            }
            res.redirect(redirectPath);
            return null;
        }, new HandlebarsTemplateEngine());

        get("/about", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("registeredTeams", Team.getAll().size());
            return new ModelAndView(model, "about.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
