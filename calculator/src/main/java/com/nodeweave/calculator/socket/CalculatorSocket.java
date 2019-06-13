package com.nodeweave.calculator.socket;

import com.nodeweave.calculator.Main;
import com.nodeweave.calculator.dto.Calculator;
import com.nodeweave.calculator.dto.Memory;
import com.nodeweave.calculator.dto.Operator;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

import java.io.IOException;

@WebSocket
public class CalculatorSocket {
    @OnWebSocketConnect
    public void onConnect(Session session) throws IOException {
//        Main.LOGGER.info("Session opened {}", sessionId);
        System.out.printf("session with id %s opened\n", getId(session));
        session.getRemote().sendString("open");
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String message) throws IOException {
//        Main.LOGGER.info("received message {}", message);
        System.out.printf("received %s: %s\n", getId(session), message);
        Calculator calculator = Main.getInstance().getCalculatorDAO().getCalculator(getId(session));

        String response = "";

        if (message.startsWith("mrc:")) {
            Memory memory = calculator.getMemory();
            response = "mrc:" + (memory == null ? "ERR" : memory.getResult());
        } else if (message.startsWith("num:")) {
            String data = message.replace("num:", "");
            if (!data.isEmpty()) {
                double num = Double.parseDouble(data);
                calculator.addNumber(num);
                Main.getInstance().getCalculatorDAO().setCalculator(getId(session), calculator);
            }
        } else if (message.startsWith("operator:")) {
            String data = message.replace("operator:", "");
            Operator operator = Operator.findByCode(data.charAt(0));
            calculator.addOperator(operator);
            Main.getInstance().getCalculatorDAO().setCalculator(getId(session), calculator);

        } else if (message.startsWith("eval")) {
            response = "eval:" + calculator.eval();
        } else if (message.startsWith("m+")) {
            calculator.storeMemory();
        } else if (message.startsWith("m-")) {
            calculator.clearMemory();
        }
        System.out.printf("Sending back %s", response);
        session.getRemote().sendString(response);
    }

    @OnWebSocketClose
    public void onClose(Session session, int status, String reason) {
        Main.getInstance().getCalculatorDAO().removeCalculator(getId(session));
        System.out.printf("session with id %s was closed for reason %s, status %s\n", getId(session), reason, status);
    }

    @OnWebSocketError
    public void onError(Throwable t) {
//        Main.LOGGER.error(t.getMessage(), t);
    }

    public String getId(Session session) {
        return session.getLocalAddress().getAddress().getCanonicalHostName();
    }
}
