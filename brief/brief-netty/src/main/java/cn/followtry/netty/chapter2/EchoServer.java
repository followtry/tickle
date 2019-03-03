package cn.followtry.netty.chapter2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @author jingzhongzhi
 * @since 2018/3/24
 */
public class EchoServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EchoServer.class);

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    /**
     * main.
     */
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            System.err.println("Usage:"+EchoServer.class.getSimpleName()+" <port>");
        }

        int port = Integer.parseInt(args[0]);
        new EchoServer(port).start();
    }

    public void start() throws InterruptedException {
        EchoServerHander serverHander = new EchoServerHander();
        //创建EventLoopGroup
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        //创建Bootstarp
        ServerBootstrap bootstrap = new ServerBootstrap();

        try{

            bootstrap.group(eventLoopGroup)
                    .localAddress(new InetSocketAddress(port))
                    //添加一个EchoServerHandler到子Channel的ChannelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(serverHander);
                        }
                    })
                    .channel(NioServerSocketChannel.class);

            //异步绑定服务器，阻塞等待知道绑定完成
            ChannelFuture sync = bootstrap.bind().sync();
            LOGGER.info("服务器已启动，端口："+port);
            //获取Channel的closeFuture，并且阻塞当前线程直到完成
            sync.channel().closeFuture().sync();
        }finally {
            //关闭EventLoopGroup,释放所有的资源
            eventLoopGroup.shutdownGracefully().sync();
        }

    }
}
