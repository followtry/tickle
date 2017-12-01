package cn.followtry.java.rpc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by followtry on 2017/6/8 0008.
 */
public class ServiceExport {
  
  private static int nThreads = 20;
  static ExecutorService service = new ThreadPoolExecutor(nThreads, nThreads,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
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
