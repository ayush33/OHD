package resource;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import bean.User;
import services.DBInfo;
import services.converter;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/loginAdmin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bean.User target2 = (User) converter.castToClass(request,bean.User.class);
		if(target2.getName().equals("admin") && target2.getPassword().equals("admin")){
			int r = (int)( Math.random()*100000);
	    	 String sess = "admin"+"+"+r;
			Statement stmt;
			try {
				stmt = new DBInfo().con.createStatement();
			String q="insert into session(sess) values('"+sess+"')";
	    	 System.out.println(stmt.execute(q));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JSONObject rObj=new JSONObject();
	    	 try {
	    		 rObj.accumulate("username", "admin");
				rObj.accumulate("type", "admin").accumulate("sess", sess).accumulate("message", "success");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 response.setContentType("application/json");
	 		response.getWriter().print(rObj);
		}
		else{
			JSONObject rObj=new JSONObject();
	    	 try {
	    		
				rObj.accumulate("message", "failed");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 response.setContentType("application/json");
	 		response.getWriter().print(rObj);
		}
	}

}
