package sam.song.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sam.song.product.common.logTrace.LogTrace;
import sam.song.product.common.logTrace.ThreadLocalLogTrace;

@Configuration
public class LogTraceConfig {

  @Bean
  public LogTrace logTrace(){
    return new ThreadLocalLogTrace();
  }

}
