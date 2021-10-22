package concurrent.process;

import java.io.IOException;
import java.util.Scanner;

public class RuntimeTest {
    public static void main(String[] args) throws IOException {
        String cmd = "ls -al";
        Process process = Runtime.getRuntime().exec(cmd);
        Scanner scanner = new Scanner(process.getInputStream());

        while(scanner.hasNextLine()){
            System.out.println(scanner.nextLine());
        }
        scanner.close();
    }
}
/* output
total 32
drwxr-xr-x  16 a11  staff   512 Oct  5 17:51 .
drwxr-xr-x   8 a11  staff   256 Sep 25 00:38 ..
-rw-r--r--@  1 a11  staff  8196 Aug 31 20:52 .DS_Store
drwxr-xr-x  10 a11  staff   320 Oct 22 22:18 .idea
drwxr-xr-x   9 a11  staff   288 Oct 10 13:10 algs
drwxr-xr-x   3 a11  staff    96 Aug 25 00:23 annotation
drwxr-xr-x   4 a11  staff   128 Oct  6 11:02 basics
drwxr-xr-x   9 a11  staff   288 Oct 22 22:16 concurrent
drwxr-xr-x   8 a11  staff   256 Oct  2 11:07 container
drwxr-xr-x   8 a11  staff   256 Aug 31 20:51 designpatterns
drwxr-xr-x   3 a11  staff    96 Jul 18 18:43 exception
drwxr-xr-x   3 a11  staff    96 Aug 27 21:55 generics
-rw-r--r--   1 a11  staff   653 Aug 27 22:10 javademo.iml
drwxr-xr-x   5 a11  staff   160 Jul 16 22:39 jvm
drwxr-xr-x   3 a11  staff    96 Oct  1 15:47 out
drwxr-xr-x   3 a11  staff    96 Sep 12 00:26 reflection
 */