package generation;

import io.nicky.translator.protocol.protocol.ProtocolVersion;

import java.io.File;
import java.util.Arrays;

public final class ProtocolsGeneration implements ClassGeneration {

    private int current;

    public ProtocolsGeneration() {
        this.createFolder();

        StringBuilder registerBlock = new StringBuilder();

        for (current = 0; current < ProtocolVersion.values().length; current++) {
            final ProtocolVersion preVersion = Arrays.stream(ProtocolVersion.values()).filter(protocolVersion -> protocolVersion.ordinal() == current - 1)
                    .findFirst().orElse(ProtocolVersion.NONE);

            final ProtocolVersion postVersion = Arrays.stream(ProtocolVersion.values()).filter(protocolVersion -> protocolVersion.ordinal() == current + 1)
                    .findFirst().orElse(ProtocolVersion.NONE);

            final ProtocolVersion currentVersion = Arrays.stream(ProtocolVersion.values()).filter(protocolVersion -> protocolVersion.ordinal() == current)
                    .findFirst().orElse(ProtocolVersion.NONE);

            final String clazzName = this.fixClazzName("Protocol" + currentVersion.getName());
            System.out.println(clazzName);

            this.makeDirectory(clazzName.toLowerCase());
            final String clazzSource = this.readFile("ProtocolBase");

            this.writeFile(new File(OUT_PUT, clazzName.toLowerCase()), clazzName + ".java",
                    clazzSource.replace("%ID%", current + "")
                            .replace("%TO%", postVersion.name())
                            .replace("%FROM%", preVersion.name())
                            .replace("%CURRENT%", currentVersion.name())
                            .replace("%NAME%", clazzName)
            );

            registerBlock.append("registerProtocol(new %s());".formatted(clazzName))
                    .append("\n");
        }

        this.writeFile(OUT_PUT, "REGISTER_BLOCK", registerBlock.toString());
    }

    public String fixClazzName(final String source) {
        return source.replace(".", "_")
                .replace("noneTo", "")
                .replace("Tonone", "");
    }

    public static void main(String[] args) {
        new ProtocolsGeneration();
    }

}
