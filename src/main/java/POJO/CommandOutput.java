package POJO;

public class CommandOutput {
    private String[] output;

    public CommandOutput(String... output) {
        this.output = output;
    }

    public String[] getOutput() {
        return this.output;
    }
}
