package com.zeng.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    //配置lswagger的docket的bean实例
    @Bean
    public Docket docket(Environment environment){

        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev");
        //通过environment。acceptsProfiles 判断是否处在自己设定的环境中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //enable 是否启用swgger
                .enable(flag)
                //分组
                .groupName("曾嘉彬测试api")
                .select()
                .apis(RequestHandlerSelectors.any())
                /*
                RequestHandlerSelectors   配置要扫描的接口的方式
                basePackage("com.zeng.control")   指定要扫描的包
                any()   扫描全部
                none()  不扫描
                withClassAnnotation(GetMapping.class)   扫描类上的注解，参数是一个注解的反射对象
                withMethodAnnotation(RestController.class)  扫描方法上的注解
                 */
//                .apis(RequestHandlerSelectors.basePackage("com.zeng.control"))
                //paths过滤什么路径
                //.paths(PathSelectors.ant("/zeng/**"))
                .build();
    }



    //配置swgger信息
    Contact contact = new Contact("曾嘉彬","https://www.zengjb.xyz","1573439264@qq.com");
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "曾嘉彬的Api文档 ",
                "Api 接口",
                "1.0",
                "https://www.zengjb.xyz",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}
