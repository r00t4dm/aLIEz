package com.r00t4dm;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

public class CleanMemShellAgentMainAttach {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        if (args.length == 0) {

            System.err.println("usage: java -jar aLIEz.jar <JVMID> \"Agent-Locate\"");


            List<VirtualMachineDescriptor> vml = VirtualMachine.list();

            for (VirtualMachineDescriptor vmd: vml) {
                System.out.println(vmd.id() + ": " + vmd.displayName());
            }

        }

        else {
            try {
                String vmId = args[0];
                String agentLocate = args[1];

                VirtualMachine vm;

                List<VirtualMachineDescriptor> vml = VirtualMachine.list();

                for (VirtualMachineDescriptor vmd : vml) {

                    // tomcat
                    if (vmd.id().equalsIgnoreCase(vmId)) {
                        System.out.println("[+]OK.i find a jvm." + vmd.displayName());
                        // 一定是有才attach，此处不能任意attach id
                        vm = VirtualMachine.attach(vmId);

                        if (vm!=null) {
//                        vm.loadAgent("/Users/r00t4dm/Downloads/JavaAgent_CleanMemShell/target/JavaAgent_CleanMemShell-1.0-SNAPSHOT.jar");
                            vm.loadAgent(agentLocate);
                            System.out.println("[+]agent is injected.");
                            vm.detach();
                        }
                    }
                }

            }
            catch (Exception e) {

            }
        }
    }
}
