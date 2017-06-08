package cn.followtry.java.rpc;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

import static java.lang.System.in;

/**
 * Created by followtry on 2017/6/8 0008.
 */
public class ExportTask implements Runnable {

  private Socket client;

  public ExportTask(Socket client) {
    this.client = client;
  }

  @Override
  public void run() {
    try {
      InputStream in = client.getInputStream();
      ObjectInputStream oi = new ObjectInputStream(in);
      ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
      invoke(oi,out);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void invoke(ObjectInputStream oi,ObjectOutputStream out) {
    try {
      String type = oi.readUTF();
      String methodName = oi.readUTF();
      Class<?>[] paramTypes = (Class<?>[])oi.readObject();
      Object[] args = (Object[])oi.readObject();

      Class<?> className = Class.forName(type);
      Method method = className.getDeclaredMethod(methodName,paramTypes);
      method.setAccessible(true);
      Object result = method.invoke(className.newInstance(),args);

      out.writeObject(result);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (out != null) {
        try {
          out.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      if (client != null) {
        try {
          client.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

  }
}
