package com.xjjlearning.springframework.boot.selector;

import com.xjjlearning.springframework.boot.selector.classes.FirstClass;
import com.xjjlearning.springframework.boot.selector.classes.SecondClass;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

//实现批量装配
public class GPImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{FirstClass.class.getName(), SecondClass.class.getName()};
    }
}
