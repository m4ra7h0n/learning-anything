import {
    foo as foo2, bar
} from './module.mjs'
foo2()
console.log(bar);


import md5 from 'md5'
const before = 'Hello World';
const after = md5(before)
console.log(before, after);
