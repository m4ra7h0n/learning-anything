
cmake_minimum_required(VERSION 3.5)

project(compile_flags)

# CMAKE_C_COMPILER：指定C编译器
# CMAKE_CXX_COMPILER：指定C++编译器
# CMAKE_C_FLAGS：指定编译C文件时编译选项，也可以通过add_definitions命令添加编译选项

# CMAKE_CXX_FLAGS 是CMake传给C++编译器的编译选项，通过设置这个值就好比 g++ -std=c++11 -g -Wall
# 强制设置默认C++编译标志变量为缓存变量
# 该缓存变量被定义在文件中，相当于全局变量，源文件中也可以使用这个变量
# "${CMAKE_CXX_FLAGS} -DEX2"这个字符串可以保留原有的CMAKE_CXX_FLAGS中的参数，额外添加了一个EX2参数
# CACHE STRING "Set C++ Compiler Flags" FORCE命令是为了强制将CMAKE_CXX_FLAGS变量 放到CMakeCache.txt文件中
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -DEX2" CACHE STRING "Set C++ Compiler Flags" FORCE)

add_executable(cmake_examples_compile_flags main.cpp)

# 为可执行文件添加私有编译定义
# 设置编译标志
# target 指的是由 add_executable()产生的可执行文件或 add_library()添加进来的库
target_compile_definitions(cmake_examples_compile_flags PRIVATE EX3)