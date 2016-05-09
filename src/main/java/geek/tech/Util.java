package geek.tech;

import java.io.IOException;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 *
 * Util provide methods as Utility
 * Created by Selina on 5/25/15.
 */
public class Util {

    public static final String JAVA_VERSION = getVersion ();

    public static String getVersion () {

        //version could be 1.8.0_45
        return System.getProperty("java.version");
    }

    public static boolean isJava8(String version) {
        return version.startsWith("1.8");
    }

    /**
     *
     * @param pathStr  only one file in the foldPath
     * @return
     */
    public static BufferedReader getFileReader(String pathStr) {

        BufferedReader reader = null;

        Path path = Paths.get(pathStr);
        if (Files.exists(path) && Files.isReadable(path)) {
            try{
                reader = Files.newBufferedReader(path);
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            System.out.println("Path "+ pathStr + " does not exist or not readable");
        }
        return reader;
    }
}
