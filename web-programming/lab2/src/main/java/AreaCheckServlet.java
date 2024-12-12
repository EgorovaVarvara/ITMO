
import utils.HitChecker;
import utils.Result;
import utils.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.logging.Logger;


public class AreaCheckServlet extends HttpServlet {

    Logger logger = Logger.getLogger(this.getClass().getName());


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            logger.info("meow2");
            double start = System.currentTimeMillis();
            float x = Float.parseFloat(request.getAttribute("x").toString());
            float y = Float.parseFloat(request.getAttribute("y").toString());
            float r = Float.parseFloat(request.getAttribute("r").toString());

            if (request.getAttribute("flag").equals("click") || request.getAttribute("flag").equals("fom") && Validator.validateX(x) && Validator.validateY(y) && Validator.validateR(r)) {
                Result result = new Result();

                result.setValue(String.valueOf(HitChecker.hit(x, y, r)));

                result.setX(String.valueOf(x));
                result.setY(String.valueOf(y));
                result.setR(String.valueOf(r));
                result.setTime(String.valueOf(LocalDateTime.now().toLocalTime().withNano(0)));

                double execTime = (System.currentTimeMillis() - start);
                result.setExecTime(String.valueOf(execTime));

                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print("{\"x\":\"" + result.getX() + "\", " +
                        "\"y\":\"" + result.getY() + "\", " +
                        "\"r\":\"" + result.getR() + "\", " +
                        "\"value\":\"" + result.getValue() +
                        "\", \"execTime\":\"" + (System.currentTimeMillis() - start) + "\", " +
                        "\"time\":\"" + result.getTime() + "\"}");
                out.flush();
            } else {
                logger.info("qwerty");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Недопустимые параметры");
            }
        } catch (Exception e) {
            logger.info("zxc");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}