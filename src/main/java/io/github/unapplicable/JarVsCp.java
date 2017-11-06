package io.github.unapplicable;

import java.net.URL;

public class JarVsCp {
    public static void main(String[] args) {
        System.out.printf("Running from jar #1: %b\n", isClassLoadedFromJar());
        System.out.printf("Running with -jar #2: %b\n", isRunningWithDashJar());
    }

    /**
     * Distinguish running from a class file vs jar (classpath or -jar)
     */
    private static boolean isClassLoadedFromJar() {
        Class jarVsCpClass = JarVsCp.class;
        URL url = jarVsCpClass.getResource(jarVsCpClass.getSimpleName() + ".class");
        System.out.printf("JarVsCp.class.resource = %s\n", url);

        return "jar".equals(url.getProtocol());
    }

    /**
     * Distinguish running with -jar vs -classpath package.MainClass
     */
    private static boolean isRunningWithDashJar() {
        String command = System.getProperty("sun.java.command");
        System.out.printf("sun.java.command = %s\n", command);
        if (null == command) {
            return false;
        }

        String firstToken = command.split("\\s", 2)[0];
        System.out.printf("sun.java.command[0] = %s\n", firstToken);

        return firstToken.endsWith(".jar");
    }
}
