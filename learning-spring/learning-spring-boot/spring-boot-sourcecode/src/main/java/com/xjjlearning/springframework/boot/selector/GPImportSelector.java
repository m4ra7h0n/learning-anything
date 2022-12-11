package com.xjjlearning.springframework.boot.selector;

import com.xjjlearning.springframework.boot.selector.classes.FirstClass;
import com.xjjlearning.springframework.boot.selector.classes.SecondClass;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

//实现批量装配
public class GPImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        /**
                let's see the default selector in spring
                -> AutoConfigurationImportSelector
        */
        // if (!isEnabled(annotationMetadata)) {
        //     return NO_IMPORTS;
        // }
        /**
         get metadata from spring-autoconfigure-metadata.properties
         */
        // AutoConfigurationImportSelector.AutoConfigurationEntry autoConfigurationEntry = getAutoConfigurationEntry(annotationMetadata);
        // return StringUtils.toStringArray(autoConfigurationEntry.getConfigurations());

        return new String[]{FirstClass.class.getName(), SecondClass.class.getName()};
    }


    /**
        get metadata from spring-autoconfigure-metadata.properties
     */
    // protected AutoConfigurationEntry getAutoConfigurationEntry(AnnotationMetadata annotationMetadata) {
    //     if (!isEnabled(annotationMetadata)) {
    //         return EMPTY_ENTRY;
    //     }
    //     AnnotationAttributes attributes = getAttributes(annotationMetadata);
    //     List<String> configurations = getCandidateConfigurations(annotationMetadata, attributes);
    //     configurations = removeDuplicates(configurations);
    //     Set<String> exclusions = getExclusions(annotationMetadata, attributes);
    //     checkExcludedClasses(configurations, exclusions);
    //     configurations.removeAll(exclusions);
    //     configurations = getConfigurationClassFilter().filter(configurations);
    //     fireAutoConfigurationImportEvents(configurations, exclusions);
    //     return new AutoConfigurationImportSelector.AutoConfigurationEntry(configurations, exclusions);
    // }

    /**
     *  Through the spring-boot SPI mechanism, all automatic assembly configuration main class information is obtained
     */
    // public List<String> getConfigurations() {
    //     return this.configurations;
    // }
}
