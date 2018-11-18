package com.rbruno.irc.command;

import java.util.Optional;

import com.rbruno.irc.client.Client;
import com.rbruno.irc.command.registration.Nick;
import com.rbruno.irc.command.registration.Pass;
import com.rbruno.irc.command.registration.User;
import com.rbruno.irc.net.Request;

public class RegistrationCommandInvoker implements CommandInvoker {

    private Pass pass;
    private Nick nick;
    private User user;

    public RegistrationCommandInvoker() {
        pass = new Pass();
        nick = new Nick();
        user = new User();
    }

    @Override
    public void runCommand(Request request, Optional<Client> command) {
        switch (request.getCommand()) {
        case "pass":
            pass.execute(request, command);
            break;
        case "nick":
            nick.execute(request, command);
            break;
        case "user":
            user.execute(request, command);
            break;
        default:
            break;
        }
    }

}
