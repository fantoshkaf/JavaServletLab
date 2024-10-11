package pck;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.Model;
import logic.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@WebServlet(urlPatterns = "/del")

public class ServletDelete extends HttpServlet {
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	Model model = Model.getInstance();

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

		model.del(id); 

		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(gson.toJson(model.getFromList()));
	}
}