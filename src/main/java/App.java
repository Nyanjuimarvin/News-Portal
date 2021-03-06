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

        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);

        Connection conn;
        AdminImplementation adminImplementation;
        MemberImplementation memberImplementation;
        DepartmentImplementation departmentImplementation;
        CompanyNewsImplementation companyNewsImplementation;
        DepartmentNewsImplementation departmentNewsImplementation;

        //Start with testing db
        Gson gson = new Gson();

        adminImplementation = new AdminImplementation(Db.sql2o);
        memberImplementation = new MemberImplementation(Db.sql2o);
        departmentImplementation = new DepartmentImplementation(Db.sql2o);
        companyNewsImplementation = new CompanyNewsImplementation(Db.sql2o);
        departmentNewsImplementation = new DepartmentNewsImplementation(Db.sql2o);
        conn = Db.sql2o.open();

        /* Get Methods */

        get("/",(req,res)->{

            Map <String,String> routes = new HashMap<>();
            routes.put("Add department","departments/new");
            routes.put("Add member","department/:departmentId/members/new");
            routes.put("Add news to department","department/:id/news/new");
            routes.put("Add Company News","companyNews/new");
            routes.put("Get All Members","members/all");
            routes.put("Get member","member/:memberId");
            routes.put("Get all departments","departments/all");
            routes.put("Get department","departments/:departmentId");
            routes.put("Get All Members in a department","departments/:id/members/all");
            routes.put("Get All company news","companyNews/all");
            routes.put("Get All Department news","departmentNews/all");
            routes.put("Get All news in a department","department/:departmentId/news");
            return gson.toJson(routes);
        });

        //All Members
        get("/members/all","application/json",(req,res)->{
            if (memberImplementation.getAllMembers().size() == 0){
                return gson.toJson("No members are added yet. Try again after adding a member");
            }
            return gson.toJson(memberImplementation.getAllMembers());
        });

        //Get Member
        get("/members/:id","application/json",(req,res)->{
            int memberId = Integer.parseInt(req.params("id"));
            if (memberImplementation.getById(memberId) == null) return gson.toJson(String.format("400: No member with the id %d exists yet.Try searching another member",memberId));
            Member member = memberImplementation.getById(memberId);
            return gson.toJson(member);
        });

        //All Members in a department
        get("/departments/:id/members/all","application/json",(req,res)->{
            int departmentId = Integer.parseInt(req.params("id"));
            if (departmentImplementation.getById(departmentId) == null ){
                return gson.toJson(String.format("400: No department with the id %d exists yet.",departmentId));
            }else if ( departmentImplementation.getAllMembersInDepartment(departmentId).size() == 0){
                return gson.toJson(String.format("400: The department with the id %d has no members.",departmentId));
            }else {
                return gson.toJson(departmentImplementation.getAllMembersInDepartment(departmentId));
            }
        });

        //Get all departments
        get("/departments/all","application/json",(req,res)->{
            if (departmentImplementation.getAllDepartments().size() == 0)
                return gson.toJson("No departments exist yet.Try again after adding one");
            return gson.toJson(departmentImplementation.getAllDepartments());
        });

        //Get Department
        get("/departments/:id","application/json",(req,res)->{
            int id = Integer.parseInt(req.params("id"));
            if (departmentImplementation.getById(id) == null){
                return gson.toJson(String.format("404: Department with the id: %d was not found",id));
            }
            return gson.toJson(departmentImplementation.getById(id));
        });


        //Get Company News
        get("/companyNews/all","application/json",(req,res)-> {
            if (companyNewsImplementation.allCompanyNews().size() == 0){
                return gson.toJson("404: No Company News Found,try adding some first");
            }
            return gson.toJson(companyNewsImplementation.allCompanyNews());
        });

        //Get All Department News
        get("/departmentNews/all","application/json",(req,res)->{
            if (departmentNewsImplementation.getAll().size() == 0){
                return gson.toJson("No department news found. Add by using the given route provided a department exists");
            }
            return gson.toJson(departmentNewsImplementation.getAll());
        });

        //Get All Department News At id
        get("/department/:id/news","application/json",(request ,response)->{
            int id = Integer.parseInt(request.params("id"));
            if (departmentImplementation.getById(id) == null){
                return gson.toJson(String.format("404: News for department with the id: %d was not found",id));
            }
            return gson.toJson(departmentImplementation.getDepartmentNews(id));
        });

        /* Post Methods */

        //New Member
        post("department/:id/members/new","application/json",(req,res)->{
            Member member = gson.fromJson(req.body(),Member.class);
            memberImplementation.add(member);
            res.status(201);
            return gson.toJson(member);
        });

        //New Department
        post("/departments/new","application/json",(req,res)->{
            Department department = gson.fromJson(req.body(),Department.class);
            departmentImplementation.add(department);
            res.status(201);
            return gson.toJson(department);
        });

        //Department News
        post("/departmentNews/new","application/json",(req,res)->{
            DepartmentNews dNews = gson.fromJson(req.body(),DepartmentNews.class);
            DepartmentNews departmentNews = new DepartmentNews(dNews.getInformation(),dNews.getCategory());
            departmentNewsImplementation.add(departmentNews);
            res.status(201);
            return gson.toJson(departmentNews);
        });

        //Add news to department
        post("/department/:id/news/new","application/json",(req,res)->{
            int departmentId = Integer.parseInt(req.params("id"));

            if(departmentImplementation.getById(departmentId) == null) {
                return gson.toJson(String.format("400 Bad Request: Could not create News as a department with the id %d does not exist",departmentId));
            }
            Department department = departmentImplementation.getById(departmentId);// get department
            DepartmentNews dNews = gson.fromJson(req.body(), DepartmentNews.class);
            DepartmentNews departmentNews = new DepartmentNews(dNews.getInformation(), dNews.getCategory());
            departmentNewsImplementation.add(departmentNews);
            departmentNewsImplementation.addDepartmentNews(department, departmentNews);
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

        //Refactor response type
        after((req,res)->{
            res.type("application/json");
        });
    }
}
