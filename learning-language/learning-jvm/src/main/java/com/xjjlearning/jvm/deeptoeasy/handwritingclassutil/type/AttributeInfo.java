package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type;

import lombok.Getter;
import lombok.Setter;

/**
 * 字段表中的属性结构
 */
@Getter
@Setter
public class AttributeInfo {
    private U2 attribute_name_index;  
    private U4 attribute_length;  
    private byte[] info;  
}