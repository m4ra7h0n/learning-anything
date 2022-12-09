package com.xjjlearning.alibaba.cloud.sentinel;


import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;

public abstract class BaseSentinelDubboFilter implements Filter {


    /**
     * Get method name of dubbo rpc
     *
     * @param invoker
     * @param invocation
     * @return
     */
    abstract String getMethodName(Invoker invoker, Invocation invocation, String prefix);

    /**
     * Get interface name of dubbo rpc
     *
     * @param invoker
     * @return
     */
    abstract String getInterfaceName(Invoker invoker, String prefix);


}
