import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.security.ProtectionDomain;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019-03-03
 */
public class ApplicationMain {

    private static final Logger log = LoggerFactory.getLogger(ApplicationMain.class);

    public static final int DEFAULT_PORT = 8080;
    public static final String DEFAULT_CONTEXT_PATH = "";
    private static final String DEFAULT_APP_CONTEXT_PATH = "src/main/webapp";


    public static void main(String[] args) {

        runJettyServer(DEFAULT_PORT, DEFAULT_CONTEXT_PATH);

    }

    public static void runJettyServer(int port, String contextPath) {

        Server server = createJettyServer(port, contextPath);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            log.error("Jetty异常",e);
        } finally {
            try {
                server.stop();
            } catch (Exception e) {
                log.error("Jetty Stop 异常",e);
            }
        }

    }

    public static Server createJettyServer(int port, String contextPath) {

        Server server = new Server(port);
        server.setStopAtShutdown(true);

        ProtectionDomain protectionDomain = ApplicationMain.class.getProtectionDomain();
        URL location = protectionDomain.getCodeSource().getLocation();
        String warFile = location.toExternalForm();

        WebAppContext context = new WebAppContext(warFile, contextPath);
        context.setServer(server);

        // 设置work dir,war包将解压到该目录，jsp编译后的文件也将放入其中。
        String currentDir = new File(location.getPath()).getParent();
        File workDir = new File(currentDir, "work");
        context.setTempDirectory(workDir);

        server.setHandler(context);
        return server;

    }
}
