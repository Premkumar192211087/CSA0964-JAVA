import org.littleshoot.proxy.HttpFiltersAdapter;
import org.littleshoot.proxy.HttpFiltersSourceAdapter;
import org.littleshoot.proxy.HttpProxyServer;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

import java.util.HashSet;
import java.util.Set;

public class AdBlockerProxy {
    private static final Set<String> AD_DOMAINS = new HashSet<>();

    static {
        // List of ad domains to block (example list)
        AD_DOMAINS.add("doubleclick.net");
        AD_DOMAINS.add("ads.google.com");
        AD_DOMAINS.add("adserver.com");
        // Add more ad domains as needed
    }

    public static void main(String[] args) {
        HttpProxyServer server = DefaultHttpProxyServer.bootstrap()
                .withPort(8080)
                .withFiltersSource(new HttpFiltersSourceAdapter() {
                    @Override
                    public HttpFiltersAdapter filterRequest(HttpRequest originalRequest) {
                        return new HttpFiltersAdapter(originalRequest) {
                            @Override
                            public HttpResponse clientToProxyRequest(HttpObject httpObject) {
                                String host = originalRequest.headers().get("Host");
                                if (host != null && isAdDomain(host)) {
                                    System.out.println("Blocking ad domain: " + host);
                                    return createBlockedResponse();
                                }
                                return null;
                            }
                        };
                    }
                }).start();
    }

    private static boolean isAdDomain(String host) {
        for (String domain : AD_DOMAINS) {
            if (host.contains(domain)) {
                return true;
            }
        }
        return false;
    }

    private static HttpResponse createBlockedResponse() {
        return new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1, HttpResponseStatus.FORBIDDEN);
    }
}
