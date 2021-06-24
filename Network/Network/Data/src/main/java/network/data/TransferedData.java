package network.data;

import java.io.Serializable;

import network.user.UserPair;

public interface TransferedData extends Serializable{
	public void process(UserPair pair);
}
