package com.cnge06.cne.example.protobuf.generic;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import com.cnge06.cne.protobuf.generic.MyFrameDecoder;
import com.cnge06.cne.protobuf.generic.MyFrameEncoder;

public class Client {
	private static String host="localhost";
	private static int port=9090;
	//private static MessageLite messageLite=Protocol.getDefaultInstance();
	public static void start() throws InterruptedException {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                	ChannelPipeline p=ch.pipeline();
                	//p.addLast("frameDecoder",new LengthFieldBasedFrameDecoder(89199,0,2,3,0));
                	
                	/*p.addLast("frameDecoder",new ProtobufVarint32FrameDecoder());
					p.addLast("protobufDecoder",
					        new ProtobufDecoder(messageLite));*/
                	p.addLast("frameDecoder",new MyFrameDecoder());
					p.addLast(new ClientHandler());
					p.addLast("frameEncoder",new MyFrameEncoder());
					/*p.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());
					p.addLast("protobufEncoder", new ProtobufEncoder());*/
                    
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (5)

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
	}
	
	public static void main(String[] args) throws InterruptedException {
		Client.start();
	}
}
