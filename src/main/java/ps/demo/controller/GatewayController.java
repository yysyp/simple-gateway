package ps.demo.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/")
public class GatewayController {

    @GetMapping("hi")
    public ResponseEntity<String> hi() {
        log.info("This is hi from gateway");
        return ResponseEntity.ok("This is hi from gateway");
    }
}
