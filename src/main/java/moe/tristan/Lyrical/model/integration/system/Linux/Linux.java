package moe.tristan.Lyrical.model.integration.system.Linux;

import lombok.extern.slf4j.Slf4j;
import moe.tristan.Lyrical.model.integration.system.OperatingSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by tristan9 on 11/7/16.
 */

@Slf4j
public class Linux implements OperatingSystem {
    private static Linux INSTANCE = null;

    public static Linux getInstance() {
        return INSTANCE == null ? new Linux() : INSTANCE;
    }

    private Linux() {
        INSTANCE = this;
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

        log.debug("Detected Linux distribution : "+raw);

        return "Linux" + raw;
    }

}
