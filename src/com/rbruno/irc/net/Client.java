package com.rbruno.irc.net;

import java.util.ArrayList;
import java.util.HashMap;

import com.rbruno.irc.Channel;

public class Client {

	private Connection connection;
	private String nickname;
	private String username;
	private String hostname;
	private String servername;
	private String realName;

	private ArrayList<Channel> channels = new ArrayList<Channel>();
	private HashMap<Mode, Boolean> modes = new HashMap<Mode, Boolean>();

	public Client(Connection connection, String nickname) {
		this.connection = connection;
		this.nickname = nickname;
	}

	public Client(Connection connection, String nickname, String username, String hostname, String servername, String realName) {
		this.connection = connection;
		this.nickname = nickname;
		this.username = username;
		this.hostname = hostname;
		this.servername = servername;
		this.realName = realName;

	}
	
	public enum Mode{
		INVISIBLE,SERVER_NOTICES,WALLOPS,OPERATOR
	}
	
	public boolean getMode(Mode mode) {
		return modes.get(mode);
	}

	public void setMode(Mode mode, boolean add) {
		modes.put(mode, add);
	}

	public String getNickname() {
		return nickname;
	}

	public String getRealName() {
		return realName;
	}

	public String getUsername() {
		return username;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Connection getConnection() {
		return connection;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getServername() {
		return servername;
	}

	public void setServername(String servername) {
		this.servername = servername;
	}

	public ArrayList<Channel> getChannels() {
		return channels;
	}

	public void addChannels(Channel channel) {
		channels.add(channel);
	}

	public void removeChannel(Channel channel) {
		channels.remove(channel);
	}

}
