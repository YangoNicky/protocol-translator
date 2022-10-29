package io.nicky.translator.protocol.protocol.json.component;

import java.util.regex.Pattern;

public enum EnumColor {

    // colors
    BLACK('0'),
    DARK_BLUE('1'),
    DARK_GREEN('2'),
    DARK_AQUA('3'),
    DARK_RED('4'),
    DARK_PURPLE('5'),
    GOLD('6'),
    GRAY('7'),
    DARK_GRAY('8'),
    BLUE('9'),
    GREEN('a'),
    AQUA('b'),
    RED('c'),
    LIGHT_PURPLE('d'),
    YELLOW('e'),
    WHITE('f'),

    // effects
    MAGIC('k'),
    BOLD('l'),
    STRIKETHROUGH('m'),
    UNDERLINE('n'),
    ITALIC('o'),
    RESET('r'),

    ;

    public static final char COLOR_CHAR = '§';
    public static final String ALL_CODES = "0123456789AaBbCcDdEeFfKkLlMmNnOoRr";
    public static final Pattern STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + COLOR_CHAR + "[0-9A-FK-OR]");

    private final String toString;
    private final Pattern pattern;

    EnumColor(char code) {
        this.toString = new String(new char[]{COLOR_CHAR, code});
        this.pattern = Pattern.compile("(?i)" + this.toString());
    }

    public Pattern getPattern() {
        return this.pattern;
    }

    @Override
    public String toString() {
        return this.toString;
    }

    public static String stripColor(String input) {
        return STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
    }

    public static String stripColor(char altColorChar, String input) {
        return stripColor(toColoredString(altColorChar, input));
    }

    public static String toColoredString(char altColorChar, String textToTranslate) {
        char[] b = textToTranslate.toCharArray();
        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == altColorChar && ALL_CODES.indexOf(b[i + 1]) > -1) {
                b[i] = EnumColor.COLOR_CHAR;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }

        return new String(b);
    }
}