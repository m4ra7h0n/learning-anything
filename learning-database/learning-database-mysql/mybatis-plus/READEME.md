mapper 相当于 from
BaseMapper是基本的方法 传入wrapper id entity(传入entity时不作为where条件)

Wrapper ((Lambda)QueryWrapper, (Lambda)UpdateWrapper) 相当于 where
Lambda加与不加的区别是  加的话调用java的类方法  不加的话调用的是数据库的字段 (建议使用lambda)
Wrapper的父类AbstractWrapper包含了基本的判断函数, 多值使用{}
Wrappers类是包装类的集合(一共四个主要static方法)  建议使用

(Lambda)QuerWrapper 主要是select方法
(Lambda)UpdateWrapper 主要是set方法
insert/delete 方法使用 mapper (因为Wrapper代表where所以没有相应的Wrapper)

包名只能叫module 不能叫entity