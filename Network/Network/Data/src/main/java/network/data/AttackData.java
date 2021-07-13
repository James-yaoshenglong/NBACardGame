package network.data;

import network.user.UserPair;

public class AttackData implements TransferedData, ResponseData{
	private static final long serialVersionUID = -6745118712315161585L;
	
	private int player1;
	private String op1;
	private int player2;
	private String op2;
	
	public AttackData(int p1, String o1, int p2, String o2) {
		this.player1 = p1;
		this.op1 = o1;
		this.player2 = p2;
		this.op2 = o2;
	}

	@Override
	public void process(UserPair pair) {
//		pair.sendToRival(this);
	}

	@Override
	public void reProcess(ClientInterface client) {
		client.getOperation().operate(this);
	}

}
