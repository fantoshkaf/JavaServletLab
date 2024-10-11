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
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@WebServlet(urlPatterns = "/put")

public class ServletPut extends HttpServlet {
	Model model = Model.getInstance();
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
		PrintWriter pw = response.getWriter();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");

		String name = jobj.get("name").getAsString();
		String surname = jobj.get("surname").getAsString();

		
		try {
			int id=jobj.get("id").getAsInt();
			double salary;
			try {
			salary = jobj.get("salary").getAsDouble();}
			catch(Exception e) {
				salary=model.getFromList().get(id).getSalary();

			}
			if(name.equals(""))
			{
				name=model.getFromList().get(id).getName();

			}
			if(surname.equals(""))
			{
				name=model.getFromList().get(id).getSurname();

			}
			User user = new User(name, surname, salary);
			model.add(user, id);

			pw.print(gson.toJson(model.getFromList().get(id)));

		} catch (Exception e) {
            String jsonResponse = "{\"error\": \"Поле id должно содержать число, а так же все поля должны быть заполнены, если пользователя не существует\"}";
            pw.print(jsonResponse);		}
		}
}