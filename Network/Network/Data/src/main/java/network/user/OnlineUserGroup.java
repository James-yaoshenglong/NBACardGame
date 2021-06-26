package network.user;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class OnlineUserGroup {
	private ArrayList<UserPair> onlineUsers; //later need to change to the thread safe array and queue
	private Queue<UserPair> waitingUsers;
	
	private static OnlineUserGroup instance = new OnlineUserGroup();
	
	private OnlineUserGroup() {
		this.onlineUsers = new ArrayList<>();
		this.waitingUsers = new LinkedList<UserPair>();
	}
	
	public static OnlineUserGroup getInstance() {
		return instance;
	}
	
	public void addUser(UserPair user) {
		onlineUsers.add(user);
	}
	
	public void match(UserPair user) {
		if(waitingUsers.isEmpty()) {
			waitingUsers.add(user);
		}
		else {
			user.match(waitingUsers.poll());
		}
	}
}
