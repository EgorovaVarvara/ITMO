
import utils.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;


public class ControllerServlet extends HttpServlet {


    Logger logger = Logger.getLogger(this.getClass().getName());


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.info("meow1");
        request.setCharacterEncoding("UTF-8");

        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        String json = sb.toString().trim().replace("{", "").replace("}", "").replace("x", "").replace("y", "").replace("r", "").replace(":", "").replace("\"", "").replace("flag", "");
        String x = json.split(",")[0];
        String y = json.split(",")[1];
        String r = json.split(",")[2];
        String flag = json.split(",")[3];

        request.setAttribute("x", x);
        request.setAttribute("y", y);
        request.setAttribute("r", r);
        request.setAttribute("flag", flag);

        request.getRequestDispatcher("/checkArea").forward(request, response);
    }

}
