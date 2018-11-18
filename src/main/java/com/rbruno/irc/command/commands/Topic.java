package com.rbruno.irc.command.commands;

import com.rbruno.irc.Server;
import com.rbruno.irc.channel.Channel;
import com.rbruno.irc.client.Client;
import com.rbruno.irc.command.Command;
import com.rbruno.irc.net.Request;
import com.rbruno.irc.reply.Error;
import com.rbruno.irc.reply.Reply;

public class Topic extends Command {

  public Topic() {
    super("TOPIC", 1);
  }

  @Override
  public void execute(Request request, Client client) {
    super.execute(request, client);
    Channel channel = Server.getServer().getChannelManger().getChannel(request.getArgs()[0]);
    if (channel == null) {
      request.getConnection().send(Error.ERR_NOSUCHCHANNEL, client, request.getArgs()[0] + " :No such channel");
      return;
    }
    if (channel.isMode('t') && !channel.checkOP(client)) {
      request.getConnection().send(Error.ERR_CHANOPRIVSNEEDED, request.getClient(), channel.getName() + " :You're not channel operator");
      request.getConnection().send(Reply.RPL_TOPIC, request.getClient(), channel.getName() + " " + channel.getTopic());
      return;
    }
    if (request.getArgs().length == 1) {
      request.getConnection().send(Reply.RPL_TOPIC, request.getClient(), channel.getName() + " " + channel.getTopic());
    } else {
      channel.setTopic(request.getArgs()[1]);
    }
  }

}
