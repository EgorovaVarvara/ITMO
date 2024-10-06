import com.fastcgi.FCGIInterface;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Main {
    private static final String RESPONSE_TEMPLATE = "Content-Type: application/json\nContent-Length: %d\n\n%s";

    public static void main(String[] args) {
        FCGIInterface fcgi = new FCGIInterface();
        while (fcgi.FCGIaccept() >= 0) {
            long startTime = System.currentTimeMillis();
            try {
                String body = readRequestBody();
                HashMap<String, String> params = parse(body);
                if (!params.containsKey("x") || !params.containsKey("y") || !params.containsKey("r")) {
                    sendJson("{\"error\": \"missed necessary query param\"}");
                    continue;
                }

                float x = Float.parseFloat(params.get("x"));
                int y = Integer.parseInt(params.get("y"));
                float r = Float.parseFloat(params.get("r"));

                if (validateX(x) && validateY(y) && validateR(r)) {
                    boolean isInside = hit(x, y, r);
                    long andTime = System.currentTimeMillis();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    sendJson("{\"x\": " + x + ", " +
                            "\"y\": " + y + ", " +
                            "\"r\": " + r + ", " +
                            "\"result\": " + isInside + ", " +
                            "\"currentTime\": \"" + dtf.format(java.time.LocalDateTime.now()) + "\", " +
                            "\"executionTime\": \"" + (andTime - startTime) + " ms\"}");
                } else {
                    sendJson("{\"error\": \"invalid data\"}");
                }
            } catch (Exception e) {
                sendJson(String.format("{\"error\": \"%s\"}", e));
            }
        }
    }

    private static String readRequestBody() throws IOException {
        try {
            FCGIInterface.request.inStream.fill();
            int contentLength = FCGIInterface.request.inStream.available();
            var buffer = ByteBuffer.allocate(contentLength);
            var readBytes = FCGIInterface.request.inStream.read(buffer.array(), 0, contentLength);
            var requestBodyRaw = new byte[readBytes];
            buffer.get(requestBodyRaw);
            buffer.clear();
            return new String(requestBodyRaw, StandardCharsets.UTF_8);
        } catch (NullPointerException e) {
            return "";
        }
    }

    private static HashMap<String, String> parse(String queryString) {

        HashMap<String, String> map = new HashMap<>();

        if (queryString == null || queryString.isEmpty()) {
            return map;
        }
        queryString = queryString.substring(1, queryString.length() - 1); // удалить скобки
        String[] pairs = queryString.split(",");

        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            map.put(URLDecoder.decode(keyValue[0].replaceAll("\"", ""), StandardCharsets.UTF_8), URLDecoder.decode(keyValue[1].replaceAll("\"", ""), StandardCharsets.UTF_8));
        }
        return map;
    }

    private static void sendJson(String jsonDump) {
        System.out.printf(RESPONSE_TEMPLATE + "%n", jsonDump.getBytes(StandardCharsets.UTF_8).length, jsonDump);
    }

    private static boolean hit(float x, int y, float r) {
        if (x < 0 && y > 0) {
            return false;
        } else if (x >= 0 && y >= 0) {
            return x <= r/2 && y <= Math.sqrt((r * r)/4 - x * x);
        } else if (x >= 0) {
            return x <= r && Math.abs(y) <= r;
        } else {
            return Math.abs(x) <= r / 2 && y >= -x - r / 2;
        }
    }

    public static boolean validateX(float x) {
        return x >= -3 && x <= 5;
    }

    public static boolean validateY(int y) {
        return y >= -3 && y <= 5;
    }

    public static boolean validateR(float r) {
        return r >= 1 && r <= 3;
    }

}