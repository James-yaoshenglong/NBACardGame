package network.data;

import java.util.ArrayList;

import network.user.UserPair;

public class DefendData implements TransferedData, ResponseData{
	private static final long serialVersionUID = -5795945499171994691L;
	
	private ArrayList<Integer> positionIDArr;
	private boolean zoneFlag;
	private int dtId; //these may later change to index later to avoid duplicate
	private int ugId;
	
	public DefendData(ArrayList<Integer> positions) {
		this.positionIDArr = positions;
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
	
	public void setZoneFlag(Boolean flag) {
		this.zoneFlag = flag;
	}
	
	public void setDt(int dt, int ug) {
		this.dtId = dt;
		this.ugId = ug;
	}

	public Boolean getZoneFlag(){
		return zoneFlag;
	}

	public Boolean isDoubleTeam(){
		return (dtId==-1);
	}

	public ArrayList<Integer> getMatchup(){
		return positionIDArr;
	}

	public int getDoubleTeam(){
		return dtId;
	}

	public int getUnguarded(){
		return ugId;
	}
}
