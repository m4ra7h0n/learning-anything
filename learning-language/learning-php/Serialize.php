<?php

class people
{
    //定义类属性（类似变量）,public 代表可见性（公有）
    public $name = 'joker';

    //定义类方法（类似函数）
    public function smile()
    {
        echo $this->name . " is smile...\n";
    }
}

$psycho = new people(); //根据people类实例化对象
$psycho->smile();

