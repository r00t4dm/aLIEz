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
//                System.out.println("We Found MemShell In You WebApplication. " + classBeingRedefined.getName());
                // 将修改好的字节码返回
//                return killMemShell(classBeingRedefined, classfileBuffer);
                return dumpClasses(classBeingRedefined, classfileBuffer);
            }
        } catch (NotFoundException | IOException | ClassNotFoundException | NoSuchMethodException e) {
            System.out.println("memshell error : " + e.getMessage());
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
            throws NotFoundException,
            CannotCompileException, IOException {
//        String className = MemShell.getCanonicalName().replace(".", "/");
//        File file = new File("/tmp/"+className.substring(0, className.lastIndexOf("/")));

        String className = MemShell.getName();
        File file = new File("/tmp/"+className);

        file.mkdirs();
        File newFile = new File(file, MemShell.getSimpleName() + ".class");
        FileOutputStream fos = new FileOutputStream(newFile);
        fos.write(byteMemShell);
        fos.flush();
        fos.close();
//        ClassPool cp = ClassPool.getDefault();
//        cp.insertClassPath("/tmp/");
//        CtClass ctClass = cp.get(MemShell.getName());
        try {
//            CtMethod ctMethodg = ctClass.getDeclaredMethod("g");
//
//            if (ctMethodg != null) {
//                ctMethodg.setBody("{System.out.println(\"asdfsafd\"); return null;}");
//                System.out.println("call killMemShell");
//                byte[] bytes = ctClass.toBytecode();
//                ctClass.detach();
//                return bytes;
//            }
        }catch (Exception e) {

        }

        try {
//            CtMethod ctMethodQ = ctClass.getDeclaredMethod("Q");
//
//            if (ctMethodQ != null) {
//
//                ctMethodQ.setBody("{System.out.println(\"asdfsafd\"); return null;}");
//                System.out.println("call killMemShell");
//                byte[] bytes = ctClass.toBytecode();
//                ctClass.detach();
//                return bytes;
//            }

        }
        catch (Exception e) {

        }

        try {
//            CtMethod ctMethodEC = ctClass.getDeclaredMethod("EC");
//
//            if (ctMethodEC != null) {
//
//                ctMethodEC.setBody("{System.out.println(\"asdfsafd\"); return null;}");
//                System.out.println("call killMemShell");
//                byte[] bytes = ctClass.toBytecode();
//                ctClass.detach();
//                return bytes;
//            }
        }
        catch (Exception e) {

        }

        try {
//            CtMethod ctMethodGet = ctClass.getDeclaredMethod("get");
//            if (ctMethodGet != null) {
//                ctMethodGet.setBody("{System.out.println(\"asdfsafd\"); return null;}");
//                System.out.println("call killMemShell");
//                byte[] bytes = ctClass.toBytecode();
//                ctClass.detach();
//                return bytes;
//            }
        }
        catch (Exception e) {

        }


        return byteMemShell;

    }


}
