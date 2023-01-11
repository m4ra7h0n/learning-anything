cmake_minimum_required(VERSION 3.5)

# 没有指定 -> 设置默认编译方式
if (NOT CMAKE_BUILD_TYPE AND NOT CMAKE_CONFIGURATION_TYPES)
    message("Setting build type to 'RelWithDebInfo' as none was specified.")
    # 如果加上CACHE关键字，则设置的这个变量会被写入缓存文件中（但如果本身缓存文件中有这个变量，则不会覆盖缓存中的变量）
    # 只有加上FORCE关键字，这个被写入文件的值会覆盖之前文件中存在的同名变量
    set(CMAKE_BUILD_TYPE RelWithDebInfo CACHE STRING "Choose the type of build." FORCE) # string 在cmake-gui下拉框选项作为提示
    set_property(CACHE CMAKE_BUILD_TYPE PROPERTY STRINGS "Debug" "Release" "MinSizeRel" "RelWithDebInfo") # 这些是下拉框选项
endif ()
# set: 普通变量，缓存变量以及环境变量

project(build_type)
add_executable(cmake_examples_build_type main.cpp)