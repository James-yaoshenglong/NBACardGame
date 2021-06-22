package network.data;

public class LoginData implements TransferedData{
	private static final long serialVersionUID = 5021324296670286304L;
	
	private String userName;
	private String password;
	
	public LoginData(String aUserName, String aPassword) {
		this.userName = aUserName;
		this.password = aPassword;
	}
	
	@Override
	public void process() {
		//检验数据
		//数据库查询
		//做出相应回应
	}
}
