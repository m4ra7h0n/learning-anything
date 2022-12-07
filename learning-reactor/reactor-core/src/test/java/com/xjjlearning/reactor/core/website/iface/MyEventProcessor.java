package com.xjjlearning.reactor.core.website.iface;

public interface MyEventProcessor {
		void register(MyEventListener<String> eventListener);
		void dataChunk(String... values);
		void processComplete();
	}