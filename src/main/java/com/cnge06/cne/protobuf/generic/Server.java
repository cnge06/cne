package com.cnge06.cne.protobuf.generic;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class Server {
	private static int PORT = 9090;
	//private static MessageLite messageLite=Protocol.getDefaultInstance();

	public static void start() throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 100)
					.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch)
								throws Exception {
							//ExtensionRegistry registry = ExtensionRegistry.newInstance();
							//Msg.registerAllExtensions(registry);
							ChannelPipeline p = ch.pipeline();
							/*p.addLast("frameDecoder",new ProtobufVarint32FrameDecoder());
							p.addLast("protobufDecoder",
							        new ProtobufDecoder(messageLite));*/
							/*p.addLast("protobufDecoder",
					        new ProtobufDecoder(messageLite,
					                registry));*/
							
							p.addLast("frameDecoder",new MyFrameDecoder());
							p.addLast(new DispatcherHandler());
							p.addLast("frameEncoder",new MyFrameEncoder());
							
							/*p.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());
							p.addLast("protobufEncoder", new ProtobufEncoder());*/
							/*
							 * if (sslCtx != null) {
							 * p.addLast(sslCtx.newHandler(ch.alloc())); }
							 * //p.addLast(new LoggingHandler(LogLevel.INFO));
							 * p.addLast(new EchoServerHandler());
							 */
						}
					});

			// Start the server.
			ChannelFuture f = b.bind(PORT).sync();

			// Wait until the server socket is closed.
			f.channel().closeFuture().sync();
		} finally {
			// Shut down all event loops to terminate all threads.
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Server.start();
	}
}
