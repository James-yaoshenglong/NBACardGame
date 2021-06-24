package network.user;

import io.netty.channel.Channel;
import network.data.LoginResponse;

public class UserPair {
	private Channel selfChannel;
	private Channel rivalChannel;
	
	public UserPair(Channel self) {
		this.selfChannel = self;
		this.rivalChannel = null;
	}
	
	public void reLogin(LoginResponse response) {

	}
}
