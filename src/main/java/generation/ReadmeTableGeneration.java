package generation;

import io.nicky.translator.protocol.protocol.ProtocolVersion;

public final class ReadmeTableGeneration implements ClassGeneration {

    public ReadmeTableGeneration() {
        this.createFolder();

        final StringBuilder content = new StringBuilder();
        for (ProtocolVersion value : ProtocolVersion.values()) {
            content.append("- [] Protocol-" + value.getName())
                    .append("\n");
        }
        this.writeFile(OUT_PUT, "readme part", content.toString());
    }

    public static void main(String[] args) {
        new ReadmeTableGeneration();
    }

}
