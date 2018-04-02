package daag.tools;

import daag.web.App;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan("daag.dao.mapper")
@ComponentScan("daag")
public class DBInit {

    public static void main(String[] args) {

    }

}
