
cmake_minimum_required(VERSION 3.5)

project(hello_headers)

# 设置变量
set(SOURCES src/Hello.cpp src/main.cpp)
# 所有源文件生成可执行文件
add_executable(hello_headers ${SOURCES})

# 设置可执行文件需要包含的库的路径
target_include_directories(hello_headers PRIVATE ${PROJECT_SOURCE_DIR}/include)

#PROJECT_SOURCE_DIR指工程顶层目录
#PROJECT_Binary_DIR指编译目录