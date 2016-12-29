package cn.jingzztech.prac.test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestScope {
	
	public static void main(String[] args) throws IOException {
		 RandomAccessFile rda = new RandomAccessFile("d:\\test\\version.ini", "rw");
		 
		 FileChannel channel = rda.getChannel();
		 
		 ByteBuffer buffer = ByteBuffer.allocate(2048);
		 
		 int read = channel.read(buffer);
		 while(read != -1){
			 System.out.println("read:"+read);
			 
			 buffer.flip();
			 while(buffer.hasRemaining()){
				 System.out.print((char) buffer.get());
			 }
			 buffer.clear();
			 read = channel.read(buffer);
		 }
		 rda.close();

	}
}
