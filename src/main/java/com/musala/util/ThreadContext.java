package com.musala.util;

import java.util.HashMap;

public class ThreadContext extends HashMap<String, Object> {

	private static final ThreadLocal<ThreadContext> THREAD_LOCAL = new ThreadLocal<>();

	public static final void add(String key, Object value) {

		if (instance().containsKey(key)) {
			instance().remove(key);
		}

		instance().put(key, value);
	}

	public static final boolean contains(String key) {

		return instance().containsKey(key);
	}

	public static final void delete(String key) {

		if (instance().containsKey(key))
			instance().remove(key);
	}

	public static final Object get(String key) {

		return instance().getOrDefault(key, null);
	}

	public static final ThreadContext instance() {

		ThreadContext threadContext = THREAD_LOCAL.get();
		if (threadContext == null) {
			threadContext = new ThreadContext();
			THREAD_LOCAL.set(threadContext);
		}
		return threadContext;
	}
}
