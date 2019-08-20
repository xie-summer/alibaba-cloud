package com.springframework.feign.configure;

import com.springframework.feign.annotation.OriginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author summer
 * 2018/7/31
 */
@Configuration
@ConditionalOnProperty(value = "security.origin-filter.enabled", matchIfMissing = true)
public class FeignConfiguration  {
    private static final Logger logger = LoggerFactory.getLogger(FeignConfiguration.class);

    private final ApplicationContext applicationContext;

    public FeignConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    @ConditionalOnMissingBean(OriginServiceAspect.class)
    public OriginServiceAspectImpl originServiceAspect(){
        return new OriginServiceAspectImpl();
    }

//    @Configuration
//    @Import({AutoConfiguredMapperScannerRegistrar.class})
//    public static class MapperScannerRegistrarNotFoundConfiguration implements InitializingBean {
//
//        @Override
//        public void afterPropertiesSet() {
//            logger.debug(" mapper bean not found");
//        }
//    }
//
//    public static class AutoConfiguredMapperScannerRegistrar
//            implements BeanFactoryAware, ImportBeanDefinitionRegistrar, ResourceLoaderAware {
//
//        private BeanFactory beanFactory;
//
//        private ResourceLoader resourceLoader;
//
//        @Override
//        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
//
//            if (!AutoConfigurationPackages.has(this.beanFactory)) {
//                logger.debug("Could not determine auto-configuration package, automatic mapper scanning disabled.");
//                return;
//            }
//
//            logger.debug("Searching for mappers annotated with @OriginService");
//
//            List<String> packages = AutoConfigurationPackages.get(this.beanFactory);
//            if (logger.isDebugEnabled()) {
//                packages.forEach(pkg -> logger.debug("Using auto-configuration base package '{}'", pkg));
//            }
//
//            ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
//            if (this.resourceLoader != null) {
//                scanner.setResourceLoader(this.resourceLoader);
//            }
////            scanner.setAnnotationClass(OriginService.class);
////            scanner.registerFilters();
//            scanner.doScan(StringUtils.toStringArray(packages));
//        }
//
//        @Override
//        public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//            this.beanFactory = beanFactory;
//        }
//
//        @Override
//        public void setResourceLoader(ResourceLoader resourceLoader) {
//            this.resourceLoader = resourceLoader;
//        }
//    }
}
