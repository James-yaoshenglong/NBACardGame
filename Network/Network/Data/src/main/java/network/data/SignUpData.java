package network.data;

import network.user.UserPair;

public class SignUpData implements TransferedData{
	private static final long serialVersionUID = -212750270649889494L;
	
	private String userName;
	private String password;
	
	public SignUpData(String aUserName, String aPassword) {
		this.userName = aUserName;
		this.password = aPassword;
	}

	@Override
	public void process(UserPair pair) {
		if(DatabaseConnector.getInstance().existCheck(this)) {
			pair.replySingle(new SignUpResponse(false));
		}
		else {
			DatabaseConnector.getInstance().signUp(this);
			pair.replySingle(new SignUpResponse(true));
		}
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

}
