package network.data;

public class MatchResponse implements ResponseData{
	private static final long serialVersionUID = 3490501964882670055L;
	
	private String rivalUserName;

	public MatchResponse(String userName) {
		this.rivalUserName = userName;
	}

	@Override
	public void process(ClientInterface client) {
	}

}
