import Dao.*;
import Exceptions.ApiException;
import Models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static spark.Spark.*;
import com.google.gson.*;

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

        //Get Department

        //Get Company News


        //Delete News

        //Delete User(Admin)

        /* Post Methods */

        //New Member
        post("/members/new","application/json",(req,res)->{
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
            DepartmentNews departmentNews = gson.fromJson(req.body(),DepartmentNews.class);
            departmentNewsImplementation.add(departmentNews);
            res.status(201);
            return gson.toJson(departmentNews);
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
    }
}
