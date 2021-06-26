package network.data;

import network.user.OnlineUserGroup;
import network.user.UserPair;


public class MatchData implements TransferedData{
	private static final long serialVersionUID = 1824201948658987925L;

	@Override
	public void process(UserPair pair) {
		OnlineUserGroup.getInstance().match(pair);
	}

}
