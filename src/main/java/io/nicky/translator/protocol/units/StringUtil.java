package io.nicky.translator.protocol.units;

public final class StringUtil {

    public static String fancyToString(final Object... elements) {
        final StringBuilder content = new StringBuilder();
        int position = 0;
        for (Object element : elements) {
            content.append(element.toString());

            if (position >= elements.length)
                break;

            content.append(", ");

            position++;
        }

        return content.toString();
    }

}
