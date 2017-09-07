package cn.followtry.nio.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by followtry on 2017/7/22 0022.
 */
public class IOdemo {
  /** main. */
  public static void main(String[] args) throws IOException {
    int port = 9000;
    ServerSocket serverSocket = new ServerSocket(port);
    Socket clientSocket = serverSocket.accept();

    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket
            .getInputStream()));
    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());

    String request,response;

    while ((request = reader.readLine()) != null) {
      if ("hello".equals(request)) {
        break;
      }
      response = processReq(request);
      //服务端处理逻辑
      writer.write(response);
    }
  }

  public static String processReq(String request) {
    return "hahah";
  }
}
