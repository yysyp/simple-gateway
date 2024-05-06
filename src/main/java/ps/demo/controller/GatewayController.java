package ps.demo.controller;


import io.micrometer.tracing.Tracer;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/")
public class GatewayController {

    @Autowired
    Tracer tracer;

    @GetMapping("hi")
    public ResponseEntity<String> hi() {
        String traceId = MDC.get("traceId");
        String spanId = MDC.get("spanId");
        log.info("This is hi from gateway, traceId={}, spanId={}", traceId, spanId);


        return ResponseEntity.ok("This is hi from gateway");
    }
}
