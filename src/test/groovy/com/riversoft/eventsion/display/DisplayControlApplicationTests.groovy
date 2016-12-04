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

		String path =  "C:\\Users\\IGOR\\git\\f103c\\tabloMaster\\Debug\\tabloMaster.bin"

		Path p = FileSystems.getDefault().getPath(path)
		byte[] fileData = Files.readAllBytes(p)
		short pageSize = 128
		byte[] outArray = new byte[129]
		boolean last = false
		short page = 0
		short length = 0
		outArray[0] = 127
		while(!last){
			length = 0
			for(length; length<pageSize; length++){
				if (length+pageSize*page<fileData.size()){
					outArray[length+1] = fileData[length+pageSize*page]
				}else{
					last = true
					break
				}
			}
			println (HexUtils.toHexString(outArray))
			println("Page number ${page}")
			println("Page length ${length}")
			short xor = 0
			for (short i=0; i<129; i++){
				xor^=outArray[i]
			}
			println("Xor = " + xor)
			page++
		}
		println(HexUtils.toHexString(outArray[length-1]))
		println ('Last')
		println (page)
	}


}
