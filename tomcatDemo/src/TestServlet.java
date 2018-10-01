
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class jsonData
 */
@WebServlet(name = "TestServlet", urlPatterns = { "/TestServlet" })
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object obj;
		
		try {
			obj = new JSONParser().parse(new FileReader("D:\\jsonData\\TESTxample.json"));
			JSONObject jo = (JSONObject) obj;
			JSONArray ja = (JSONArray) jo.get("demo");
			ArrayList<Object[]> list = new ArrayList<Object[]>();
			int thisPage=1;
			int pageNum =1;
			if (request.getParameter("thisPage") != null && request.getParameter("thisPage") != "") {
				thisPage = Integer.parseInt(request.getParameter("thisPage"));
			}
			if (ja != null) {
				for (int i = 0; i < 10; i++) {
					int j = i + ((thisPage - 1) * 10);
					if (j >= ja.size()) {
						break;
					}
					JSONObject json_obj = (JSONObject) ja.get(j);
					Long id = (Long) json_obj.get("id");
					String name = (String) json_obj.get("name");
					Long years = (Long) json_obj.get("years");
					String teamName = (String) json_obj.get("teamName");
					Object[] jsonString = new Object[4];
					jsonString[0] = id;
					jsonString[1] = name;
					jsonString[2] = years;
					jsonString[3] = teamName;
					list.add(jsonString);
				}
				pageNum =  (ja.size()/10);
				if (ja.size()%10 != 0) {
					++pageNum;
				}
			}
			request.setAttribute("demo", list);
			request.setAttribute("type", "select");
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("thisPage", request.getParameter("thisPage"));
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
	
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
