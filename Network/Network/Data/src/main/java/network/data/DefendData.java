package network.data;

import java.util.ArrayList;

import network.user.UserPair;

public class DefendData implements TransferedData, ResponseData{
	private static final long serialVersionUID = -5795945499171994691L;
	
	private ArrayList<Integer> positionIDArr;
	private boolean zoneFlag;
	
	public DefendData() {
		this.positionIDArr = new ArrayList<>();
	}
	
	@Override
	public void reProcess(ClientInterface client) {
		client.getOperation().operate(this);
	}

	@Override
	public void process(UserPair pair) {
		pair.sendToRival(this);
		pair.replySingle(this);
	}

}
