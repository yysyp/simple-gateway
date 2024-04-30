package ps.demo.common;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.UUID;

public class TraceContextGenerator {

    private static final SecureRandom random = new SecureRandom();

    public static String generateTraceVersion() {
        return "00";
    }

    // 生成32字符的十六进制追踪ID
    public static String generateTraceId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    // 生成16字符的十六进制跨度ID
    public static String generateSpanId() {
        byte[] bytes = new byte[8];
        random.nextBytes(bytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    // 生成默认的追踪标志（目前只设置了采样决策位为0）
    public static String generateTraceFlags() {
        return "00"; // 表示默认采样决策
    }

    public static String generateTraceState() {
        return "";
    }

    // 生成traceparent头
    public static String generateTraceParent() {
        String version = generateTraceVersion();
        String traceId = generateTraceId();
        String spanId = generateSpanId();
        String traceFlags = generateTraceFlags();
        // 默认情况下不设置追踪状态
        String traceState = generateTraceState(); // 可选字段，可以根据需要设置

        return String.format("%s-%s-%s-%s%s%s", version, traceId, spanId, traceFlags, (traceState.isEmpty() ? "" : "-"), traceState);
    }

    public static void main(String[] args) {
        String traceParent = generateTraceParent();
        System.out.println(traceParent);
    }
}
