https://tttang.com/archive/1405/#toc_snakeyaml
https://www.cnblogs.com/zpchcbd/p/14941783.html

不知道js怎么执行的java代码?
```text
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        // 执行javascript
        engine.eval("print('hello word!!')");
        engine.eval("print(\"Visit Runoob!\".search(/Runoob/i))");
        engine.eval("new java.lang.ProcessBuilder['(java.lang.String[])']" +
                "(['open', '/System/Applications/Calculator.app']).start()");
```