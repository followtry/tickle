package cn.followtry.java.rpc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by followtry on 2017/6/8 0008.
 */
public class ServiceExport {
  static ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

  public static void exporter(String host,int port) {
    ServerSocket socket = null;
    try {
      socket = new ServerSocket();
      socket.bind(new InetSocketAddress(host,port));
      while (true) {
        service.execute(new ExportTask(socket.accept()));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      if (socket != null) {
        try {
          socket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
