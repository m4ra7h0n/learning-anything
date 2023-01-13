cmake_minimum_required(VERSION 3.5)
project(hello_library)

# C语言---静态库VS动态库
# https://cloud.tencent.com/developer/article/1656728

# 生成动态库 libhello_library.dylb
add_library(hello_library SHARED src/Hello.cpp)
target_include_directories(hello_library PUBLIC ${PROJECT_SOURCE_DIR}/include)
# 给动态库起别名
add_library(hello::library ALIAS hello_library)

add_executable(hello_binary src/main.cpp)
target_link_libraries(hello_binary PRIVATE hello::library)