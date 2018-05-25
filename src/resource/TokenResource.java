package resource;

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

import org.json.JSONException;
import org.json.JSONObject;

import bean.Tokens;
import services.DBInfo;
import services.Email;
import services.converter;

/**
 * Servlet implementation class TokenResource
 */
@WebServlet("/token")
public class TokenResource extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TokenResource() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sess = request.getHeader("sess");
		if(sess!=null){
			try {
				Statement stmt = new DBInfo().con.createStatement();
				String query = "select * from session where sess='"+sess+"'";
				ResultSet rs = stmt.executeQuery(query);
				if(rs.next()){
					String id = sess.substring(0,sess.indexOf('+'));
					String uType = sess.substring(sess.indexOf('+')+1,sess.indexOf('+')+2);
					if(uType.equals("u")){
						String q="select * from token where user_id='"+id+"'";
						rs = stmt.executeQuery(q);
						response.setContentType("application/json");
						response.getWriter().print(converter.getFormattedResult(rs));
					}
					else if(uType.equals("f")){
						String q="select * from token where facilitator_id='"+id+"'";
						rs = stmt.executeQuery(q);
						response.setContentType("application/json");
						response.getWriter().print(converter.getFormattedResult(rs));
					}
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
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside");
		String sess = request.getHeader("sess");
		if(sess!=null){
			try {
				Statement stmt = new DBInfo().con.createStatement();
				String query = "select * from session where sess='"+sess+"'";
				ResultSet rs = stmt.executeQuery(query);
				if(rs.next()){
					String id = sess.substring(0,sess.indexOf('+'));
					String uType = sess.substring(sess.indexOf('+')+1,sess.indexOf('+')+2);
					
					Tokens token = (Tokens) converter.castToClass(request, Tokens.class);
					
					String q="select * from providers where facility_id='"+token.getFacility_id()+"' and block_id=(select block_id from room where id ='"+token.getRoom_id()+"')";
					rs = stmt.executeQuery(q);
					rs.next();
					token.setFacilitator_id(rs.getInt("facilitator_id"));
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					System.out.println(token.getDescription());
					String q1 = "insert into token(user_id,date,facilitator_id,description,facility_id,room_id) values('"+id+"','"+sdf.format(new Date())+"','"+token.getFacilitator_id()+"','"+token.getDescription()+"','"+token.getFacility_id()+"','"+token.getRoom_id()+"')";
					stmt.execute(q1);
					rs = stmt.executeQuery("select * from facilitator where id='"+token.getFacilitator_id()+"'");
					rs.next();
					String information= "A problem arrived at "+token.getRoom_id()+" for "+token.getDescription();
					Email.sendMail(rs.getString("email"), "new Token generated", information);
					System.out.println("ssssssss");
					JSONObject rObj=new JSONObject();
				   	 try {
				   		
							rObj.accumulate("message", "success");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				   	 response.setContentType("application/json");
						response.getWriter().print(rObj);
						
				}
				else
				{
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
	            catch(Exception e)
			{
	            	e.printStackTrace();
			}
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
		
		//doGet(request, response);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sess = req.getHeader("sess");
		JSONObject rObj=new JSONObject();
		if(sess!=null){
			Statement stmt;
			try {
				stmt = new DBInfo().con.createStatement();
				String query = "select * from session where sess='"+sess+"'";
				ResultSet rs = stmt.executeQuery(query);
				if(rs.next()){
					String id = sess.substring(0,sess.indexOf('+'));
					String uType = sess.substring(sess.indexOf('+')+1,sess.indexOf('+')+2);
					Tokens token = (Tokens) converter.castToClass(req, Tokens.class);
					String q = "update token set status='"+token.getStatus()+"' where id='"+token.getId()+"'";
					stmt.execute(q);
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
		//super.doPut(req, resp);
	}

}
