package network.data;

public class SignUpResponse implements ResponseData{
	private static final long serialVersionUID = -8773130581431162245L;
	
	private boolean isSuccess;
	
	public SignUpResponse(Boolean success) {
		this.isSuccess = success;
	}

	@Override
	public void process(ClientInterface client) {
		client.getOperation().operate(this);
	}
	
	public boolean getStatus(){
		return isSuccess;
	}
}
