//简单理解: tsj就是强类型版本的javascript
// src/ts/index.ts
function getFirstWord(msg: string) {
    console.log(msg.split(' ')[0])
}

getFirstWord('Hello World')