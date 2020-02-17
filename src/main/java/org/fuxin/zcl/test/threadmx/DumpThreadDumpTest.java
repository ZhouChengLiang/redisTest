package org.fuxin.zcl.test.threadmx;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class DumpThreadDumpTest {
    public static void main(String[] args) {
        ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();
        for (ThreadInfo ti : threadMxBean.dumpAllThreads(true, true)) {
            System.out.print(ti.toString());
        }
    }
}
