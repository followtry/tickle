package cn.followtry.zk.example.lock;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by followtry on 2017/5/31.
 */
public class FakeLimitedResource {
  private final AtomicBoolean inUse = new AtomicBoolean(false);
  
  public void     use() throws InterruptedException
  {
    // in a real application this would be accessing/manipulating a shared resource
    
    if ( !inUse.compareAndSet(false, true) )
    {
      throw new IllegalStateException("Needs to be used by one client at a time");
    }
    
    try
    {
      Thread.sleep((long)(3 * Math.random()));
    }
    finally
    {
      inUse.set(false);
    }
  }
}
