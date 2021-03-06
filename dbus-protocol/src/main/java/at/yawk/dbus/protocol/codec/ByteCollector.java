package at.yawk.dbus.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yawkat
 */
@Slf4j
class ByteCollector extends ChannelHandlerAdapter {
    private ByteBuf buffer = null;

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof ByteBuf) {
            if (buffer == null) {
                buffer = (ByteBuf) msg;
            } else {
                buffer.writeBytes((ByteBuf) msg);
                ((ByteBuf) msg).release();
            }
        } else {
            super.write(ctx, msg, promise);
        }
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        if (buffer != null) {
            log.trace("Flush {}", buffer.readableBytes());
            ctx.write(buffer, ctx.voidPromise());
            buffer = null;
        } else {
            log.trace("Flush -1");
        }
        super.flush(ctx);
    }
}
