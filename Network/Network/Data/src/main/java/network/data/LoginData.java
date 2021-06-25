package network.data;

import network.user.OnlineUserGroup;
import network.user.UserPair;

public class LoginData implements TransferedData{
	private static final long serialVersionUID = 5021324296670286304L;
	
	private String userName;
	private String password;
	
	public LoginData(String aUserName, String aPassword) {
		this.userName = aUserName;
		this.password = aPassword;
	}
	
	@Override
	public void process(UserPair pair) {
		//检验数据
		//数据库查询
		if(DatabaseConnector.getInstance().loginCheck(this)) {
			pair.setUserName(userName);
			OnlineUserGroup.getInstance().addUser(pair);
			pair.reLogin(new LoginResponse(true));
		}
		else {
			pair.reLogin(new LoginResponse(false));
		}
	}
	
	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
}
