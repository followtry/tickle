package cn.followtry.java.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by followtry on 2017/6/9 0009.
 */
public class RpcImporter {

  public static <S> S importer(Class<S> serviceClass,InetSocketAddress address) {
    return (S)Proxy.newProxyInstance(serviceClass.getClassLoader(),serviceClass.getInterfaces(),
            new InvocationHandler() {

      @Override
      public Object invoke(Object proxy,Method method,Object[] args) throws Throwable {
        Socket socket = new Socket();
        socket.connect(address);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        try {

          out.writeUTF(serviceClass.getName());
          out.writeUTF(method.getName());
          out.writeObject(method.getParameterTypes());
          out.writeObject(args);
          return in.readObject();
        } finally {
          socket.close();
          in.close();
          out.close();
        }
      }
    });
  }
}
