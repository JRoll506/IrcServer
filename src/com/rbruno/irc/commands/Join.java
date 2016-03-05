package com.rbruno.irc.commands;


import com.rbruno.irc.Channel;
import com.rbruno.irc.Request;
import com.rbruno.irc.Server;

public class Join extends Command {

	public Join() {
		super("JOIN", 1);
	}

	@Override
	public void execute(Request request) throws Exception {
		// TODO: Check password
		String[] channels = request.getArgs()[0].split(",");
		for (String channelName : channels) {
			Channel channel = Server.getServer().getChannelManger().getChannel(channelName);
			channel.addClient(request.getConnection().getClient());
			request.getConnection().getClient().addChannels(channel);

		}


	}

}