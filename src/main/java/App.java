import Dao.*;
import Exceptions.ApiException;
import Models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static spark.Spark.*;
import com.google.gson.*;

import java.awt.peer.CanvasPeer;
import java.util.HashMap;
import java.util.List;
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
            Member member = memberImplementation.getById(id);
            System.out.println(id);
            System.out.println(member.getName());
            return gson.toJson(member);
        });

        //Get Department ::Good ->No.of employees
        get("/departments/:id","application/json",(req,res)->{
            int id = Integer.parseInt(req.params("id"));
            Department department = departmentImplementation.getById(id);
            System.out.println(id);
            System.out.println(department.getName());
            return gson.toJson(department);
        });

        //Get Company News
        get("/companyNews/all","application/json",(req,res)->{
           return gson.toJson(companyNewsImplementation.allCompanyNews());
        });

        //Get All Department News
        get("/departmentNews/all","application/json",(req,res)->{
            return gson.toJson(departmentNewsImplementation.getAll());
        });

        //Get All Department News At id *Not Yet*
        get("/department/:id/news","application/json",(request ,response)->{
            int id = Integer.parseInt(request.params("id"));
            return gson.toJson(departmentImplementation.getDepartmentNews(id));
        });

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
            DepartmentNews dNews = gson.fromJson(req.body(),DepartmentNews.class);
            DepartmentNews departmentNews = new DepartmentNews(dNews.getInformation(),dNews.getCategory());
            departmentNewsImplementation.add(departmentNews);
            res.status(201);
            return gson.toJson(departmentNews);
        });

        //Add news to department *Good*
        post("/department/:id/news/new","application/json",(req,res)->{
            int departmentId = Integer.parseInt(req.params("id"));
            System.out.println(departmentId);//252
            Department department = departmentImplementation.getById(departmentId);// get department
            DepartmentNews dNews = gson.fromJson(req.body(),DepartmentNews.class);
            DepartmentNews departmentNews = new DepartmentNews(dNews.getInformation(),dNews.getCategory());

            departmentNewsImplementation.add(departmentNews);//Step == null check
            System.out.println(departmentNews.getId());
            System.out.println(departmentNews.getDateCreated());
            System.out.println(departmentNews.getInformation());
            System.out.println(departmentNews.getCategory());
            System.out.println(departmentNews.type);
            res.status(201);
            return gson.toJson(departmentNews);
        });

        //Company News
        post("/companyNews/new","application/json",(req,res)->{
            CompanyNews cNews = gson.fromJson(req.body(),CompanyNews.class);
            CompanyNews companyNews = new CompanyNews(cNews.getInformation(),cNews.getCategory());
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
//            ApiException error = (ApiException) exception;
//            Map <String, Object> jsonMap = new HashMap<>();
//            jsonMap.put("status", error.getStatusCode());
//            jsonMap.put("errorMessage", error.getMessage());
//            res.type("application/json");
//            res.status(error.getStatusCode());
//            res.body(gson.toJson(jsonMap));
//        });

        //Refactor response type
        after((req,res)->{
            res.type("application/json");
        });
    }
}
