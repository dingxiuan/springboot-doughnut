package com.benefitj.spring.registrar;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * 注解注册器
 *
 * @author DINGXIUAN
 */
public interface AnnotationMetadataRegistrar {

  /**
   * 注册
   *
   * @param metadata    注解类的信息
   * @param beanFactory bean工厂
   */
  void register(AnnotationMetadata metadata, ConfigurableListableBeanFactory beanFactory);

}
