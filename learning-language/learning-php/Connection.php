<?php

class Connection
{
    // 资源类型对象不写入序列化数据
    protected $link; // this will be null after serializing
    private $dsn, $username, $password;

    /**
     * @param $dsn
     * @param $username
     * @param $password
     */
    public function __construct($dsn, $username, $password)
    {
        $this->dsn = $dsn;
        $this->username = $username;
        $this->password = $password;
        $this->connect();
    }

    private function connect()
    {
        $this->link = new PDO($this->dsn, $this->username, $this->password);
    }

    public function __sleep()
    {
        // TODO: Implement __sleep() method.
        return array('dsn', 'username', 'password');
    }


    // 在反序列化之后执行一些初始化操作
    public function __wakeup()
    {
        // TODO: Implement __wakeup() method.
        $this->connect();
    }
}