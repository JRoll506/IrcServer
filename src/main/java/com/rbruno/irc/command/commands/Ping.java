package com.rbruno.irc.command.commands;

import com.rbruno.irc.Server;
import com.rbruno.irc.client.Client;
import com.rbruno.irc.command.Command;
import com.rbruno.irc.net.Request;

public class Ping extends Command {

  public Ping() {
    super("PING", 1);
  }

  @Override
  public void execute(Request request, Client client) {
    request.getConnection().send(":" + Server.getServer().getConfig().getHostname() + " PONG " + request.getConnection().getNickname());
  }

}
