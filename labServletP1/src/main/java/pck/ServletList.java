package pck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.Model;
import logic.User;

@WebServlet(urlPatterns = "/get")

public class ServletList extends HttpServlet {
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	Model model = Model.getInstance();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuffer sb = new StringBuffer();
		String line;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			System.out.println("Error");
		}

		JsonObject jobj = gson.fromJson(String.valueOf(sb), JsonObject.class);

		request.setCharacterEncoding("UTF-8");

		int id = jobj.get("id").getAsInt();

		response.setContentType("application/json;charset=utf-8");

		PrintWriter pw = response.getWriter();

		if (id == 0) {
			pw.print(gson.toJson(model.getFromList()));

		}

		else if (id > 0) {

				pw.print(gson.toJson(model.getFromList().get(id)));

			}
		
	else {
            String jsonResponse = "{\"error\": \"Id должен быть положительным\"}";
            pw.print(jsonResponse);
		}
	}

}
