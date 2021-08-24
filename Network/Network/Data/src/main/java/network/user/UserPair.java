package network.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import io.netty.channel.Channel;
import network.data.FileChunk;
import network.data.FileRequest;
import network.data.MatchResponse;
import network.data.ResponseData;

public class UserPair {
	private String userName;
	private Channel selfChannel;
	private Channel rivalChannel;
	private File file;
	private RandomAccessFile raf;
	private final int CHUNK_LEN = 10;
	
	public UserPair(Channel self) {
		this.selfChannel = self;
		this.rivalChannel = null;
	}
	
	public void replySingle(ResponseData response) {
		selfChannel.writeAndFlush(response);
	}

	public void setUserName(String name) {
		this.userName=name;
	}
	
	public void match(UserPair another) {
		rivalChannel = another.selfChannel;
		another.rivalChannel = selfChannel;
		selfChannel.writeAndFlush(new MatchResponse(another.userName, 1));
		rivalChannel.writeAndFlush(new MatchResponse(userName, 2));
	}
	
	public void sendToRival(ResponseData response) {
		rivalChannel.writeAndFlush(response);
	}
	
	public void readFile(FileRequest fileReq) {//maintain a file reader for a user
		if(file == null || file.getName() != fileReq.getName() || raf == null) {
			file = new File(fileReq.getName());
			try {
				raf = new RandomAccessFile(file, "r");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return;
			}
		}
		int start = fileReq.getStart();
		if(start >= 0) {
			try {
				raf.seek(start);
				int length;
				if (CHUNK_LEN < (int)(raf.length()-start)) {
					length = CHUNK_LEN;
				}
				else {
					length = (int)(raf.length()-start);
				}
				byte[] bytes = new byte[length];
				int byteRead;
				if ((byteRead = raf.read(bytes)) != -1) {
					FileChunk fileChunk = new FileChunk();
					fileChunk.setName(fileReq.getName());
					fileChunk.setStartEnd(start, start+byteRead);
					fileChunk.setBytes(bytes);
					replySingle(fileChunk);
	            } 
				else {
					FileChunk fileChunk = new FileChunk();
					fileChunk.setName(fileReq.getName());
					fileChunk.setStartEnd(-1, start);
	                raf.close();
	                System.out.println("文件已经读完--------" + byteRead);
	            }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		 
	}
}
