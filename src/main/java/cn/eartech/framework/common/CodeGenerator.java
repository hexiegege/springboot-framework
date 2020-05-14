package cn.eartech.framework.common;

import cn.eartech.framework.configuer.DataSourceConfigurer;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.baomidou.mybatisplus.annotation.IdType.ASSIGN_UUID;

/**
 * 代码生成器，根据数据库快速生成代码
 * 会覆盖旧的代码，注意只有第一次建表使用
 * @author shanfa
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = FrameworkApplication.class)
public class CodeGenerator {

    public static String[] entityTables ={};

    // 数据源配置
    @Autowired
    DataSourceConfigurer dataSourceConfigurer ;

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (!StringUtils.isEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * 根据表名生成代码
     * @param tables  表名数组
     * @param dataSourceConfigurer  数据源
     * @param firstTimeFlag  是否首次生成
     */
    public static void generateCoreFromTables(String[] tables,DataSourceConfigurer dataSourceConfigurer,boolean firstTimeFlag){
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //根目录
        String projectPath = System.getProperty("user.dir");
        //设置输出路径
        gc.setOutputDir(projectPath + "/src/main/java");
        //设置作者
        gc.setAuthor("shanfa");
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        //生成后是否打开文件夹
        gc.setOpen(false);
        //TODO: 是否覆盖之前的生成的同名文件 默认false
        gc.setFileOverride(false);
        // xml配置文件生成BaseColumnList
        gc.setBaseColumnList(true);
        // xml配置文件生成BaseResultMap
        gc.setBaseResultMap(true);
        // XML 二级缓存
        gc.setEnableCache(false);
        // 设置生成的文件名
        gc.setMapperName("%sDao");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        gc.setEntityName("%s");
        // 设置主键类型
        gc.setIdType(ASSIGN_UUID);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        // dsc.setSchemaName("public");
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dataSourceConfigurer.getUrl());
        dsc.setDriverName(dataSourceConfigurer.getDriverClassName());
        dsc.setUsername(dataSourceConfigurer.getUsername());
        dsc.setPassword(dataSourceConfigurer.getPassword());
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName(scanner("模块名"));
        //包名
        if(firstTimeFlag){
            pc.setParent("cn.eartech.framework");
        }else{
            pc.setParent("cn.eartech.framework.addition");
        }
        // 生成文件的目录
        pc.setEntity("entity");
        pc.setMapper("dao");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                if(firstTimeFlag){
                    return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }else{
                    return projectPath + "/src/main/resources/mapper1/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！

            }
        });

//        cfg.setFileCreate(new IFileCreate() {
//            @Override
//            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
//                // 判断自定义文件夹是否需要创建
//                checkDir("调用默认方法创建的目录");
//                return false;
//            }
//        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置自定义输出模板
        TemplateConfig templateConfig = new TemplateConfig();
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 大写
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //是否跳过视图
        strategy.setSkipView(true);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 自定义实体父类
        //strategy.setSuperEntityClass("com.core.base.SuperEntity");
        // 自定义 mapper 父类
        //strategy.setSuperMapperClass("com.core.base.SuperMapper");
        // 自定义 service 父类
        //strategy.setSuperServiceClass("com.core.base.SuperService");
        // 自定义 service 实现类父类
        //strategy.setSuperServiceImplClass("com.core.base.SuperServiceImpl");
        // 自定义 controller 父类
        //strategy.setSuperControllerClass("com.core.base.SuperController");
        // 写于父类中的公共字段
        //strategy.setSuperEntityColumns("id");
        //  也可以加入数组 例如tables
        //strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        //需要包含的表名，允许正则表达式（与exclude二选一配置）
        strategy.setInclude(tables);
        // 驼峰转下划线
        strategy.setControllerMappingHyphenStyle(true);
        // 表名前缀
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    public static void generateCoreFromTables(String[] tables,DataSourceConfigurer dataSourceConfigurer){
         generateCoreFromTables(tables,dataSourceConfigurer,true);
    }

    public static void main(String[] args) {
        // 运行main方法把类名上注解注释掉
//        DataSourceConfigurer configurer = new DataSourceConfigurer();
//        configurer.setDriverClassName("com.mysql.jdbc.Driver");
//        configurer.setUrl("jdbc:mysql://47.106.65.56:3307/eartech?useSSL=false&useUnicode=true&characterEncoding=UTF-8");
//        configurer.setUsername("root");
//        configurer.setPassword("abAB@123");
//        generateCoreFromTables(entityTables,configurer,false);
        
    }

    @Test
   public void  generate(){
        entityTables = new String[]{"user"};
        generateCoreFromTables(entityTables,dataSourceConfigurer,false);

    }
}
