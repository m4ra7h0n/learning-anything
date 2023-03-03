https://wujiuye.github.io/JVMByteCodeGitBook
《java虚拟机规范 SE8》

# 源码
虚拟机栈大小设置:
openjdk/hotspot/src/os_cpu/linux_x86/vm/os_linux_x86.cpp
```c
// return default stack size for thr_type
size_t os::Linux::default_stack_size(os::ThreadType thr_type) {
  // default stack size (compiler thread needs larger stack)
#ifdef AMD64
  size_t s = (thr_type == os::compiler_thread ? 4 * M : 1 * M); // java编译器 4M 否则 1M
#else
  size_t s = (thr_type == os::compiler_thread ? 2 * M : 512 * K);
#endif // AMD64
  return s;
```
虚拟机启动时默认虚拟机栈大小
```text
// Check minimum allowable stack size for thread creation and to initialize
// the java system classes, including StackOverflowError - depends on page
// size.  Add a page for compiler2 recursion in main thread.
// Add in 2*BytesPerWord times page size to account for VM stack during
// class initialization depending on 32 or 64 bit VM.
os::Linux::min_stack_allowed = MAX2(os::Linux::min_stack_allowed,
          (size_t)(StackYellowPages+StackRedPages+StackShadowPages) * Linux::page_size() +
                  (2*BytesPerWord COMPILER2_PRESENT(+1)) * Linux::vm_default_page_size());
```


# 工具
1.jinfo(查看jvm信息)
```bash
卡着程序执行
ps -ef | grep StackOve  # get 30697
jinfo -flag ThreadStackSize 30697 # result -> -XX:ThreadStackSize=1024
```

2.jcmd(查看jvm内存信息)
java8给HotSpot VM引入了Native Memory Tracking (NMT)特性，可以用于追踪JVM的内部内存使用
开启:
```bash
添加: -XX:NativeMemoryTracking=summary
jcmd pid VM.native_memory summary scale=MB
```

```text

Native Memory Tracking:

Total: reserved=3451MB, committed=256MB
              # java堆使用情况, reserved保留内存, committed: 提交内存
-                 Java Heap (reserved=2048MB, committed=128MB)
                            (mmap: reserved=2048MB, committed=128MB) 
              # 用于存储类元数据信息使用到的原生内存, 共776个类, 使用14MB内存 
-                     Class (reserved=1041MB, committed=14MB)
                            (classes #776)
                            (malloc=9MB #762) 
                            (mmap: reserved=1032MB, committed=5MB) 
              # 线程占用的内存, 线程大小为22, 线程栈大小(-Xss)为21MB, 每个线程占用1MB内存 
-                    Thread (reserved=21MB, committed=21MB)
                            (thread #22)
                            (stack: reserved=21MB, committed=21MB)
              # JIT的代码缓存, 共使用3MB内存 
-                      Code (reserved=244MB, committed=3MB)
                            (mmap: reserved=244MB, committed=2MB) 
              # GC用到的一些堆外内存, 比如GC算法使用到的一些堆外空间 
-                        GC (reserved=85MB, committed=79MB)
                            (malloc=10MB #132) 
                            (mmap: reserved=75MB, committed=69MB) 
 
-                  Internal (reserved=9MB, committed=9MB)
                            (malloc=9MB #2073) 

-                    Symbol (reserved=2MB, committed=2MB)
                            (malloc=1MB #268) 
                            (arena=1MB #1)
```