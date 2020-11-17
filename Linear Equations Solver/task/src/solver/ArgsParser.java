package solver;

public class ArgsParser {
    String[] args;
    ArgsParser(String[] args) {
        this.args = args;
    }

    public String inputFileName() {
        if (args != null && args.length > 0) {
            if (args[0].equals("-in")) {
                return args[1];
            }
        }
        return null;
    }
    public String outputFileName() {
        if (args != null && args.length > 0) {
            if (args[2].equals("-out")) {
                return args[3];
            }
        }
        return null;
    }
}
