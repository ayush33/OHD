package resource;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
 * Servlet implementation class issueResource
 */
@WebServlet("/reported")
public class issueResource extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public issueResource() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Statement stmt = new DBInfo().con.createStatement();
			String query="Select * from token where status='reported'";
			ResultSet rs = stmt.executeQuery(query);
			response.setContentType("application/json");
			response.getWriter().print(converter.getFormattedResult(rs));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().append("");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sess = request.getHeader("sess");
		JSONObject rObj=new JSONObject();
		if(sess!=null){
			Statement stmt;
			try {
				stmt = new DBInfo().con.createStatement();
				String query = "select * from session where sess='"+sess+"'";
				ResultSet rs = stmt.executeQuery(query);
				if(rs.next()){
//					String id = sess.substring(0,sess.indexOf('+'));
//					String uType = sess.substring(sess.indexOf('+')+1,sess.indexOf('+')+2);
					Tokens token = (Tokens) converter.castToClass(request, Tokens.class);
					String q = "update token set status='"+token.getStatus()+"' where id='"+token.getId()+"'";
					stmt.execute(q);
					String q1="select email from facilitator where id=(select facilitator_id from token where id='"+token.getId()+"')";
					rs=stmt.executeQuery(q1);
					rs.next();
					String information = "A user has reported for token id : "+token.getId()+". Please resolve ASAP.";
					Email.sendMail(rs.getString("email"), "Warning for Reported token", information);
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
		response.setContentType("application/json");
		response.getWriter().print(rObj);
	}

}
