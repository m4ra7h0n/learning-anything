
cmake_minimum_required(VERSION 3.5)

project(compile_flags)

# CMAKE_C_COMPILER：指定C编译器
# CMAKE_CXX_COMPILER：指定C++编译器
# CMAKE_C_FLAGS：指定编译C文件时编译选项，也可以通过add_definitions命令添加编译选项

# 强制设置默认C++编译标志变量为缓存变量
# 该缓存变量被定义在文件中，相当于全局变量，源文件中也可以使用这个变量
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -DEX2" CACHE STRING "Set C++ Compiler Flags" FORCE)

add_executable(cmake_examples_compile_flags main.cpp)

# 为可执行文件添加私有编译定义
# 设置编译标志
target_compile_definitions(cmake_examples_compile_flags PRIVATE EX3)