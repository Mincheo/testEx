package com.company;

import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Api {
    final String HOST = "localhost";
    final int PORT = 8000;
    final OrmDB orm = new OrmDB();
    public void Start() throws IOException, SQLException {
        orm.createDB();
        orm.createTable();
        HttpServer server = HttpServer.create(new InetSocketAddress(HOST, PORT), 0);
        server.createContext("/getSortRes", new ReadHandler());
        server.createContext("/postSortRes", new SaveHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server is running on http://"+HOST+":"+PORT);

    }


    class ReadHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange request) throws IOException
        {


            Map<String, String> params = queryToMap(request.getRequestURI().getQuery());
            String id_sort = params.get("id_sort");
            ArrayList<Integer> result = new ArrayList<>();
            try {
                result = orm.readSortRes(id_sort);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String response = result.toString();

            Headers headers = request.getResponseHeaders();
            headers.add("Access-Control-Allow-Origin", "*");
            headers.add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            headers.add("Access-Control-Allow-Headers", "Content-Type");

            request.sendResponseHeaders(200, response.length());
            OutputStream os = request.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    class SaveHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange request) throws IOException
        {

            InputStream inputStream = request.getRequestBody();
            Scanner s = new Scanner(inputStream).useDelimiter("\\A");
            String body = s.hasNext() ? s.next() : "";
            try {
                orm.saveSortRes(body.split(","));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String response = "";

            Headers headers = request.getResponseHeaders();
            headers.add("Access-Control-Allow-Origin", "*");
            headers.add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            headers.add("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

            request.sendResponseHeaders(200, response.length());
            OutputStream os = request.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    private Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        String[] params = query.split("&");
        for (String param : params) {
            String[] keyValue = param.split("=");
            if (keyValue.length >= 2) {
                result.put(keyValue[0], keyValue[1]);
            }
        }
        return result;
    }
}
