
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
           * Servlet implementation class jsonData
 */
@WebServlet(name = "Edit", urlPatterns = { "/Edit" })
public class EditDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditDemo() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String thisPage = request.getParameter("thisPage");
		String edit_index = request.getParameter("myIndex");
		String edit_id = request.getParameter("id");
		String edit_name = request.getParameter("name");
		String edit_years = request.getParameter("years");
		String edit_teamName = request.getParameter("teamName");

		if (thisPage == null || thisPage == "") {
			thisPage = "1";
		}

		// Read JSON AND WRITE
		Object obj;
		try {
			obj = new JSONParser().parse(new FileReader("D:\\jsonData\\TESTxample.json"));
			JSONObject jo = (JSONObject) obj;
			JSONArray ja = (JSONArray) jo.get("demo");
			JSONArray updateJa = new JSONArray();
			ArrayList<Object[]> list = new ArrayList<Object[]>();

			if (ja != null) {

				Map m = new LinkedHashMap(ja.size());

				for (int i = 0; i < ja.size(); i++) {

					if (i == Integer.parseInt(edit_index) + ((Integer.parseInt(thisPage) - 1) * 10)) {

						m = new LinkedHashMap(4);

						if (edit_id != "") {
							m.put("id", Long.parseLong(edit_id));
						} else {
							m.put("id", null);
						}

						m.put("name", edit_name);

						if (edit_years != "") {
							m.put("years", Long.parseLong(edit_years));
						} else {
							m.put("years", null);
						}

						m.put("teamName", edit_teamName);

						updateJa.add(m);

					} else {
						m = new LinkedHashMap(4);
						JSONObject json_obj = (JSONObject) ja.get(i);
						Long id = (Long) json_obj.get("id");
						String name = (String) json_obj.get("name");
						Long years = (Long) json_obj.get("years");
						String teamName = (String) json_obj.get("teamName");

						m.put("id", id);
						m.put("name", name);
						m.put("years", years);
						m.put("teamName", teamName);

						updateJa.add(m);

					}

				}
			}

			jo.put("demo", updateJa);
			PrintWriter pw = new PrintWriter("D:\\jsonData\\TESTxample.json");
			pw.write(jo.toJSONString());

			pw.flush();
			pw.close();

			RequestDispatcher disp = request.getRequestDispatcher("/TestServlet");
			disp.forward(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}
