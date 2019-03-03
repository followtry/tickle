package cn.followtry.netty.chapter2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @author jingzhongzhi
 * @since 2018/3/24
 */
public class EchoClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(EchoClient.class);

    private final String host;

    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws InterruptedException {

        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            //创建客户端的Bootstrap
            Bootstrap bootstrap = new Bootstrap();
            //指定EventLoopGroup，以处理客户端事件，需要适用于NIO实现
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    //设置服务器地址
                    .remoteAddress(new InetSocketAddress(host,port))
                    //在创建channel时，向Channelpipeline中添加一个echoClientHandler实例
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            //连接远程节点，阻塞等到连接建立
            ChannelFuture future = bootstrap.connect().sync();
            //阻塞，直到Channel关闭
            future.channel().closeFuture().sync();

        }finally {
            //关闭线程池并释放所有资源
            eventLoopGroup.shutdownGracefully().sync();
        }
    }

    /**
     * main.
     */
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2) {
            System.err.println("Usage: "+EchoClient.class.getSimpleName()+" <host> <port>");
        }
        LOGGER.info("启动客户端");

        String host = args[0];
        int port = Integer.parseInt(args[1]);
        new EchoClient(host,port).start();

        LOGGER.info("启动客户端完成");
    }
}
