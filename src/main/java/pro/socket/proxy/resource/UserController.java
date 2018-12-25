package pro.socket.proxy.resource;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import pro.socket.proxy.domain.Message;
import pro.socket.proxy.domain.OutputMessage;
import pro.socket.proxy.service.SendAutoCommandsService;

@Controller
public class UserController {

    private final SendAutoCommandsService service;

    public UserController(final SendAutoCommandsService service) {
        this.service = service;
    }

    @MessageMapping("/user")
    @SendTo("/topic/commands")
    public OutputMessage sendCommandToRobot(final Message message) {
        service.startSending("/user");
        return new OutputMessage(message);
    }

    @MessageMapping("/robot")
    @SendTo("/topic/responses")
    public OutputMessage sendResponseFromRobotToUser(final Message message) {
        return new OutputMessage(message);
    }
}