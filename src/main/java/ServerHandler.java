import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

public class ServerHandler extends SimpleChannelInboundHandler<HttpRequest> {
    State state = State.WAIT_REQUEST_1;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpRequest request) throws Exception {
        switch (state){

            case WAIT_REQUEST_1:
                HttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
                ctx.writeAndFlush(response);
                this.state = State.WAIT_REQUEST_2;
                break;
            case WAIT_REQUEST_2:
                System.out.println("got request 2: " +  request.uri());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + state);
        }
    }

    enum State{
        WAIT_REQUEST_1,
        WAIT_REQUEST_2
    }
}
