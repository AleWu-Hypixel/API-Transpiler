package de.alewu.apitranspiler;

import de.alewu.apitranspiler.http.HypixelClient;
import de.alewu.apitranspiler.transpiler.JavaTranspiler;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

public class ApiTranspilerApplication implements Callable<Integer> {

    @Option(names = {"--help", "-h"}, usageHelp = true, description = "Display this help and exit")
    private boolean help;
    @Option(names = {"--path"}, description = "Define the path where the Java code will get generated")
    private String path;
    @Parameters(index = "0", description = "The key used to access the api")
    private String apiKey;

    @Override
    public Integer call() throws Exception {
        JavaTranspiler transpiler = new JavaTranspiler();
        try (var client = new HypixelClient(apiKey)) {
            System.out.println(client.collections());
        }
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new ApiTranspilerApplication()).execute(args);
        System.exit(exitCode);
    }
}
