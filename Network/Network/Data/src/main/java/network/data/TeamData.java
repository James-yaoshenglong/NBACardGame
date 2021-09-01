package network.data;

import network.user.OnlineUserGroup;
import network.user.UserPair;


public class TeamData implements TransferedData, ResponseData{
	private static final long serialVersionUID = 1824271949656987925L;

    private int score;
    private int order;

    public TeamData(int o){
        this.order = o;
        this.score = 0;
    }

	@Override
	public void process(UserPair pair) {
		pair.sendToRival(this);
		pair.replySingle(this);
	}

    @Override
	public void reProcess(ClientInterface client) {
		client.getOperation().operate(this);
	}

    public void addScore(int n){
        score = n;
    }

    public int getScore(){
        return score;
    }

    public int getOrder(){
        return order;
    }
}
