import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class Initializer extends ChannelInitializer<SocketChannel> {
    private final EchoHandler handler = new EchoHandler();
    @Override
    public void initChannel(SocketChannel ch){
        ch
                .pipeline()
                .addLast(handler);
    }
}
