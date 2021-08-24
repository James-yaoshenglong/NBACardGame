package network.data;

import java.io.File;

import network.user.UserPair;

public class FileRequest implements TransferedData{
	private static final long serialVersionUID = -642094435981504898L;
	
	private String name; //file name
	private int start; //start position of the file chunk
	
	public FileRequest(String name, int start) {
		this.name = name;
		this.start = start;
	}

	@Override
	public void process(UserPair pair) {
		pair.readFile(this);
	}
	
	public String getName() {
		return name;
	}
	
	public int getStart() {
		return start;
	}

}
