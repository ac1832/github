
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
@WebServlet(name = "YourServletURL", urlPatterns = { "/YourServletURL" })
public class YourServletURL extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public YourServletURL() {
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
		// JSONObject jo = new JSONObject();

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

					m = new LinkedHashMap(4);
					JSONObject json_obj = (JSONObject) ja.get(i);
					Long idJson = (Long) json_obj.get("id");
					String nameJson = (String) json_obj.get("name");
					Long yearsJson = (Long) json_obj.get("years");
					String teamNameJson = (String) json_obj.get("teamName");

					m.put("id", idJson);
					m.put("name", nameJson);
					m.put("years", yearsJson);
					m.put("teamName", teamNameJson);

					updateJa.add(m);

				}
			}
			// for address data, first create LinkedHashMap
			Map m = new LinkedHashMap(4);
			// JSONArray ja = new JSONArray();
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
				updateJa.add(m);
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
