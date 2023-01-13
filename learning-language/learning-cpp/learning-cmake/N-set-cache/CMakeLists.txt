
# https://blog.csdn.net/weixin_39732534/article/details/110658282
cmake_minimum_required(VERSION 3.5)
project(set_cache_test)

# 3.cache
# 变量定义
# set(<variable> <value> CACHE STRING INTERNAL)
# 修改cache变量
# set(<variable> <value> CACHE INSTERNAL FORCE)

set(MY_GLOBAL_VAR "666" CACHE STRING INTERNAL)
message("第一次在父目录 CMAKE_INSTALL_PREFIX=${CMAKE_INSTALL_PREFIX}")
message("第一次在父目录 MY_GLOBAL_VAR=${MY_GLOBAL_VAR}")
add_subdirectory(src)
message("第二次在父目录 CMAKE_INSTALL_PREFIX=${CMAKE_INSTALL_PREFIX}")
message("第二次在父目录 MY_GLOBAL_VAR=${MY_GLOBAL_VAR}")
set(CMAKE_INSTALL_PREFIX "-->usr")
message("第三次在父目录 CMAKE_INSTALL_PREFIX=${CMAKE_INSTALL_PREFIX}")


cmake_minimum_required(VERSION 3.10)
set(MY_GLOBAL_VAR "666")
message("第四次在父目录 MY_GLOBAL_VAR=$CACHE{MY_GLOBAL_VAR}")
add_subdirectory(src2)
message("第五次在父目录局部 MY_GLOBAL_VAR=${MY_GLOBAL_VAR}")
message("第五次在父目录全局 MY_GLOBAL_VAR=$CACHE{MY_GLOBAL_VAR}")