package network.data;

public class FileChunk implements ResponseData{
	private static final long serialVersionUID = 6039257886757108723L;
	
	private String name;
	private int start;
	private int end;
	private byte[] bytes;

	@Override
	public void reProcess(ClientInterface client) {
		System.out.println(bytes);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setStartEnd(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

}
