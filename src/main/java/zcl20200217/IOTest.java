package zcl20200217;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author zhoucl
 * 2020/3/8 下午4:37
 * 演示如何使用流(Source)与汇(Slink)来对文件进行常用操作
 */
public class IOTest {

    @Test
    public void copyFile() throws IOException {
        /**
         * 创建对应的Source和Sink
         */
       CharSource charSource = Files.asCharSource(new File("zcl20200217/Cat.java"), Charsets.UTF_8);

       CharSink charSink = Files.asCharSink(new File("targetFile/abc.java"),Charsets.UTF_8);

        /**
         * 拷贝
         */
        charSource.copyTo(charSink);

    }
}
