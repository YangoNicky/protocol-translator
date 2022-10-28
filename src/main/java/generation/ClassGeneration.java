package generation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public interface ClassGeneration {

    File OUT_PUT = Paths.get("./generated/").toFile();

    public default void createFolder() {

        if (!OUT_PUT.exists() && !OUT_PUT.mkdirs())
            throw new RuntimeException("Can't create folder!");

    }

    public default void makeDirectory(String name) {
        final File file = new File(OUT_PUT, name);

        if (!file.exists() && !file.mkdirs())
            throw new RuntimeException("Can't create folder!");
    }

    public default void writeFile(final File directory, final String name, final String content) {
        try {
            final File subFile = new File(directory, name);
            final FileWriter fileWriter = new FileWriter(subFile);
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();
        } catch (final Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public default String readFile(final String name) {
        try {
            final Scanner scanner = new Scanner(new File(OUT_PUT, name));
            final StringBuilder content = new StringBuilder();

            while (scanner.hasNextLine())
                content.append(scanner.nextLine())
                        .append("\n");

            scanner.close();

            return content.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
