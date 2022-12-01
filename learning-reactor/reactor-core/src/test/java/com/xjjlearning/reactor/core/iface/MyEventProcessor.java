package com.xjjlearning.reactor.core.iface;

public interface MyEventProcessor {
		void register(MyEventListener<String> eventListener);
		void dataChunk(String... values);
		void processComplete();
	}