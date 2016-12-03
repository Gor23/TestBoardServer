package com.riversoft.eventsion.display.service

import com.riversoft.eventsion.display.models.FirmwareSendModel
import groovy.util.logging.Slf4j
import org.apache.tomcat.util.buf.HexUtils
import org.springframework.stereotype.Service

import javax.annotation.PostConstruct
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path


@Service
@Slf4j
class FirmwareLoaderService {

    short page = 0
    String path = "C:\\Users\\IGOR\\git\\f103c\\tabloMaster\\Debug\\tabloMaster.bin"
    Path p = FileSystems.getDefault().getPath(path)
    byte[] fileData = Files.readAllBytes(p)
    byte pageSize = 127
    byte[] outArray = new byte[pageSize]

//    @PostConstruct
//    void testMethod() {
//        def model = loadFirmware(true)
//        println("Done")
//    }

    FirmwareSendModel loadFirmware(boolean start) {
        if (start) {
            page = 0
        }

        boolean last = false

        short length = 0
        for (length; length < pageSize; length++) {
            if (length + pageSize * page < fileData.size()) {
                outArray[length] = fileData[length + pageSize * page]
            } else {
                last = true
                break
            }
        }
        def model = new FirmwareSendModel(
                last: (byte) (last ? 1 : 0),
                number: page,
                length: (byte) length,
                payload: outArray
        )
        println(HexUtils.toHexString(outArray))
        println("Page number ${page}")
        println("Page length ${length}")
        page++
        return model
    }
}
