package com.riversoft.eventsion.display.service

import com.riversoft.eventsion.display.models.FirmwareSendModel
import groovy.util.logging.Slf4j
import org.apache.tomcat.util.buf.HexUtils
import org.codehaus.groovy.runtime.ArrayUtil
import org.springframework.stereotype.Service

import javax.annotation.PostConstruct
import java.lang.reflect.Field
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
    short pageSize = 128
    byte[] outArray = new byte[pageSize]

//    @PostConstruct
//    void testMethod() {
//        byte[] array = serializeToByteArray(loadFirmware(true))
//        println(array.toString())
//    }
    boolean last = false

    FirmwareSendModel loadFirmware(boolean start) {
        if (start) {
            page = 0
            last = false
        }

        if (last){
            println("Finish")
            return null
        }

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
                length: length,
                payload: outArray
        )
        println(HexUtils.toHexString(outArray))
        println("Page number ${page}")
        println("Page length ${length}")
        page++
        return model
    }
    byte[] serializeToByteArray(FirmwareSendModel model){

        String firstField = "\"last\":"
        String secondField = "\"number\":"
        String thirdField = "\"length\":"
        String fourthField = "\"payload\":"

        short index = 0
        byte[] out = new byte[200]

        out[index++] = 0x7B

        byte[] temp = firstField.getBytes()
        for (short i; i<firstField.length(); i++){
            out[index] = temp[i]
            index++
        }

        out[index++] = model.last
        out[index++] = 0x2C
        temp = secondField.getBytes()

        for (short i; i<secondField.length(); i++){
            out[index] = temp[i]
            index++
        }

        out[index++] = model.number
        out[index++] = model.number>>8
        out[index++] = 0x2C
        temp = thirdField.getBytes()

        for (short i; i<thirdField.length(); i++){
            out[index] = temp[i]
            index++
        }

        out[index++] = model.length
        out[index++] = model.length>>8
        out[index++] = 0x2C

        temp = fourthField.getBytes()
        for (short i; i<fourthField.length(); i++){
            out[index] = temp[i]
            index++
        }

        for (short i; i<model.length; i++){

            out[index] = model.payload[i]
            index++
        }

        out[index] = 0x7D

        return out
    }
//    static byte[] serialize(FirmwareSendModel model) {
//        Class inputCalss = model.getClass()
//        Field[] fields = inputCalss.getFields()
//        short index = 0
//        byte[] out
//
//    }
}
