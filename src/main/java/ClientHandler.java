import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpVersion;

public class ClientHandler extends SimpleChannelInboundHandler<HttpResponse> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(createRequest1());
    }

    private HttpRequest createRequest1() {
        HttpRequest request = new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "");
        request.headers().set(HttpHeaderNames.HOST, "127.0.0.1");
        request.headers().set("request", "1");
        request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        return request;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpResponse msg) throws Exception {
        System.out.println("sending request 2");
        ctx.writeAndFlush(createRequest2());
    }



    private HttpRequest createRequest2() {
        HttpRequest request = new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "");
        request.headers().set("request", "2");
        request.headers().set(HttpHeaderNames.HOST, "127.0.0.1");
        request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        return request;
    }
}
