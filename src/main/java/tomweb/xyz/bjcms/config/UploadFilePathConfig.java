package tomweb.xyz.bjcms.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
public class UploadFilePathConfig extends WebMvcConfigurationSupport {


    @org.springframework.beans.factory.annotation.Value("${base.upload.fail.clue.path}")
    String uploadFailCluePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/**").addResourceLocations(
                "file:"+uploadFailCluePath);

        System.out.println("自定义静态资源目录用于文件映射"+uploadFailCluePath);

    }



}