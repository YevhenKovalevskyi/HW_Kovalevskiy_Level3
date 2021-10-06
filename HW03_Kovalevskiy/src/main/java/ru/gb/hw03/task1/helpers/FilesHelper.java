package ru.gb.hw03.task1.helpers;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Objects;

/**
 * FilesHelper Class is a helper class for different handling of file paths
 *
 * @author e.kovalevskiy
 * @version 1.0
 */
@Slf4j
public final class FilesHelper {
    
    /**
     * The method determines Maven subproject source file path
     *
     * @param currClass calling class
     * @param resourceName name of the resource
     * @param fileName name of the file
     * @return path to file
     */
    public static String getSourcePackageFilePathMaven(Class<?> currClass, String resourceName, String fileName) {
        String filePath = "";
        
        try {
            filePath = URLDecoder.decode(Objects.requireNonNull(currClass.getResource(resourceName)).getFile(), "UTF-8")
                    .replace(System.getProperty("user.dir").replace("\\", "/"), "")
                    .replace(resourceName, "")
                    .replace("target/classes", "src/main/java")
                    .replace("//", "")
                    .concat(fileName);
            log.info("Got resource directory path.");
        } catch (UnsupportedEncodingException e) {
            log.error("URLDecoder decode error.");
            e.printStackTrace();
        }
        
        return filePath;
    }
    
    /**
     * The method determines Gradle subproject source file path
     *
     * @param currClass calling class
     * @param resourceName name of the resource
     * @param fileName name of the file
     * @return path to file
     */
    public static String getSourcePackageFilePathGradle(Class<?> currClass, String resourceName, String fileName) {
        String filePath = "";
        
        try {
            filePath = URLDecoder.decode(Objects.requireNonNull(currClass.getResource(resourceName)).getFile(), "UTF-8")
                    .replace(System.getProperty("user.dir").replace("\\", "/"), "")
                    .replace(resourceName, "")
                    .replace("build/classes/java/main", "src/main/java")
                    .replace("//", "")
                    .concat(fileName);
            log.info("Got resource directory path.");
        } catch (UnsupportedEncodingException e) {
            log.error("URLDecoder decode error.");
            e.printStackTrace();
        }
        
        return filePath;
    }
    
    /**
     * The method determines Maven subproject resource file path
     *
     * @param currClass calling class
     * @param resourceName name of the resource
     * @param fileName name of the file
     * @return path to resource file
     */
    public static String getResourcePackageFilePathMaven(Class<?> currClass, String resourceName, String fileName) {
        String filePath = "";
        
        try {
            filePath = URLDecoder.decode(Objects.requireNonNull(currClass.getResource(resourceName)).getFile(), "UTF-8")
                    .replace(System.getProperty("user.dir").replace("\\", "/"), "")
                    .replace(resourceName, "")
                    .replace("target/classes", "src/main/resources")
                    .replace("//", "")
                    .concat(fileName);
            log.info("Got resource directory path.");
        } catch (UnsupportedEncodingException e) {
            log.error("URLDecoder decode error.");
            e.printStackTrace();
        }
        
        return filePath;
    }
    
    /**
     * The method determines Gradle subproject resource file path
     *
     * @param currClass calling class
     * @param resourceName name of the resource
     * @param fileName name of the file
     * @return path to resource file
     */
    public static String getResourcePackageFilePathGradle(Class<?> currClass, String resourceName, String fileName) {
        String filePath = "";
        
        try {
            filePath = URLDecoder.decode(Objects.requireNonNull(currClass.getResource(resourceName)).getFile(), "UTF-8")
                    .replace(System.getProperty("user.dir").replace("\\", "/"), "")
                    .replace(resourceName, "")
                    .replace("build/classes/java/main", "src/main/resources")
                    .replace("//", "")
                    .concat(fileName);
            log.info("Got resource directory path.");
        } catch (UnsupportedEncodingException e) {
            log.error("URLDecoder decode error.");
            e.printStackTrace();
        }
        
        return filePath;
    }
    
    /**
     * The method determines Maven subproject resource root directory + file path
     *
     * @param currClass calling class
     * @param resourceName name of the resource
     * @param fileName name of the file
     * @return path to resource file
     */
    public static String getResourceRootFilePathMaven(Class<?> currClass, String resourceName, String fileName) {
        String filePath = "";
        
        try {
            filePath = URLDecoder.decode(Objects.requireNonNull(currClass.getResource(resourceName)).getFile(), "UTF-8")
                    .replace(System.getProperty("user.dir").replace("\\", "/"), "")
                    .replace(resourceName, "")
                    .replace("target/classes", "src/main/resources")
                    .replace("//", "")
                    .replace(currClass.getPackage().getName().replace(".", "/") + "/", "")
                    .concat(fileName);
            log.info("Got resource directory path.");
        } catch (UnsupportedEncodingException e) {
            log.error("URLDecoder decode error.");
            e.printStackTrace();
        }
        
        return filePath;
    }
    
    /**
     * The method determines Gradle subproject resource root directory + file path
     *
     * @param currClass calling class
     * @param resourceName name of the resource
     * @param fileName name of the file
     * @return path to resource file
     */
    public static String getResourceRootFilePathGradle(Class<?> currClass, String resourceName, String fileName) {
        String filePath = "";
        
        try {
            filePath = URLDecoder.decode(Objects.requireNonNull(currClass.getResource(resourceName)).getFile(), "UTF-8")
                    .replace(System.getProperty("user.dir").replace("\\", "/"), "")
                    .replace(resourceName, "")
                    .replace("build/classes/java/main", "src/main/resources")
                    .replace("//", "")
                    .replace(currClass.getPackage().getName().replace(".", "/") + "/", "")
                    .concat(fileName);
            log.info("Got resource directory path.");
        } catch (UnsupportedEncodingException e) {
            log.error("URLDecoder decode error.");
            e.printStackTrace();
        }
        
        return filePath;
    }
}
