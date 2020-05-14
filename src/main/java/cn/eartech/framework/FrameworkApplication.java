package cn.eartech.framework;

import cn.eartech.framework.common.DateFormatColumn;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;


/**
 * 启动类
 * @author shanfa
 */
//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
public class FrameworkApplication {
	public static void main(String[] args) {
		SpringApplication.run(FrameworkApplication.class, args);
		System.out.println(
				"******************************************\n" +
				"* 启动成功                                * \n" +
				"* 智听欢迎你                              * \n" +
				"****************************************** \n" );
	}
	/**
	 * 获取BCrypt算法密码加密编码器
	 *
	 * @return 一个BCryptPasswordEncoder类的实例
	 */
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


	/**
	 * 获取ObjectMapper
	 * JAVA8 日期 时间的序列化和反序列化  LocalDate  LocalDateTime
	 *  jsr-310
	 * @return 一个ObjectMapper的实例
	 */
	@Bean
	public ObjectMapper getObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(LocalDateTime.class,new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateFormatColumn.DEFAULT_DATE_TIME_FORMAT)));
		javaTimeModule.addSerializer(LocalDate.class,new LocalDateSerializer(DateTimeFormatter.ofPattern(DateFormatColumn.DEFAULT_DATE_FORMAT)));
		javaTimeModule.addSerializer(LocalTime.class,new LocalTimeSerializer(DateTimeFormatter.ofPattern(DateFormatColumn.DEFAULT_TIME_FORMAT)));
		javaTimeModule.addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateFormatColumn.DEFAULT_DATE_TIME_FORMAT)));
		javaTimeModule.addDeserializer(LocalDate.class,new LocalDateDeserializer(DateTimeFormatter.ofPattern(DateFormatColumn.DEFAULT_DATE_FORMAT)));
		javaTimeModule.addDeserializer(LocalTime.class,new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DateFormatColumn.DEFAULT_TIME_FORMAT)));
		objectMapper.registerModule(javaTimeModule).registerModule(new ParameterNamesModule());
		return objectMapper;
	}

	/**
	 * 获取DozerMapper
	 * dozer不支持java8 LocalDate
	 * @return 一个DozerBeanMapper的实例
	 */
	@Bean
	public Mapper getDozerMapper() {
		DozerBeanMapper beanMapper = new DozerBeanMapper();
		beanMapper.setMappingFiles(Collections.singletonList("dozerJdk8Converters.xml"));
		return beanMapper;
	}


	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean
	public DruidDataSource druidDataSource(){
		return new DruidDataSource();
	}


	/**
	 * druid的监控页面
	 * @return
	 */
	@Bean
	public ServletRegistrationBean<StatViewServlet> druidStatViewServlet() {
		ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(new StatViewServlet(),  "/druid/*");
		registrationBean.addInitParameter("allow", "127.0.0.1");// IP白名单 (没有配置或者为空，则允许所有访问)
		registrationBean.addInitParameter("deny", "");// IP黑名单 (存在共同时，deny优先于allow)
		registrationBean.addInitParameter("loginUsername", "root");
		registrationBean.addInitParameter("loginPassword", "1234");
		registrationBean.addInitParameter("resetEnable", "false");
		return registrationBean;
	}


}
