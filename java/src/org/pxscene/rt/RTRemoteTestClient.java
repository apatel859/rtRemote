package org.pxscene.rt;

import org.pxscene.rt.remote.RTRemoteConnectionManager;

import java.net.URI;
import java.util.concurrent.Future;

public class RTRemoteTestClient {
  public static void main(String[] args) throws Exception {

    // tcp://127.0.0.1:123455/some_name
    // <scheme>://<host>:<port>/<object name>
    URI uri = new URI(args[0]);

    RTObject obj = RTRemoteConnectionManager.getObjectProxy(uri);

    int n = 10;

    while (true) {
      try {
        // do get
        // future blocks until operation completes, also supports timed-wait
        // RTValue v = f.get(1000, TimeUnit.MILLISECONDS);
        Future<RTValue> f = obj.get("prop");
        RTValue v = f.get();
        System.out.println("get n:" + v);

        Thread.sleep(1000);

        // do set
        Future<Void> f2 = obj.set("prop", new RTValue(n++));
        f2.get();
        System.out.println("set");


      } catch (Exception err) {
        err.printStackTrace();
      }
    }
  }
}
