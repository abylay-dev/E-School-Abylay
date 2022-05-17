package kz.abylay.eschool;

import kz.abylay.eschool.models.Roles;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MappedTypes(Roles.class)
@MapperScan("kz.abylay.eschool.repositories.mapper")
@SpringBootApplication
public class EschoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(EschoolApplication.class, args);
    }

}
