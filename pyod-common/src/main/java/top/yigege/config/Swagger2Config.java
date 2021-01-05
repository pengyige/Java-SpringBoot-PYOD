package top.yigege.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import top.yigege.constant.PyodConstant;
import top.yigege.constant.ResultCodeEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: Swagger2Config
 * @Description:swagger配置
 * @author: yigege
 * @date: 2020年09月26日 16:46
 */
/*@Configuration
@EnableSwagger2*/
public class Swagger2Config {
// "application/json","application/xml"
    //配置content type
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
            new HashSet<String>(Arrays.asList("application/x-www-form-urlencoded;charset=UTF-8"));

    @Bean
    public Docket createRestApi() {
        //统一返回状态
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        for (ResultCodeEnum item : ResultCodeEnum.values()) {
            responseMessageList.add(new ResponseMessageBuilder().code(item.getCode()).message(item.getMsg()).responseModel(null).build());
        }

        //公共参数
      /*  ParameterBuilder aParameterBuilder = new ParameterBuilder();
        *//*aParameterBuilder.name("Content-Type")
                .description("请求类型")
                .defaultValue("application/x-www-form-urlencoded;charset=UTF-8")
                .modelRef
                        (new ModelRef("string")).parameterType("header").required(true).build();*//*

        List<Parameter> aParameters = Lists.newArrayList();
        aParameters.add(aParameterBuilder.build());*/



        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                //.globalOperationParameters(aParameters)
   /*             .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)*/
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(PyodConstant.Common.BASE_PACKAGE+".controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("PYOD API")
                .version("1.0")
                .build();
    }
}
