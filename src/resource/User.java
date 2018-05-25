package resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import bean.Tokens;
import services.DBInfo;
import services.Email;
import services.converter;

@WebServlet("/user")
public class User extends HttpServlet
{
	private static final long serialVersionUID = 1L;
    public User()
    {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			Statement stmt = new DBInfo().con.createStatement();
			String query="Select id,name,email,phone,type from user";
			ResultSet rs = stmt.executeQuery(query);
			response.setContentType("application/json");
			response.getWriter().print(converter.getFormattedResult(rs));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().append("");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("inside");
		JSONObject rObj=new JSONObject();
		String sess = request.getHeader("sess");
		if(sess!=null){
			try {
				Statement stmt = new DBInfo().con.createStatement();
				String query = "select * from session where sess='"+sess+"'";
				ResultSet rs = stmt.executeQuery(query);
				if(rs.next()){
					String id = sess.substring(0,sess.indexOf('+'));
//					String uType = sess.substring(sess.indexOf('+')+1,sess.indexOf('+')+2);
					if(id.equals("admin")){
					bean.User user = (bean.User) converter.castToClass(request, bean.User.class);
					
					//String q="select * from providers where facility_id='"+token.getFacility_id()+"' and block_id=(select block_id from room where id ='"+token.getRoom_id()+"')";
					//rs = stmt.executeQuery(q);
					//rs.next();
					//token.setFacilitator_id(rs.getInt("facilitator_id"));
					//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					//System.out.println(token.getDescription());
					String q1 = "insert into user values('"+user.getId()+"','"+user.getName()+"','"+user.getEmail()+"','"+user.getPhone()+"','"+user.getPassword()+"','"+user.getType()+"')";
					stmt.execute(q1);
					//rs = stmt.executeQuery("select * from facilitator where id='"+token.getFacilitator_id()+"'");
					//rs.next();
					String information= "You have been successfully registered with OHD with ID:"+user.getId()+" password:"+user.getPassword();
					Email.sendMail(user.getEmail(), "Registration Successful", information);
					System.out.println("ssssssss");
					
				   	 try {
				   		
							rObj.accumulate("message", "success");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				   	 
					}
					else{
					   	 try {
						   		
								rObj.accumulate("message", "illegal");
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
						
				}
				else
				{
					
			    	 try {
			    		
						rObj.accumulate("message", "failed");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	 
				}
				}
	            catch(Exception e)
			{
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
		response.setContentType("application/json");
		response.getWriter().print(rObj);
		//doGet(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("inside");
		JSONObject rObj=new JSONObject();
		String sess = request.getHeader("sess");
		String id=request.getParameter("id");
		if(sess!=null){
			try {
				Statement stmt = new DBInfo().con.createStatement();
				String query = "select * from session where sess='"+sess+"'";
				ResultSet rs = stmt.executeQuery(query);
				if(rs.next()){
					String id1 = sess.substring(0,sess.indexOf('+'));
//					String uType = sess.substring(sess.indexOf('+')+1,sess.indexOf('+')+2);
					if(id1.equals("admin")){
					//bean.User user = (bean.User) converter.castToClass(request, bean.User.class);
					
					//String q="select * from providers where facility_id='"+token.getFacility_id()+"' and block_id=(select block_id from room where id ='"+token.getRoom_id()+"')";
					//rs = stmt.executeQuery(q);
					//rs.next();
					//token.setFacilitator_id(rs.getInt("facilitator_id"));
					//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					//System.out.println(token.getDescription());
					query = "delete from user where id='"+id+"'";
					stmt.execute(query);
					//rs = stmt.executeQuery("select * from facilitator where id='"+token.getFacilitator_id()+"'");
					//rs.next();
//					String information= "You have been successfully registered with OHD with ID:"+user.getId()+" password:"+user.getPassword();
//					Email.sendMail(user.getEmail(), "Registration Successful", information);
//					System.out.println("ssssssss");
					
				   	 try {
				   		
							rObj.accumulate("message", "success");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				   	 
					}
					else{
					   	 try {
						   		
								rObj.accumulate("message", "illegal");
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
						
				}
				else
				{
					
			    	 try {
			    		
						rObj.accumulate("message", "failed");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	 
				}
				}
	            catch(Exception e)
			{
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
		response.setContentType("application/json");
		response.getWriter().print(rObj);
		//doGet(request, response);
	}

}
