https://zhuanlan.zhihu.com/p/357803433
https://www.jianshu.com/p/e4cbcd764783

```bash
# 编译生成可执行文件
clang test.c -o build/test
clang++ test.cpp -o build/testcpp
# 生成llvm字节码文件
clang -O1 -emit-llvm test.c -c -o build/test.bc
# 生成LLVM 的汇编代码 .ll 文件(可视化字节码文件)
clang -O1 -emit-llvm test.c -S -o build/test.ll
# 编译字节码文件为汇编文件
llc build/test.bc -o build/test.s
```

文件转化
```bash
# 将 .bc 文件转化为 .ll 文件:
llvm-dis test.bc
# 将 .ll 文件转化为 .bc 文件:
llvm-as test.ll
# 将 .bc 或 .ll 文件转化为本机平台的汇编代码
llc test.bc
llc test.ll
```

运行字节码文件
```bash
lli build/test.bc
```