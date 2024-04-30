package ps.demo.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TraceContextParser {

    private static final Pattern TRACEPARENT_PATTERN =
            Pattern.compile("(?<version>[0-9a-f]{2})-(?<traceId>[0-9a-f]{32})-(?<spanId>[0-9a-f]{16})-(?<traceFlags>[0-9a-f]{2})(?:-(?<traceState>[0-9a-f]{2}([0-9a-f]{2})*))?");

    public static MyTraceContext parse(String traceparent) {
        Matcher matcher = TRACEPARENT_PATTERN.matcher(traceparent);
        if (matcher.matches()) {
            String version = matcher.group("version");
            String traceId = matcher.group("traceId");
            String spanId = matcher.group("spanId");
            String traceFlags = matcher.group("traceFlags");
            String traceState = matcher.group("traceState");
            return new MyTraceContext(version, traceId, spanId, traceFlags, traceState);
        }
        return null;
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyTraceContext {
        private String version;
        private String traceId;
        private String spanId;
        private String traceFlags;

        @Builder.Default
        private String traceState = "";

        @Override
        public String toString() {
            return String.format("%s-%s-%s-%s%s%s", version, traceId, spanId, traceFlags, (StringUtils.isEmpty(traceState) ? "" : "-"), traceState);
        }
    }
}