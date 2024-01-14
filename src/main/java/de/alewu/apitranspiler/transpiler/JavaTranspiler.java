package de.alewu.apitranspiler.transpiler;

public class JavaTranspiler extends PackageWriter {

    public JavaTranspiler() {
        super(".");
    }

    public PackageWriter writePackage(String path) {
        return new PackageWriter(path);
    }

}
