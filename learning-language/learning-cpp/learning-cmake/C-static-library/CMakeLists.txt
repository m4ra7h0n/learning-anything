cmake_minimum_required(VERSION 3.5)
project(hello_library)

# hello.cpp 生成静态库 libhello_library.a
add_library(hello_library STATIC src/Hello.cpp)
# PUBLIC: 这个库包含了这个路径, 并且调用这个库的其他目标也包含了这个路径
# PRIVATE: 这个库包含了这个路径, 其他调用这个库的目标包含不了这个路径
# INTERFACE: 这个库没包含这个路径, 但是调用这个库的其他目标包含了这个路径
target_include_directories(hello_library PUBLIC ${PROJECT_SOURCE_DIR}/include) # 这样cpp中include就可以只写include文件夹下的目录

# 添加可执行文件
add_executable(hello_binary src/main.cpp)
# 链接可执行文件和静态库
target_link_libraries(hello_binary PRIVATE hello_library)