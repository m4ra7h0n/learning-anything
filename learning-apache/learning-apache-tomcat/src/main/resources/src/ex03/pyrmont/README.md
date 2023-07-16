```text
startup.Bootstrap -> HttpConnector(connect by while) -> HttpProcessor.process()
-> ServletProcessor.process() -> load Servlet by classLoader -> SomeServletProcessor.process()
-> StaticResourceProcessor.process()
```