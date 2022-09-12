package com.enjoy.jvm.memory;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author: Michael
 * @date: 9/12/2022 9:49 AM
 * jinfo -flags pid
 *
 *  -XX:CICompilerCount=4
 *  -XX:InitialHeapSize=266338304
 *  -XX:MaxHeapSize=4246732800 (4246MB)
 *  -XX:MaxNewSize=1415577600  (1415MB)
 *  -XX:MinHeapDeltaBytes=524288
 *  -XX:NewSize=88604672 (88MB)
 *  -XX:OldSize=177733632 (177MB)
 *  -XX:+UseCompressedClassPointers
 *  -XX:+UseCompressedOops
 *  -XX:+UseFastUnorderedTimeStamps
 *  -XX:-UseLargePagesIndividualAllocation
 *  -XX:+UseParallelGC
 * Command line:  -javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\lib\idea_rt.jar=50224:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\bin -Dfile.encoding=UTF-8
 */
public class JinfoTest {

    public static void main(String[] args) {
        ArrayList<byte[]> arrayList=new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            arrayList.add(new byte[100*1024]);// 100KB
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
