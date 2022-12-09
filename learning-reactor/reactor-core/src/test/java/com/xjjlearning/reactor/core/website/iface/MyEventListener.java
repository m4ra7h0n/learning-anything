package com.xjjlearning.reactor.core.website.iface;

import java.util.List;

public interface MyEventListener<T> {
    void onDataChunk(List<T> chunk);
    void processComplete();
}