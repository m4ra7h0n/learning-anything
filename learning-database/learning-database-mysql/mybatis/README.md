# notes
resultMap: 数据库列和类的字段映射
mybatis 的异常是 RuntimeException子类

# notice
实体类中不要使用基本类型  会被赋初始值  而不是null

# other
1.idea自定义mybatis xml file 模版
Settings --> Editor --> File And Code Templates --> 中间选中Files --> 点击+号，添加模板 --> 输入模板名字：Name:XML File.xml （name可以自定义） --> 后缀名extension：xml --> 在面板中间输入内容并把enable live Template(激活模板)勾选上 ------>点击Apply—ok：
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="">

</mapper>
```

2.去掉mybatis.xml中的红线黄线背景色
https://blog.csdn.net/wsjzzcbq/article/details/89528252