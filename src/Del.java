import java.io.FileReader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 周智慧 on 2020/3/22.
 */
public class Del {

    public static void main(String[] args) {
        System.out.println("start");
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            System.out.println("do privilege");
            try {
                String jarWholePath = Del.class.getProtectionDomain().getCodeSource().getLocation().getFile();
                System.out.println("jarWholePath=" + jarWholePath);
                FileReader fileReader = new FileReader(jarWholePath + "./Del.class");
                fileReader.read();
            } catch (Exception e) {
                System.out.println("e=" + e);
            }
            System.out.println("do privilege 2");
            return null;
        });
        System.out.println("end");
    }

    /**/

}