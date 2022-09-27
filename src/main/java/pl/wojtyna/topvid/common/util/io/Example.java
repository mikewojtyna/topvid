package pl.wojtyna.topvid.common.util.io;

import pl.wojtyna.topvid.common.util.io.legacy.VeryUglyLegacyFileOperations;
import pl.wojtyna.topvid.patterns.AdapterPattern;

import java.io.IOException;

@AdapterPattern
public class Example {

    public static void main(String[] args) throws IOException {
        FileOperations fileOperationsUsingUglyAdaptee =
            new FileOperationsAdapter(new VeryUglyLegacyFileOperations());
        Client client = new Client(fileOperationsUsingUglyAdaptee);
        client.runDemo();
    }
}
