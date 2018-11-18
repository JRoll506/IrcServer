package com.rbruno.irc.command.commands;

import com.rbruno.irc.Server;
import com.rbruno.irc.channel.Channel;
import com.rbruno.irc.client.Client;
import com.rbruno.irc.client.LocalClient;
import com.rbruno.irc.command.Command;
import com.rbruno.irc.net.Request;
import com.rbruno.irc.reply.Error;
import com.rbruno.irc.reply.Reply;

public class Whois extends Command {

  public Whois() {
    super("WHOIS", 1);
  }

  @Override
  public void execute(Request request, Client client) {
    for (String current : request.getArgs()[0].split(",")) {
      LocalClient target = Server.getServer().getClientManager().getClient(current);
      if (target == null) {
        request.getConnection().send(Error.ERR_NOSUCHNICK, client.get(), current + " :No such nick");
        continue;
      }

      request.getConnection().send(Reply.RPL_WHOISUSER, client.get(), target.getNickname() + " " + target.getUsername() + " " + Server.getServer().getConfig().getHostname() + " * :" + target.getRealName());
      // TODO Whois server
      if (target.operator)
        request.getConnection().send(Reply.RPL_WHOISOPERATOR, client.get(), target.getNickname() + " :is an IRC operator");
      
      if (target.getAwayMessage().isPresent())
        request.getConnection().send(Reply.RPL_AWAY, client.get(), target.getNickname() + " :" + target.getAwayMessage().get());
      
      String channels = "";
      for (Channel channel : target.getChannels()) {
        if (channel.checkOP(target)) {
          channels = channels + "@" + channel.getName() + " ";
        } else if (channel.hasVoice(target)) {
          channels = channels + "+" + channel.getName() + " ";
        } else {
          channels = channels + channel.getName() + " ";
        }
      }
      request.getConnection().send(Reply.RPL_WHOISCHANNELS, client.get(), target.getNickname() + " :" + channels);
      request.getConnection().send(Reply.RPL_ENDOFWHOIS, client.get(), target.getNickname() + " :End of /WHOIS list");

    }

  }

}
