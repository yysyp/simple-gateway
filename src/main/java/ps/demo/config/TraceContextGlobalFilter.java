package ps.demo.config;


import io.micrometer.tracing.TraceContext;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import ps.demo.common.TraceContextGenerator;
import ps.demo.common.TraceContextParser;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TraceContextGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    Tracer tracer;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        log.info("test log===>>1...");
        String traceparentHeader = exchange.getRequest().getHeaders().getFirst("traceparent");
        log.info("test log===>>2...traceparentHeader={}", traceparentHeader);


//        MDC.put("traceId", traceContext.traceId());
//        MDC.put("spanId", traceContext.spanId());

        return chain.filter(exchange);

//        ServerHttpRequest mutatedRequest = request.mutate()
//                .headers(httpHeaders -> {
//                    httpHeaders.put("traceparent", traceParam);
//                    httpHeaders.set("myTraceId", traceparent);
//                })
//                .build();
//        ServerWebExchange newExchange = exchange.mutate().request(mutatedRequest).build();
//
//        return chain.filter(newExchange)
//                .doFinally(s -> {
//                    MDC.remove("traceId");
//                    MDC.remove("spanId");
//                });

    }


    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
