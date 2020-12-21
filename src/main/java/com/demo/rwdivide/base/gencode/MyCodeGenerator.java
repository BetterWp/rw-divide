package com.demo.rwdivide.base.gencode;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 代码生成器
 * @Date: 2020/12/08 20:02
 * @Author: wp
 **/
public class MyCodeGenerator {

    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        /**
         * TODO 只生成实体类的表
         */
        String[] tableNamesForEntity = {};
        /**
         * TODO 生成实体类、mapper、xml的表
         */
        String[] tableNamesForAll = {"t_order_0"};

        for (String tableName : tableNamesForEntity) {
            generator(tableName, false);
        }
        for (String tableName : tableNamesForAll) {
            generator(tableName, true);
        }
    }

    /**
     * 代码自动生成主类
     *
     * @param tableName    表名
     * @param generatorAll 是否全部生成
     */
    private static void generator(String tableName, boolean generatorAll) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        /**
         * 全局策略配置
         */
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        // 目标目录
        gc.setOutputDir(projectPath + "/src/main/java");
        // TODO 作者
        gc.setAuthor("wp");
        // 是否覆盖 默认false
        gc.setFileOverride(true);
        // 是否打开输出目录
        gc.setOpen(false);
        // 启用swagger2注解
        gc.setSwagger2(false);
        // 在生成的Mapper文件中加入通用查询结果列
        gc.setBaseColumnList(true);
        // 在生成的Mapper文件中加入通用查询映射结果
        gc.setBaseResultMap(true);
        // 只使用Date时间类型
        gc.setDateType(DateType.ONLY_DATE);
        // 注入全局策略配置
        mpg.setGlobalConfig(gc);

        /**
         * 数据源配置
         */
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3316/demo_ds_0?useUnicode=true&serverTimezone=GMT%2B8&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        /**
         * 包配置
         */
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("test");
        pc.setParent("com.demo.rwdivide");
        pc.setEntity("model");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        if (generatorAll) {
            // 自定义配置会被优先输出
            focList.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            });
        }
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        /**
         * 模板配置
         */
        TemplateConfig templateConfig = new TemplateConfig();
        if (!generatorAll) {
            templateConfig.setMapper(null);
        }
        templateConfig.setXml(null);
        templateConfig.setController(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        mpg.setTemplate(templateConfig);

        /**
         * 策略配置
         */
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 是否为lombok模型 默认false
        strategy.setEntityLombokModel(true);
        // controller类是否直接返回json
        strategy.setRestControllerStyle(true);
        // 需要包含的表名
        strategy.setInclude(tableName);
        // 命名规则：驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
