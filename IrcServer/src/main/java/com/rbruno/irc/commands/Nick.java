package com.rbruno.irc.commands;

import java.io.IOException;

import com.rbruno.irc.net.ClientRequest;

public class Nick extends Command {

	public Nick() {
		super("NICK", 1);
	}

	@Override
	public void execute(ClientRequest request) throws IOException {
		
	}

}
