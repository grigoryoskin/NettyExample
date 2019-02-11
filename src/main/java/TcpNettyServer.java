import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TcpNettyServer {
    public void serve(int port) throws Exception{
        System.out.println("starting the netty server...");
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            new ServerBootstrap()
                    .option(ChannelOption.SO_BACKLOG,50_000)
                    .group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new Initializer())
                    .bind(port)
                    .sync()
                    .channel()
                    .closeFuture()
                    .sync();


        }finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
