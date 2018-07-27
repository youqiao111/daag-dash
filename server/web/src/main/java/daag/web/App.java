package daag.web;

import daag.web.config.tomcatConfig;
import org.apache.catalina.Context;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.MultipartConfigElement;
import java.nio.charset.Charset;

import org.mybatis.spring.annotation.MapperScan;

import java.util.Arrays;

/**
 * Created by tant on 2017-07-14-0014.
 */
@SpringBootApplication
@MapperScan("daag.dao.mapper")
@ComponentScan("daag")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public EmbeddedServletContainerFactory createEmbeddedServletContainerFactory()
    {
        TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
        tomcatFactory.setPort(8080);
        tomcatFactory.setSessionTimeout(30000);
        tomcatFactory.setUriEncoding(Charset.forName("UTF-8"));
        tomcatFactory.addConnectorCustomizers(new tomcatConfig());
        tomcatFactory.setTomcatContextCustomizers(Arrays.asList(new CustomCustomizer()));
        return tomcatFactory;
    }

    static class CustomCustomizer implements TomcatContextCustomizer {
        @Override
        public void customize(Context context) {
            context.setUseHttpOnly(false);
            //context.setSessionCookieDomain("localhost");
        }
    }


    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.setSessionTimeout(1800);//单位为S
            }
        };
    }
}
