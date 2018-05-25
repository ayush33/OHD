package resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import bean.User;
import services.DBInfo;
import services.converter;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
//		StringBuilder sb = new StringBuilder();
//	    BufferedReader reader = request.getReader();
//	    try {
//	        String line;
//	        while ((line = reader.readLine()) != null) {
//	            sb.append(line).append('\n');
//	        }
//	    } finally {
//	        reader.close();
//	    }
//	    System.out.println(sb.toString());
//	     String json=sb.toString();
//	   // User garima = new ObjectMapper().readValue(json, User.class);
//	     Gson gson=new Gson();
//	     bean.User target2 = gson.fromJson(json, bean.User.class);
		
	     bean.User target2 = (User) converter.castToClass(request,bean.User.class);
	     
	     try {
			Statement stmt=new DBInfo().con.createStatement();
		
	     String query="SELECT * FROM user WHERE id='"+target2.getId()+"' and password ='"+target2.getPassword()+"'";
	     ResultSet rs=stmt.executeQuery(query);
	     if(rs.next())
	     {
	    	 String id=rs.getString("id");
	    	 int r = (int)( Math.random()*100000);
	    	 String sess = id+"+u+"+r;
	    	 String q="insert into session values('"+id+"','"+r+"','"+sess+"')";
	    	 stmt.execute(q);
	    	 JSONObject rObj=new JSONObject();
	    	 try {
	    		 rObj.accumulate("id", id);
				rObj.accumulate("type", "u").accumulate("sess", sess).accumulate("message", "success");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 response.setContentType("application/json");
	 		response.getWriter().print(rObj);
	     }
	     else{
	    	// Statement stmt=DBInfo.con.createStatement();
	 		
		     String query1="SELECT * FROM facilitator WHERE id='"+target2.getId()+"' and password ='"+target2.getPassword()+"'";
		     ResultSet rs1=stmt.executeQuery(query1);
		     if(rs1.next())
		     {
		    	 String id=rs1.getString("id");
		    	 int r = (int)( Math.random()*100000);
		    	 String sess = id+"+f+"+r;
		    	 String q="insert into session values('"+id+"','"+r+"','"+sess+"')";
		    	  stmt.execute(q);
		    	 JSONObject rObj=new JSONObject();
		    	 try {
		    		 rObj.accumulate("id", id);
					rObj.accumulate("type", "f").accumulate("sess", sess).accumulate("message", "success");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	 response.setContentType("application/json");
		    	 response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:3000");
		         response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,PUT,DELETE");
		         response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		         response.setHeader("Access-Control-Max-Age", "86400");
		         
		    	 
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
	     } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
	
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sess = req.getHeader("sess");
		JSONObject rObj=new JSONObject();
		if(sess!=null){
			Statement stmt = null;
			try {
				stmt = new DBInfo().con.createStatement();
			
			String query = "select * from session where sess='"+sess+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				query = "delete from session where sess='"+sess+"'";
				stmt.execute(query);
				
			   	 try {
			   		
						rObj.accumulate("message", "success");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			   	
			}
			else{
				try {
			   		
					rObj.accumulate("message", "failed");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
		   		
				rObj.accumulate("message", "failed");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 resp.setContentType("application/json");
			resp.getWriter().print(rObj);
		//super.doDelete(req, resp);
	}

}
