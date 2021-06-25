package network.user;

import java.util.ArrayList;

public class OnlineUserGroup {
	private ArrayList<UserPair> onlineUsers; //later need to change to the thread safe array and queue
	
	private static OnlineUserGroup instance = new OnlineUserGroup();
	
	private OnlineUserGroup() {
		this.onlineUsers = new ArrayList<>();
	}
	
	public static OnlineUserGroup getInstance() {
		return instance;
	}
	
	public void addUser(UserPair user) {
		onlineUsers.add(user);
	}
}
