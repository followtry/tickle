package cn.followtry.boot.java;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2020/1/21
 */
public class Agent {
    /**
     * main.
     */
    public static void main(String[] args) throws IOException {
        //读取
        ClassReader classReader = new ClassReader("cn.followtry.boot.java.AgentBaseTest");
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        //处理

    }
}
