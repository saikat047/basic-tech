package org.test.fun.config;

import brave.Tracing;
import brave.http.HttpTracing;
import brave.propagation.B3Propagation;
import brave.propagation.CurrentTraceContext;
import brave.propagation.ExtraFieldPropagation;
import brave.spring.web.TracingClientHttpRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import zipkin2.Span;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;

@Configuration
@Import({
        TracingClientHttpRequestInterceptor.class
})
public class TracingConfiguration {

    @Bean
    Sender sender() {
        return OkHttpSender.create("http://127.0.0.1:9411/api/v2/spans");
    }

    @Bean
    AsyncReporter<Span> spanReporter() {
        return AsyncReporter.create(sender());
    }

    @Bean
    Tracing tracing() {
        return Tracing.newBuilder()
                .localServiceName("person-service-client")
                .propagationFactory(ExtraFieldPropagation.newFactory(B3Propagation.FACTORY, "user-name"))
                .currentTraceContext(CurrentTraceContext.Default.create())
                .spanReporter(spanReporter()).build();
    }

    @Bean
    HttpTracing httpTracing(Tracing tracing) {
        return HttpTracing.create(tracing);
    }
}