package com.r00t4dm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.lang.management.ManagementFactory;
import java.util.HashSet;
import java.util.Set;

public class CleanMemShellAgentmain {

//    private static final String CLASSNAME = "javax.servlet.Servlet";
//    private static final String FILTERNAME = "javax.servlet.Filter";
//    private static final String LISTENERNAME = "javax.servlet.ServletRequestListener";

    private static final Set<Class> setClasses = new HashSet<>();

    public static String getCurrentPID() {
        try {
            String name = ManagementFactory.getRuntimeMXBean().getName();
            return name.split("@")[0];
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * @param agentOps 类名
     * @param inst
     * @throws UnmodifiableClassException
     */
    public static void agentmain(String agentOps, Instrumentation inst)
            throws UnmodifiableClassException, ClassNotFoundException, IOException {

//        System.out.println(getCurrentPID());
//        String temp = System.getProperty("java.io.tmpdir");
//        System.out.println(temp);
//        File file = new File("/tmp/.attach_pid"+getCurrentPID());
//        System.out.println(file.getAbsolutePath());
//        FileOutputStream fos = new FileOutputStream(file);
//        fos.flush();
//        fos.close();


        for (Class clazz : inst.getAllLoadedClasses()) {
            Class[] classes = clazz.getInterfaces();
            for (int i = 0; i < classes.length; i++) {
                if (classes[i].getName().equalsIgnoreCase("javax.servlet.Servlet") ||
                classes[i].getName().equalsIgnoreCase("javax.servlet.Filter") ||
                classes[i].getName().equalsIgnoreCase("javax.servlet.ServletRequestListener")) {
                    System.out.println("符合条件的类 ：" + clazz.getName());
//                    inst.addTransformer(new CleanMemShellTransformer(inst), true);
//                    inst.retransformClasses(clazz);
                    setClasses.add(clazz);
                }
            }

            try {
                // 没有做测试
                if (clazz.getSuperclass().getName().contains("AbstractTranslet")) {
                    System.out.println("符合条件的类 ：" + clazz.getName());
                    setClasses.add(clazz);
                }
            }
            catch (NullPointerException nullPointerException) {
                // not print
            }


        }

        inst.addTransformer(new CleanMemShellTransformer(inst), true);
        inst.retransformClasses(setClasses.toArray(new Class[setClasses.size()]));

    }


}
