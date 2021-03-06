package network.data;

public class LoginResponse implements ResponseData{
	private static final long serialVersionUID = 5682101371072651885L;
	boolean isSuccess;
	
	public LoginResponse(boolean success) {
		this.isSuccess = success;
	}

	public boolean getStatus(){
		return isSuccess;
	}

	@Override
	public void reProcess(ClientInterface client) {
		client.getOperation().operate(this);
	}
}
