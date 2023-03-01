import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author 狐狸半面添
 * @create 2023-02-03 15:36
 */
public class MybatisPlusGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://8.134.136.211:3306/wave_edu?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("狐狸半面添") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\SoftwareEngineering\\java\\project\\waveedu\\waveedu-messagesdk\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.zhulang.waveedu") // 设置父包名
                            .moduleName("messagesdk") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\SoftwareEngineering\\java\\project\\waveedu\\waveedu-messagesdk\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("messagesdk_consumer_error_log") // 设置需要生成的表名
                            .addTablePrefix("messagesdk_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
public static void main01(String[] args) {
    FastAutoGenerator.create("jdbc:mysql://8.134.136.211:3306/wave_edu?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8", "root", "123456")
            .globalConfig(builder -> {
                builder.author("狐狸半面添") // 设置作者
                        //.enableSwagger() // 开启 swagger 模式
                        .fileOverride() // 覆盖已生成文件
                        .outputDir("D:\\SoftwareEngineering\\java\\project\\waveedu\\waveedu-edu\\src\\main\\java"); // 指定输出目录
            })
            .packageConfig(builder -> {
                builder.parent("com.zhulang.waveedu") // 设置父包名
                        .moduleName("edu") // 设置父包模块名
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\SoftwareEngineering\\java\\project\\waveedu\\waveedu-edu\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
            })
            .strategyConfig(builder -> {
                builder.addInclude("edu_common_homework_stu_score") // 设置需要生成的表名
                        .addTablePrefix("edu_"); // 设置过滤表前缀
            })
            .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
            .execute();
}
}
