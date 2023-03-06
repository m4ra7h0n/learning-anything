ASM常用api
- visitCode：访问方法的Code属性，实际上也是一个空方法，什么事情也不做；
- visitMaxs：用于设置方法的局部变量表与操作数栈的大小；
- MethodVisitor接口提供的编写字节码指令相关的API：
- visitInsn：往Code属性的code数组中添加一条无操作数的字节码指令，如dup指令、aload_0指令等；
- visitVarInsn：往Code属性的code数组中添加一条需要一个操作数的字节码指令，如aload指令；
- visitFieldInsn：往Code属性的code数组中添加一条访问字段的字节码指令，用于添加putfield、getfield、putstatic、getstatic指令；
- visitTypeInsn：往Code属性的code数组中添加一条操作数为常量池中某个CONSTANT_Class_info常量的索引的字节码指令，如new指令；
- visitMethodInsn：往Code属性的code数组中添加一条调用方法的字节码指令，如invokevirtual指令。

org.objectweb.asm.Type 
- getInternalName：获取一个类的内部类型名称，比如我们调用String.class.getName方法获取到的名称是“java.lang.String”，调用Type的getInternalName方法获取到的就是“java/lang/String”；
- getDescriptor：获取类的描述符，如String类的描述符为“Ljava/lang/String;”。