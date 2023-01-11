#!/bin/bash
#这个脚本的作用是如果存在build.clang这个文件夹，就把它删除掉

# https://blog.csdn.net/weixin_42207486/article/details/81232871
# -e 判断对象是否存在
# -d 判断对象是否存在，并且为目录
# -f 判断对象是否存在，并且为常规文件
# -L 判断对象是否存在，并且为符号链接
# -h 判断对象是否存在，并且为软链接
# -s 判断对象是否存在，并且长度不为0
# -w 判断对象是否存在，并且可写
# -x 判断对象是否存在，并且可执行
# -O 判断对象是否存在，并且属于当前用户
# -G 判断对象是否存在，并且属于当前用户组
# -nt 判断file1是否比file2新  [ "/data/file1" -nt "/data/file2" ]
# -ot 判断file1是否比file2旧  [ "/data/file1" -ot "/data/file2" ]

if [ -e "$(pwd)/build.clang" ]; then
  echo "deleting $(pwd)/build.clang"
  rm -r "$(pwd)/build.clang"
fi
