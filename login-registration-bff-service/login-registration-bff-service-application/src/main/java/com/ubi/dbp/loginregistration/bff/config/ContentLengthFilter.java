package com.ubi.dbp.loginregistration.bff.config;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;
public class ContentLengthFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return chain.filter(exchange)
                .doOnTerminate(() -> addContentLengthHeader(exchange));
    }
    private void addContentLengthHeader(ServerWebExchange exchange) {
        Long contentLength = exchange.getResponse().getHeaders().getContentLength();
        if (contentLength != null) {
        	
            exchange.getResponse().getHeaders().setContentLength(contentLength);
        }
    }
}