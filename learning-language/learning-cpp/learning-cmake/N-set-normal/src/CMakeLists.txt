cmake_minimum_required(VERSION 3.10)
message("进入子目录 src/CMakeLists.txt 文件")
#set(MY_VAL "777") # 只修改子目录的变量
set(MY_VAL "777" PARENT_SCOPE) # 修改父目录的变量
message("在子目录，MY_VAL=${MY_VAL}")
message("退出子目录")