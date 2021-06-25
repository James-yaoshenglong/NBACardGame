package network.user;

import io.netty.channel.Channel;
import network.data.LoginResponse;

public class UserPair {
	private String userName;
	private Channel selfChannel;
	private Channel rivalChannel;
	
	public UserPair(Channel self) {
		this.selfChannel = self;
		this.rivalChannel = null;
	}
	
	public void reLogin(LoginResponse response) {
		selfChannel.writeAndFlush(response);
	}

	public void setUserName(String name) {
		this.userName=name;
	}
}
