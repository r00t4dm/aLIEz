package com.r00t4dm;

import com.sun.tools.attach.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class CleanMemShellAgentMainAttach {
    private static final String TOOLS_CLASS_NAME = "com.sun.tools.attach.VirtualMachine";
    private static final String VMD_CLASS_NAME = "com.sun.tools.attach.VirtualMachineDescriptor";

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException,
            NoSuchMethodException, IllegalAccessException, MalformedURLException {
        if (args.length == 0) {
            System.err.println("usage: java -jar aLIEz.jar <JVMID> \"Agent-Locate\"");
            listTargetVMs();
        } else {
            String vmdId = args[0];
            String agentLocate = args[1];
            attachToTargetVM(vmdId, agentLocate);

        }
    }
    private static void attachToTargetVM(String vmdId, String agentLocate) throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException {

        File jarFile = new File(System.getProperty("java.home"));
        System.out.println("java.home : " +System.getProperty("java.home"));
        File toolsJar  = new File(jarFile.getParentFile() + "/lib/", "tools.jar");
        System.out.println("tools.jar path: " + toolsJar.getAbsolutePath());



        URL[] urls = new URL[] {toolsJar.toURI().toURL()};
        Class<?> vmClass = new URLClassLoader(urls).loadClass(TOOLS_CLASS_NAME);
//        Class<?> vmClass = Class.forName(TOOLS_CLASS_NAME);
        Method attachMethod = vmClass.getDeclaredMethod("attach", String.class);
        Object vmInstance = attachMethod.invoke(null, vmdId);
        Method loadAgentMethod = vmClass.getDeclaredMethod("loadAgent", String.class);
        loadAgentMethod.invoke(vmInstance, agentLocate);
        System.out.println("[+]agent is injected.");

        Method detachMethod = vmClass.getDeclaredMethod("detach");
        detachMethod.invoke(vmInstance);
    }

    private static void listTargetVMs() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException, MalformedURLException {


        File jarFile = new File(System.getProperty("java.home"));
        File toolsJar  = new File(jarFile.getParentFile() + "/lib/", "tools.jar");

        System.out.println("java.home: " + System.getProperty("java.home"));
        System.out.println("tools.jar path: " + toolsJar.getAbsolutePath());
        URL[] urls = new URL[] {toolsJar.toURI().toURL()};
        Class<?> vmClass = new URLClassLoader(urls).loadClass(TOOLS_CLASS_NAME);

//        Class<?> vmClass = Class.forName(TOOLS_CLASS_NAME);
        Method listMethod = vmClass.getDeclaredMethod("list");
        List<?> vms = (List<?>) listMethod.invoke(null);
        for (Object vmObject : vms) {

            Class<?> vmdClass = vmObject.getClass();
//            Class<?> vmdClass = new URLClassLoader(urls).loadClass(VMD_CLASS_NAME);
//            Class<?> vmdClass = Class.forName(VMD_CLASS_NAME);
            Method idMethod = vmdClass.getMethod("id");
            Method displayNameMethod = vmdClass.getMethod("displayName");

            String id = (String) idMethod.invoke(vmObject);
            String displayName = (String) displayNameMethod.invoke(vmObject);
            System.out.println(id + " : " + displayName);
        }
    }

}
