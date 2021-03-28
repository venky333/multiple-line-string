package com.oracle.multiplelinestring;

import com.oracle.multiplelinestring.service.MultipleLineStringService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MultipleLineStringApplication {

    private static final String INPUT = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
            "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
            "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
            "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
            "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s";

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(MultipleLineStringApplication.class, args);
        MultipleLineStringService multipleLineStringService = applicationContext.getBean(MultipleLineStringService.class);
        multipleLineStringService.processMultiLineString(INPUT);
    }

}
