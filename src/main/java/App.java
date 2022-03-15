import Dao.*;
import Exceptions.ApiException;
import Models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static spark.Spark.*;
import com.google.gson.*;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {

        Connection conn;
        AdminImplementation adminImplementation;
        MemberImplementation memberImplementation;
        DepartmentImplementation departmentImplementation;
        CompanyNewsImplementation companyNewsImplementation;
        DepartmentNewsImplementation departmentNewsImplementation;

        //Start with testing db
        String connectionString = "jdbc:postgresql://localhost:5432/organization_news_test";
        Sql2o sql2o = new Sql2o(connectionString,"marvin","nrvnqsr13");
        Gson gson = new Gson();

        adminImplementation = new AdminImplementation(sql2o);
        memberImplementation = new MemberImplementation(sql2o);
        departmentImplementation = new DepartmentImplementation(sql2o);
        companyNewsImplementation = new CompanyNewsImplementation(sql2o);
        departmentNewsImplementation = new DepartmentNewsImplementation(sql2o);
        conn = sql2o.open();

        /* Get Methods */

        //Get Member
        get("/members/:id","application/json",(req,res)->{
            int id = Integer.parseInt(req.params("id"));
            return gson.toJson(memberImplementation.getById(id));
        });

        //Get Department

        //Get Company News


        //Delete News

        //Delete User(Admin)

        /* Post Methods */

        //New Member *All Good*
        post("department/:id/members/new","application/json",(req,res)->{
            Member member = gson.fromJson(req.body(),Member.class);
            memberImplementation.add(member);
            res.status(201);
            return gson.toJson(member);
        });

        //New Department *All Good*
        post("/departments/new","application/json",(req,res)->{
            Department department = gson.fromJson(req.body(),Department.class);
            departmentImplementation.add(department);
            res.status(201);
            return gson.toJson(department);
        });

        //Department News :: Repetitive maybe
        post("/departmentNews/new","application/json",(req,res)->{
            DepartmentNews departmentNews = gson.fromJson(req.body(),DepartmentNews.class);
            departmentNewsImplementation.add(departmentNews);
            res.status(201);
            return gson.toJson(departmentNews);
        });

        //Add news to department *Good*
        post("/department/:id/news/new","application/json",(req,res)->{
            int departmentId = Integer.parseInt(req.params("id"));
            Department department = departmentImplementation.getById(departmentId);
            DepartmentNews dNews = gson.fromJson(req.body(),DepartmentNews.class);
            departmentNewsImplementation.add(dNews);
            departmentNewsImplementation.addDepartmentNews(department,dNews);
            res.status(201);
            return gson.toJson(dNews);
        });

        //Company News
        post("/companyNews/new","application/json",(req,res)->{
            CompanyNews companyNews = gson.fromJson(req.body(),CompanyNews.class);
            companyNewsImplementation.add(companyNews);
            res.status(201);
            return gson.toJson(companyNews);
        });

        //Admin
        post("/admin/me","application/json",(req,res)->{
            Admin admin = gson.fromJson(req.body(),Admin.class);
            adminImplementation.add(admin);
            res.status(201);
            return gson.toJson(admin);
        });


        //Filters

//        exception(ApiException.class, (exception, req, res) -> {
//            ApiException err = (ApiException) exception;
//            Map<String, Object> jsonMap = new HashMap<>();
//            jsonMap.put("status", err.getStatusCode());
//            jsonMap.put("errorMessage", err.getMessage());
//            res.type("application/json");
//            res.status(err.getStatusCode());
//            res.body(gson.toJson(jsonMap));
//        });

        //Refactor response type
        after((req,res)->{
            res.type("application/json");
        });
    }
}
