https://www.jianshu.com/p/7ee89f70dfe5

# source code
## back pressure
overflow strategy (back pressure)  
BUFFER by default
```java
	enum OverflowStrategy {
		/**
		 * Completely ignore downstream backpressure requests.
		 * <p>
		 * This may yield {@link IllegalStateException} when queues get full downstream.
		 */
		IGNORE,
		/**
		 * Signal an {@link IllegalStateException} when the downstream can't keep up
		 */
		ERROR,
		/**
		 * Drop the incoming signal if the downstream is not ready to receive it.
		 */
		DROP,
		/**
		 * Downstream will get only the latest signals from upstream.
		 */
		LATEST,
		/**
		 * Buffer all signals if the downstream can't keep up.
		 * <p>
		 * Warning! This does unbounded buffering and may lead to {@link OutOfMemoryError}.
		 */
		BUFFER
	}
```
## FluxCreate
BaseSink as the father   
BufferAsyncSink by default   

LambdaSubscriber