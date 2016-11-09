package moe.tristan.Lyrical.model.integration.system.Linux;

import moe.tristan.Lyrical.model.integration.system.OperatingSystem;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by tristan9 on 11/7/16.
 */

public class Linux implements OperatingSystem {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Linux.class);
    private static Linux INSTANCE = null;

    private Linux() {
        INSTANCE = this;
    }

    public static Linux getInstance() {
        return INSTANCE == null ? new Linux() : INSTANCE;
    }

    @Override
    public String getName() {
        String raw = "";

        try {
            Process process = Runtime.getRuntime().exec("lsb_release -is");
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                raw += line;
            }
            in.close();
        } catch (IOException e) {
            log.error("Error reading the output stream of the getName process impl.", e);
        }
        return "Linux " + raw;
    }

}
