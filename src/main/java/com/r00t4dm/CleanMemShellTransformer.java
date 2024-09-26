package com.r00t4dm;

import javassist.*;
import java.io.*;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class CleanMemShellTransformer implements ClassFileTransformer {

    private static final String CLASSNAME = "javax.servlet.Servlet";
    private static final String FILTERNAME = "javax.servlet.Filter";
    private static final String LISTENERNAME = "javax.servlet.ServletRequestListener";
    private static final String TEMPLATENAME = "com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet";

    private final Instrumentation inst;

    public CleanMemShellTransformer(Instrumentation inst) {
        this.inst = inst;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer)  {
        try {
            if (isMemshell(loader, classBeingRedefined, classfileBuffer)) {
                return dumpClasses(classBeingRedefined, classfileBuffer);
            }
        } catch (NotFoundException | IOException | ClassNotFoundException | NoSuchMethodException e) {
            System.out.println("dump MemShell Error : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 不是内存马不修改字节码并返回
        return classfileBuffer;
    }

    /**
     * dump bytecode to class file.
     * @param classBeingRedefined
     * @param classfileBuffer
     * @return
     */
    private byte[] dumpClasses(Class<?> classBeingRedefined, byte[] classfileBuffer) throws IOException {
        String className = classBeingRedefined.getName();
        File file = new File("/tmp/memshell/"+className);

        file.mkdirs();
        File newFile = new File(file, classBeingRedefined.getSimpleName() + ".class");
        FileOutputStream fos = new FileOutputStream(newFile);
        fos.write(classfileBuffer);
        fos.flush();
        fos.close();
        return classfileBuffer;
    }

    /**
     * 根据特征判断是否是内存马
     * @param loader
     * @param aClass
     * @param bytes
     * @return true代表是内存马，false代表不是
     */
    private static boolean isMemshell(ClassLoader loader, Class aClass, byte[] bytes) throws NotFoundException,
            ClassNotFoundException, IOException, NoSuchMethodException {


        Class servletClass = loader.loadClass(CLASSNAME);
        Class filterClass = loader.loadClass(FILTERNAME);
        Class listenerClass = loader.loadClass(LISTENERNAME);
        Class templateClass = loader.loadClass(TEMPLATENAME);


        if (templateClass.isAssignableFrom(aClass)) {
            return true;
        }

        if (servletClass.isAssignableFrom(aClass) ||
                filterClass.isAssignableFrom(aClass) ||
                listenerClass.isAssignableFrom(aClass)) {


            return true;

        }

        return false;
    }

    /**
     *
     * @param MemShell 内存马
     * @param byteMemShell 内存马的字节码
     * @return 清理后的字节码
     * @throws NotFoundException
     * @throws CannotCompileException
     * @throws IOException
     */
    private static byte[] killMemShell (Class MemShell, byte[] byteMemShell)
            throws IOException {
        String className = MemShell.getName();
        File file = new File("/tmp/"+className);

        file.mkdirs();
        File newFile = new File(file, MemShell.getSimpleName() + ".class");
        FileOutputStream fos = new FileOutputStream(newFile);
        fos.write(byteMemShell);
        fos.flush();
        fos.close();
        return byteMemShell;

    }


}
