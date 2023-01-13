cmake_minimum_required(VERSION 3.5)
project(set_normal_test)

# 测试一下set
# https://blog.csdn.net/weixin_39732534/article/details/110658282
# 1.function()和add_subdirectory()中的set二者只是拷贝值
message("父目录 CMakeLists.txt 文件")
set(MY_VAL "666")
message("第一次在父目录 MY_VAL=${MY_VAL}")
add_subdirectory(../N-set-normal/src)
message("第二次在父目录，MY_VAL=${MY_VAL}")

# 函数定义 也可以修改上层的变量
function(xyz test_VAL) # 函数定义处！
    set(FUNCTION_VAL "7777")
    set(MY_VAL "888" PARENT_SCOPE) # 设置parent->MY_VAL
    message("functions is MY_VAL=${MY_VAL}")
endfunction(xyz)

xyz(${MY_VAL})
message("第三次在父目录，MY_VAL=${MY_VAL}")
message("function_val: ${FUNCTION_VAL}") # none




# CMAKE_MODULE_PATH 的解释
# Semicolon-separated list of directories specifying a search path for CMake modules to be loaded by the include() or find_package() commands
# before checking the default modules that come with CMake. By default it is empty, it is intended to be set by the project.

# find_package() vs include()
# https://blog.csdn.net/huihui__huihui/article/details/120841451

# list
# https://www.jianshu.com/p/89fb01752d6f
# list (subcommand <list> [args...])
# subcommand: LENGTH GET APPEND FIND INSERT REMOVE_ITEM REMOVE_AT REMOVE_DUPLICATES REVERSE SORT...


# 2.include()和macro()对于set是指针
message("父目录 CMakeLists.txt 文件")
set(MY_VAL "666")
message("第四次在父目录 MY_VAL=${MY_VAL}")

# 使用 include() 文件的宏
list(APPEND CMAKE_MODULE_PATH ${PROJECT_SOURCE_DIR}/cmake_modules)
include(Findtest) # 从 CMAKE_MODULE_PATH 包含的路径中搜索 Findtest.cmake 文件
test(${MY_VAL}) # 调用宏
xyz(${MY_VAL}) # 调用函数
# 使用 find_package() 搜索
find_package(test REQUIRED) # 从 CMAKE_MODULE_PATH 包含的路径中搜索 Findtest.cmake 文件 与 include () 两者的效果是一样的！
message("第五次在父目录，MY_VAL=${MY_VAL}")
message("include test=${test_VAL}")
message("macro_val=${macro_val}")
