package cn.followtry.java.rpc;

import java.net.InetSocketAddress;

/**
 * 最简单的RPC测试
 * Created by followtry on 2017/6/9 0009.
 */
public class RpcClientMain {
  /** main. */
  public static void main(String[] args) {
    String host ="localhost";
    int port = 8080;
    new Thread(() ->{
      ServiceExport.exporter(host,port);
    }).start();
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    RpcImporter rpcImporter = new RpcImporter();
    EchoService importer = RpcImporter.importer(EchoServiceImpl.class,new InetSocketAddress
            (host,port));
    for (int i = 0; i <10 ; i++) {

      System.out.println(importer.echo("jingzz"+i+" "));
    }
  }
}
