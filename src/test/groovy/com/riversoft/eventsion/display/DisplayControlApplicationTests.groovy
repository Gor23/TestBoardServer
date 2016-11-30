package com.riversoft.eventsion.display

import org.apache.tomcat.util.buf.HexUtils
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path


@RunWith(SpringRunner)
@SpringBootTest
class DisplayControlApplicationTests {

	@Test
	void contextLoads() {

		String path = "/home/gor/"

		Path p = FileSystems.getDefault().getPath("/home/gor/tabloMaster.bin")
		byte[] fileData = Files.readAllBytes(p)
		byte pageSize = 127
		byte[] outArray = new byte[pageSize]
		boolean last = false
		short page = 0
		short length = 0
		while(!last){
			length = 0
			for(length; length<pageSize; length++){
				if (length+pageSize*page<fileData.size()){
					outArray[length] = fileData[length+pageSize*page]
				}else{
					last = true
					break
				}
			}
			println (HexUtils.toHexString(outArray))
			println("Page number ${page}")
			println("Page length ${length}")
			page++
		}
		println(HexUtils.toHexString(outArray[length-1]))
		println ('Last')
		println (page)

	}


}
