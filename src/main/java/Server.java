import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.nio.file.Files;
import java.util.concurrent.Executors;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Server {

    static ObjectMapper json = new ObjectMapper();
    static UserDAO userDAO = new UserDAO();
    static TaskDAO taskDAO = new TaskDAO();

    public static void main(String[] args) throws Exception {
        Database.getConnection();

        HttpServer server = HttpServer.create(new InetSocketAddress(3001), 0);
        server.createContext("/api/login", Server::handleLogin);
        server.createContext("/api/users", Server::handleUsers);
        server.createContext("/api/tasks", Server::handleTasks);
        server.createContext("/", Server::handleStatic);
        server.setExecutor(Executors.newFixedThreadPool(4));
        server.start();

        System.out.println("running on http://localhost:3001");
    }

    static void handleLogin(HttpExchange req) throws IOException {
        if (preflight(req)) return;
        try {
            Map body = json.readValue(readBody(req), Map.class);
            User user = userDAO.checkLogin((String) body.get("username"), (String) body.get("password"));

            if (user != null) {
                send(req, Map.of("success", true, "name", user.getName(), "username", user.getUsername()));
            } else {
                send(req, Map.of("success", false));
            }
        } catch (Exception e) {
            error(req, e);
        }
    }

    static void handleUsers(HttpExchange req) throws IOException {
        if (preflight(req)) return;
        String[] p = req.getRequestURI().getPath().split("/");
        String m = req.getRequestMethod();
        try {
            if (p.length > 3 && m.equals("GET")) {
                send(req, userDAO.getUser(decode(p[3])));
                return;
            }
            if (p.length > 3 && m.equals("DELETE")) {
                userDAO.deleteUser(decode(p[3]));
                send(req, Map.of("success", true));
                return;
            }
            if (m.equals("POST")) {
                createUser(req);
            }
        } catch (Exception e) {
            error(req, e);
        }
    }

    static void handleTasks(HttpExchange req) throws IOException {
        if (preflight(req)) return;
        String[] p = req.getRequestURI().getPath().split("/");
        String m = req.getRequestMethod();
        try {
            if (p.length == 4) {
                String username = decode(p[3]);
                if (m.equals("GET")) {
                    send(req, taskDAO.getTasksForUser(username));
                    return;
                }
                if (m.equals("POST")) {
                    createTask(req, username);
                }
            }
            if (p.length == 5) {
                String taskName = decode(p[3]);
                String username = decode(p[4]);
                if (m.equals("PATCH")) {
                    taskDAO.toggleDone(taskName, username);
                    userDAO.updateTotalPoints(username);
                    send(req, Map.of("success", true));
                }
                if (m.equals("DELETE")) {
                    taskDAO.deleteTask(taskName, username);
                    userDAO.updateTotalPoints(username);
                    send(req, Map.of("success", true));
                }
            }
        } catch (Exception e) {
            error(req, e);
        }
    }

    // --- small helpers ---

    static void createUser(HttpExchange req) throws Exception {
        Map body = json.readValue(readBody(req), Map.class);
        User newUser = new User((String) body.get("username"));
        newUser.setName((String) body.get("name"));
        userDAO.createUser(newUser, (String) body.get("password"));
        send(req, Map.of("success", true));
    }

    static void createTask(HttpExchange req, String username) throws Exception {
        Map body = json.readValue(readBody(req), Map.class);
        Task newTask = new Task((String) body.get("tasks"), Integer.parseInt(body.get("points").toString()));
        newTask.setDescription((String) body.get("description"));
        taskDAO.createTask(newTask, username, (String) body.get("dateDue"));
        send(req, Map.of("success", true));
    }

    // serves index.html when you go to localhost:3001
    static void handleStatic(HttpExchange req) throws IOException {
        try {
            String[] paths = {
                    "src/main/resources/index.html",
                    "MentorKod/src/main/resources/index.html",
                    "src/index.html"
            };

            File file = null;
            for (String path : paths) {
                File f = new File(path);
                System.out.println("trying: " + f.getAbsolutePath() + " -> " + f.exists());
                if (f.exists()) {
                    file = f;
                    break;
                }
            }

            if (file == null) {
                sendResponse404(req);
                return;
            }

            byte[] bytes = Files.readAllBytes(file.toPath());
            req.getResponseHeaders().set("Content-Type", "text/html; charset=utf-8");
            req.sendResponseHeaders(200, bytes.length);
            req.getResponseBody().write(bytes);
            req.getResponseBody().close();

        } catch (Exception e) {
            System.out.println("error serving file: " + e.getMessage());
            sendResponse404(req);
        }
    }

    static void sendResponse404(HttpExchange req) throws IOException {
        byte[] bytes = "404 not found".getBytes();
        req.sendResponseHeaders(404, bytes.length);
        req.getResponseBody().write(bytes);
        req.getResponseBody().close();
    }

    // --- boring stuff below ---

    static boolean preflight(HttpExchange req) throws IOException {
        req.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        req.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PATCH, DELETE, OPTIONS");
        req.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");
        if (req.getRequestMethod().equals("OPTIONS")) {
            req.sendResponseHeaders(204, -1);
            return true;
        }
        return false;
    }

    static void send(HttpExchange req, Object body) throws IOException {
        byte[] bytes = json.writeValueAsBytes(body);
        req.getResponseHeaders().set("Content-Type", "application/json");
        req.sendResponseHeaders(200, bytes.length);
        req.getResponseBody().write(bytes);
        req.getResponseBody().close();
    }

    static void error(HttpExchange req, Exception e) throws IOException {
        e.printStackTrace();
        send(req, Map.of("error", e.getMessage()));
    }

    static String readBody(HttpExchange req) throws IOException {
        return new String(req.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
    }

    static String decode(String s) throws Exception {
        return URLDecoder.decode(s, "UTF-8");
    }
}