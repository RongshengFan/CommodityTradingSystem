package org.example.cts;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");

        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/cts", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("chaoheng")
                            .outputDir(projectPath + "/src/main/java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.admin")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/src/main/resources/mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("adminer", "goods", "orders", "announcement","user") // 你的表名
                            .entityBuilder()
                            .enableLombok()
                            .controllerBuilder()
                            .enableRestStyle();
                })
                .execute();
    }
}