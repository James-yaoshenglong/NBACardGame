package network.data;

import java.io.Serializable;

public interface ResponseData extends Serializable{
	public void process(ClientInterface client);
}
