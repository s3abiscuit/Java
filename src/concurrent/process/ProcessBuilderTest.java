package concurrent.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessBuilderTest {
    public static void main(String[] args) throws IOException, InterruptedException {

        String[] command = {"ping", "google.com"};

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.directory(new File(System.getProperty("user.home")));

        try {
            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();

            System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*
PING google.com (240.0.0.23): 56 data bytes
64 bytes from 240.0.0.23: icmp_seq=0 ttl=64 time=0.179 ms
64 bytes from 240.0.0.23: icmp_seq=1 ttl=64 time=0.385 ms
64 bytes from 240.0.0.23: icmp_seq=2 ttl=64 time=0.330 ms

--- google.com ping statistics ---
3 packets transmitted, 3 packets received, 0.0% packet loss
round-trip min/avg/max/stddev = 0.179/0.298/0.385/0.087 ms

Exited with error code : 0
 */
