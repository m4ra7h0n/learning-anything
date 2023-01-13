# 该文件定义了一个函数以及一个宏
message("进入 Findtest.cmake 文件")

set(test_VAL "222") # 验证根目录 CMake 文件能够访问这个变量
set(MY_VAL "000") # 测试 include() 效果

# 宏定义
macro(test MY_VA) # 定义一个宏！
    set(macro_val "1") # 宏内部定义变量
    message("macro is MY_VAL=${MY_VA}")
    set(MY_VAL "999") # 直接修改的就是调用该宏所处的文件中的 Normal 变量
endmacro(test)

# 函数定义
function(xyz test_VAL)
    set(MY_VAL "888" PARENT_SCOPE)  # 修改 调用者的 变量
    message("function is MY_VAL=${MY_VAL}")
endfunction(xyz)