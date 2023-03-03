package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type;

import lombok.Getter;
import lombok.Setter;

/**
 * 字段表中的字段结构
 */
@Setter
@Getter
public class FieldInfo {
    private U2 access_flags;  
    private U2 name_index;  
    private U2 descriptor_index;  
    private U2 attributes_count;  
    private AttributeInfo[] attributes;
}