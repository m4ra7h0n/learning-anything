cmake_minimum_required(VERSION 3.5)

project(third_party_include)

# 1.去这里找/usr/local/Cellar/cmake/3.19.1/share/cmake/Modules/FindBoost.cmake
# 2./usr/local/lib/cmake/Boost-1.81.0/BoostConfig.cmake:141 (find_package)
# 3.boost_filesystemConfig.cmake
# 4.boost_filesystemConfig.cmake

find_package(Boost REQUIRED COMPONENTS filesystem system)
# REQUIRED - 告诉模块这是必需的，如果找不到会报错
# COMPONENTS - 要查找的库列表 从后面的参数代表的库里找boost

# xxx_FOUND
if (Boost_FOUND)
    message("boost found")
    message("path is ${Boost_INCLUDE_DIRS}")
else ()
    message(FATAL_ERROR "Cannot find Boost")
endif ()

add_executable(third_party_include main.cpp)

target_link_libraries(third_party_include PRIVATE Boost::filesystem)


# 下面是旧版的链接用法
## Include the boost headers
#target_include_directories(third_party_include PRIVATE ${Boost_INCLUDE_DIRS})
## link against the boost libraries
#target_link_libraries(third_party_include PRIVATE ${Boost_SYSTEM_LIBRARY} ${Boost_FILESYSTEM_LIBRARY})