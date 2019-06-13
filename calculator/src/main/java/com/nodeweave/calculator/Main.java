package com.nodeweave.calculator;


import com.nodeweave.calculator.dao.CalculatorDAO;
import com.nodeweave.calculator.socket.CalculatorSocket;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class Main {
//    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static Main instance;
    private final CalculatorDAO calculatorDAO;

    public Main() {
        instance = this;
        this.calculatorDAO = new CalculatorDAO();

        Server server = new Server();
        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setPort(8080);
        server.addConnector(serverConnector);

        WebSocketHandler webSocketHandler = new WebSocketHandler() {
            @Override
            public void configure(WebSocketServletFactory webSocketServletFactory) {
                webSocketServletFactory.getPolicy().setIdleTimeout(1000 * 3600);
                webSocketServletFactory.register(CalculatorSocket.class);
            }
        };

        server.setHandler(webSocketHandler);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main();
    }

    public static Main getInstance() {
        return instance;
    }

    public CalculatorDAO getCalculatorDAO() {
        return calculatorDAO;
    }
}
