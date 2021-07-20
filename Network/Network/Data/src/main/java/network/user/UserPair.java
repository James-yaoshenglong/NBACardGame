package network.user;

import io.netty.channel.Channel;
import network.data.MatchResponse;
import network.data.ResponseData;

public class UserPair {
	private String userName;
	private Channel selfChannel;
	private Channel rivalChannel;
	
	public UserPair(Channel self) {
		this.selfChannel = self;
		this.rivalChannel = null;
	}
	
	public void replySingle(ResponseData response) {
		selfChannel.writeAndFlush(response);
	}

	public void setUserName(String name) {
		this.userName=name;
	}
	
	public void match(UserPair another) {
		rivalChannel = another.selfChannel;
		another.rivalChannel = selfChannel;
		selfChannel.writeAndFlush(new MatchResponse(another.userName, 1));
		rivalChannel.writeAndFlush(new MatchResponse(userName, 2));
	}
	
	public void sendToRival(ResponseData response) {
		rivalChannel.writeAndFlush(response);
	}
}
