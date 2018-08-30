
import java.io.IOException;
import java.io.PrintWriter;
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
		String id[] = request.getParameterValues("id");
		String name[] = request.getParameterValues("name");
		String years[] = request.getParameterValues("years");
		String teamName[] = request.getParameterValues("teamName");
		JSONObject jo = new JSONObject();

		// for address data, first create LinkedHashMap
		Map m = new LinkedHashMap(4);
		JSONArray ja = new JSONArray();
		for (int i = 0; i < id.length; i++) {
			m = new LinkedHashMap(4);
			if (id[i] != "") {
				m.put("id", Long.parseLong(id[i]));
			} else {
				m.put("id", null);
			}
			m.put("name", name[i]);
			if (years[i] != "") {
				m.put("years", Long.parseLong(years[i]));
			} else {
				m.put("years", null);
			}
			m.put("teamName", teamName[i]);
			// adding map to list
			ja.add(m);
		}
		// putting phoneNumbers to JSONObject
		jo.put("demo", ja);
		// writing JSON to file:"JSONExample.json" in cwd
		PrintWriter pw = new PrintWriter("D:\\jsonData\\TESTxample.json");
		pw.write(jo.toJSONString());

		pw.flush();
		pw.close();

		RequestDispatcher disp = request.getRequestDispatcher("/TestServlet");
		disp.forward(request, response);
	}
}
