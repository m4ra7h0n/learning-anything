package com.xjjlearning.alibaba.cloud.sentinel;

import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;

public class SentinelDubboProviderFilter extends BaseSentinelDubboFilter{
    @Override
    String getMethodName(Invoker invoker, Invocation invocation, String prefix) {
        return null;
    }

    @Override
    String getInterfaceName(Invoker invoker, String prefix) {
        return null;
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return null;
    }
}
