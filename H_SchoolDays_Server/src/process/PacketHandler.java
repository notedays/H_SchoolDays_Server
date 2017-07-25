package process;

import com.himaginus.common.packet.RequestPacket;
import com.schoolDays.himaginus.server.process.PacketExecutor;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

public class PacketHandler extends ChannelInboundHandlerAdapter {
	PacketExecutor executor;

	public PacketHandler(PacketExecutor executor) {
		this.executor = executor;
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		System.out.println(ctx.channel().remoteAddress()+"Login");
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
			RequestPacket mrp = (RequestPacket) msg;
			executor.execute(ctx, mrp);
		} finally {
			// object reference를 풀어주기 위해서 반드시 release 해야한다.
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			if (event.state() == IdleState.READER_IDLE) {
				ctx.close();
				// process.clearUserSession(process.getUserBySession(ctx));
			}
		}
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// process.clearUserSession(process.getUserBySession(ctx));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.fireExceptionCaught(cause);
		cause.printStackTrace();
		System.out.println("EXCEPTION FROM HANDLER :: ");
		// process.clearUserSession(process.getUserBySession(ctx));
		ctx.close();
	}
}
