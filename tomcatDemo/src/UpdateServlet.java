
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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class jsonData
 */
@WebServlet(name = "update", urlPatterns = { "/update" })
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
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
		String index[] = request.getParameterValues("myIndex");
		String id[] = request.getParameterValues("id");
		String name[] = request.getParameterValues("name");
		String years[] = request.getParameterValues("years");
		String teamName[] = request.getParameterValues("teamName");

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

					if ((i >=(Integer.parseInt(thisPage)-1) * 10 && i< (index.length + ((Integer.parseInt(thisPage) - 1) * 10))) ) {

						m = new LinkedHashMap(4);
						int j = i-((Integer.parseInt(thisPage) - 1) * 10);
						if (id[j] != "") {
							m.put("id", Long.parseLong(id[j]));
						} else {
							m.put("id", null);
						}

						m.put("name", name[j]);

						if (years[j] != "") {
							m.put("years", Long.parseLong(years[j]));
						} else {
							m.put("years", null);
						}

						m.put("teamName", teamName[j]);

						updateJa.add(m);

					} else {
						m = new LinkedHashMap(4);
						JSONObject json_obj = (JSONObject) ja.get(i);
						Long json_id = (Long) json_obj.get("id");
						String json_name = (String) json_obj.get("name");
						Long json_years = (Long) json_obj.get("years");
						String json_teamName = (String) json_obj.get("teamName");

						m.put("id", json_id);
						m.put("name", json_name);
						m.put("years", json_years);
						m.put("teamName", json_teamName);

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
