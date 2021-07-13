package network.data;

public class MatchResponse implements ResponseData{
	private static final long serialVersionUID = 3490501964882670055L;
	
	private String rivalUserName;
	int order; //1 represent for first, 2 represent for second

	public MatchResponse(String userName, int anOrder) {
		this.rivalUserName = userName;
		this.order = anOrder;
	}

	@Override
	public void reProcess(ClientInterface client) {
		client.getOperation().operate(this);
	}
	
	public int getOrder() {
		return order;
	}

}
