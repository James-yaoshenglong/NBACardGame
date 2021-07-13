package network.data;

import java.io.Serializable;

public interface ResponseData extends Serializable{
	public void reProcess(ClientInterface client);//这个方法可以写到abstract class中间
}
