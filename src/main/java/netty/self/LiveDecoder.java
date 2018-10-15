package netty.self;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class LiveDecoder extends ReplayingDecoder<LiveDecoder.LiveState> { //1

    public enum LiveState { //2
        LENGTH,
        CONTENT
    }

    private String message = "message";

    public LiveDecoder() {
        super(LiveState.LENGTH); // 3
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        switch (state()) { // 4
            case LENGTH:
                int length = byteBuf.readInt();
                if (length > 0) {
                    checkpoint(LiveState.CONTENT); // 5
                } else {
                    list.add(message); // 6
                }
                break;
            case CONTENT:
                byte[] bytes = new byte[message.length()];
                byteBuf.readBytes(bytes);
                String content = new String(bytes);
                list.add(content);
                break;
            default:
                throw new IllegalStateException("invalid state:" + state());
        }
    }
}
