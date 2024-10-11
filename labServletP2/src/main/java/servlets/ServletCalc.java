package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@WebServlet(urlPatterns = "/calc")
public class ServletCalc extends HttpServlet {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error reading input: " + e.getMessage());
        }

        JsonObject jobj = gson.fromJson(sb.toString(), JsonObject.class);
        PrintWriter pw = response.getWriter();

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8"); 
try {
            double a = jobj.get("a").getAsDouble();
            double b = jobj.get("b").getAsDouble();
            String math = jobj.get("math").getAsString();
            double res = 0;

            switch (math) {
                case "+":
                    res = a + b;
                    break;
                case "-":
                    res = a - b;
                    break;
                case "*":
                    res = a * b;
                    break;
                case "/":
                        res = a / b;
                    break;  
                default:
                	throw new Exception("Wrong Data");
            }
            String jsonResponse = "{\"result\": "+res+"}";
            pw.print(jsonResponse); 
}
	catch(Exception e) {
        String jsonResponse = "{\"error\": \"Ошибка в введенных данных\"}";
        pw.print(jsonResponse);
	}
        }
    }
