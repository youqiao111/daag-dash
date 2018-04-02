package daag.web.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

public class tomcatConfig implements TomcatConnectorCustomizer {
    public void customize(Connector connector) {
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        protocol.setMaxConnections(2000);
        protocol.setMaxThreads(2000);
        protocol.setConnectionTimeout(30000);
        protocol.setMinSpareThreads(20);
    }

}