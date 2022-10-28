
package io.nicky.auth.minecraft;

import org.apache.commons.io.IOCase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

/**
 * The type Filename utils.
 */
public class FilenameUtils {
    /**
     * The constant EXTENSION_SEPARATOR.
     */
    public static final char EXTENSION_SEPARATOR = '.';
    /**
     * The constant EXTENSION_SEPARATOR_STR.
     */
    public static final String EXTENSION_SEPARATOR_STR = Character.toString('.');
    private static final char UNIX_SEPARATOR = '/';
    private static final char WINDOWS_SEPARATOR = '\\';
    private static final char SYSTEM_SEPARATOR;
    private static final char OTHER_SEPARATOR;

    /**
     * Instantiates a new Filename utils.
     */
    public FilenameUtils() {
    }

    /**
     * Is system windows boolean.
     *
     * @return the boolean
     */
    static boolean isSystemWindows() {
        return SYSTEM_SEPARATOR == '\\';
    }

    private static boolean isSeparator(char ch) {
        return ch == '/' || ch == '\\';
    }

    /**
     * Normalize string.
     *
     * @param filename the filename
     * @return the string
     */
    public static String normalize(String filename) {
        return doNormalize(filename, SYSTEM_SEPARATOR, true);
    }

    /**
     * Normalize string.
     *
     * @param filename      the filename
     * @param unixSeparator the unix separator
     * @return the string
     */
    public static String normalize(String filename, boolean unixSeparator) {
        char separator = (char) (unixSeparator ? 47 : 92);
        return doNormalize(filename, (char)separator, true);
    }

    /**
     * Normalize no end separator string.
     *
     * @param filename the filename
     * @return the string
     */
    public static String normalizeNoEndSeparator(String filename) {
        return doNormalize(filename, SYSTEM_SEPARATOR, false);
    }

    /**
     * Normalize no end separator string.
     *
     * @param filename      the filename
     * @param unixSeparator the unix separator
     * @return the string
     */
    public static String normalizeNoEndSeparator(String filename, boolean unixSeparator) {
        char separator = (char) (unixSeparator ? 47 : 92);
        return doNormalize(filename, (char)separator, false);
    }

    private static String doNormalize(String filename, char separator, boolean keepSeparator) {
        if (filename == null) {
            return null;
        } else {
            int size = filename.length();
            if (size == 0) {
                return filename;
            } else {
                int prefix = getPrefixLength(filename);
                if (prefix < 0) {
                    return null;
                } else {
                    char[] array = new char[size + 2];
                    filename.getChars(0, filename.length(), array, 0);
                    char otherSeparator = separator == SYSTEM_SEPARATOR ? OTHER_SEPARATOR : SYSTEM_SEPARATOR;

                    for(int i = 0; i < array.length; ++i) {
                        if (array[i] == otherSeparator) {
                            array[i] = separator;
                        }
                    }

                    boolean lastIsDirectory = true;
                    if (array[size - 1] != separator) {
                        array[size++] = separator;
                        lastIsDirectory = false;
                    }

                    int i;
                    for(i = prefix + 1; i < size; ++i) {
                        if (array[i] == separator && array[i - 1] == separator) {
                            System.arraycopy(array, i, array, i - 1, size - i);
                            --size;
                            --i;
                        }
                    }

                    for(i = prefix + 1; i < size; ++i) {
                        if (array[i] == separator && array[i - 1] == '.' && (i == prefix + 1 || array[i - 2] == separator)) {
                            if (i == size - 1) {
                                lastIsDirectory = true;
                            }

                            System.arraycopy(array, i + 1, array, i - 1, size - i);
                            size -= 2;
                            --i;
                        }
                    }

                    label109:
                    for(i = prefix + 2; i < size; ++i) {
                        if (array[i] == separator && array[i - 1] == '.' && array[i - 2] == '.' && (i == prefix + 2 || array[i - 3] == separator)) {
                            if (i == prefix + 2) {
                                return null;
                            }

                            if (i == size - 1) {
                                lastIsDirectory = true;
                            }

                            for(int j = i - 4; j >= prefix; --j) {
                                if (array[j] == separator) {
                                    System.arraycopy(array, i + 1, array, j + 1, size - i);
                                    size -= i - j;
                                    i = j + 1;
                                    continue label109;
                                }
                            }

                            System.arraycopy(array, i + 1, array, prefix, size - i);
                            size -= i + 1 - prefix;
                            i = prefix + 1;
                        }
                    }

                    if (size <= 0) {
                        return "";
                    } else if (size <= prefix) {
                        return new String(array, 0, size);
                    } else if (lastIsDirectory && keepSeparator) {
                        return new String(array, 0, size);
                    } else {
                        return new String(array, 0, size - 1);
                    }
                }
            }
        }
    }

    /**
     * Concat string.
     *
     * @param basePath          the base path
     * @param fullFilenameToAdd the full filename to add
     * @return the string
     */
    public static String concat(String basePath, String fullFilenameToAdd) {
        int prefix = getPrefixLength(fullFilenameToAdd);
        if (prefix < 0) {
            return null;
        } else if (prefix > 0) {
            return normalize(fullFilenameToAdd);
        } else if (basePath == null) {
            return null;
        } else {
            int len = basePath.length();
            if (len == 0) {
                return normalize(fullFilenameToAdd);
            } else {
                char ch = basePath.charAt(len - 1);
                return isSeparator(ch) ? normalize(basePath + fullFilenameToAdd) : normalize(basePath + '/' + fullFilenameToAdd);
            }
        }
    }

    /**
     * Directory contains boolean.
     *
     * @param canonicalParent the canonical parent
     * @param canonicalChild  the canonical child
     * @return the boolean
     * @throws IOException the io exception
     */
    public static boolean directoryContains(String canonicalParent, String canonicalChild) throws IOException {
        if (canonicalParent == null) {
            throw new IllegalArgumentException("Directory must not be null");
        } else if (canonicalChild == null) {
            return false;
        } else {
            return IOCase.SYSTEM.checkEquals(canonicalParent, canonicalChild) ? false : IOCase.SYSTEM.checkStartsWith(canonicalChild, canonicalParent);
        }
    }

    /**
     * Separators to unix string.
     *
     * @param path the path
     * @return the string
     */
    public static String separatorsToUnix(String path) {
        return path != null && path.indexOf(92) != -1 ? path.replace('\\', '/') : path;
    }

    /**
     * Separators to windows string.
     *
     * @param path the path
     * @return the string
     */
    public static String separatorsToWindows(String path) {
        return path != null && path.indexOf(47) != -1 ? path.replace('/', '\\') : path;
    }

    /**
     * Separators to system string.
     *
     * @param path the path
     * @return the string
     */
    public static String separatorsToSystem(String path) {
        if (path == null) {
            return null;
        } else {
            return isSystemWindows() ? separatorsToWindows(path) : separatorsToUnix(path);
        }
    }

    /**
     * Gets prefix length.
     *
     * @param filename the filename
     * @return the prefix length
     */
    public static int getPrefixLength(String filename) {
        if (filename == null) {
            return -1;
        } else {
            int len = filename.length();
            if (len == 0) {
                return 0;
            } else {
                char ch0 = filename.charAt(0);
                if (ch0 == ':') {
                    return -1;
                } else if (len == 1) {
                    if (ch0 == '~') {
                        return 2;
                    } else {
                        return isSeparator(ch0) ? 1 : 0;
                    }
                } else {
                    int posUnix;
                    if (ch0 == '~') {
                        posUnix = filename.indexOf(92, 1);
                        if (posUnix == -1 && posUnix == -1) {
                            return len + 1;
                        } else {
                            posUnix = posUnix == -1 ? posUnix : posUnix;
                            posUnix = posUnix == -1 ? posUnix : posUnix;
                            return Math.min(posUnix, posUnix) + 1;
                        }
                    } else {
                        char ch1 = filename.charAt(1);
                        if (ch1 == ':') {
                            ch0 = Character.toUpperCase(ch0);
                            if (ch0 >= 'A' && ch0 <= 'Z') {
                                return len != 2 && isSeparator(filename.charAt(2)) ? 3 : 2;
                            } else {
                                return -1;
                            }
                        } else if (isSeparator(ch0) && isSeparator(ch1)) {
                            posUnix = filename.indexOf(47, 2);
                            int posWin = filename.indexOf(92, 2);
                            if ((posUnix != -1 || posWin != -1) && posUnix != 2 && posWin != 2) {
                                posUnix = posUnix == -1 ? posWin : posUnix;
                                posWin = posWin == -1 ? posUnix : posWin;
                                return Math.min(posUnix, posWin) + 1;
                            } else {
                                return -1;
                            }
                        } else {
                            return isSeparator(ch0) ? 1 : 0;
                        }
                    }
                }
            }
        }
    }

    /**
     * Index of last separator int.
     *
     * @param filename the filename
     * @return the int
     */
    public static int indexOfLastSeparator(String filename) {
        if (filename == null) {
            return -1;
        } else {
            int lastUnixPos = filename.lastIndexOf(47);
            int lastWindowsPos = filename.lastIndexOf(92);
            return Math.max(lastUnixPos, lastWindowsPos);
        }
    }

    /**
     * Index of extension int.
     *
     * @param filename the filename
     * @return the int
     */
    public static int indexOfExtension(String filename) {
        if (filename == null) {
            return -1;
        } else {
            int extensionPos = filename.lastIndexOf(46);
            int lastSeparator = indexOfLastSeparator(filename);
            return lastSeparator > extensionPos ? -1 : extensionPos;
        }
    }

    /**
     * Gets prefix.
     *
     * @param filename the filename
     * @return the prefix
     */
    public static String getPrefix(String filename) {
        if (filename == null) {
            return null;
        } else {
            int len = getPrefixLength(filename);
            if (len < 0) {
                return null;
            } else {
                return len > filename.length() ? filename + '/' : filename.substring(0, len);
            }
        }
    }

    /**
     * Gets path.
     *
     * @param filename the filename
     * @return the path
     */
    public static String getPath(String filename) {
        return doGetPath(filename, 1);
    }

    /**
     * Gets path no end separator.
     *
     * @param filename the filename
     * @return the path no end separator
     */
    public static String getPathNoEndSeparator(String filename) {
        return doGetPath(filename, 0);
    }

    private static String doGetPath(String filename, int separatorAdd) {
        if (filename == null) {
            return null;
        } else {
            int prefix = getPrefixLength(filename);
            if (prefix < 0) {
                return null;
            } else {
                int index = indexOfLastSeparator(filename);
                int endIndex = index + separatorAdd;
                return prefix < filename.length() && index >= 0 && prefix < endIndex ? filename.substring(prefix, endIndex) : "";
            }
        }
    }

    /**
     * Gets full path.
     *
     * @param filename the filename
     * @return the full path
     */
    public static String getFullPath(String filename) {
        return doGetFullPath(filename, true);
    }

    /**
     * Gets full path no end separator.
     *
     * @param filename the filename
     * @return the full path no end separator
     */
    public static String getFullPathNoEndSeparator(String filename) {
        return doGetFullPath(filename, false);
    }

    private static String doGetFullPath(String filename, boolean includeSeparator) {
        if (filename == null) {
            return null;
        } else {
            int prefix = getPrefixLength(filename);
            if (prefix < 0) {
                return null;
            } else if (prefix >= filename.length()) {
                return includeSeparator ? getPrefix(filename) : filename;
            } else {
                int index = indexOfLastSeparator(filename);
                if (index < 0) {
                    return filename.substring(0, prefix);
                } else {
                    int end = index + (includeSeparator ? 1 : 0);
                    if (end == 0) {
                        ++end;
                    }

                    return filename.substring(0, end);
                }
            }
        }
    }

    /**
     * Gets name.
     *
     * @param filename the filename
     * @return the name
     */
    public static String getName(String filename) {
        if (filename == null) {
            return null;
        } else {
            int index = indexOfLastSeparator(filename);
            return filename.substring(index + 1);
        }
    }

    /**
     * Gets base name.
     *
     * @param filename the filename
     * @return the base name
     */
    public static String getBaseName(String filename) {
        return removeExtension(getName(filename));
    }

    /**
     * Gets extension.
     *
     * @param filename the filename
     * @return the extension
     */
    public static String getExtension(String filename) {
        if (filename == null) {
            return null;
        } else {
            int index = indexOfExtension(filename);
            return index == -1 ? "" : filename.substring(index + 1);
        }
    }

    /**
     * Remove extension string.
     *
     * @param filename the filename
     * @return the string
     */
    public static String removeExtension(String filename) {
        if (filename == null) {
            return null;
        } else {
            int index = indexOfExtension(filename);
            return index == -1 ? filename : filename.substring(0, index);
        }
    }

    /**
     * Equals boolean.
     *
     * @param filename1 the filename 1
     * @param filename2 the filename 2
     * @return the boolean
     */
    public static boolean equals(String filename1, String filename2) {
        return equals(filename1, filename2, false, IOCase.SENSITIVE);
    }

    /**
     * Equals on system boolean.
     *
     * @param filename1 the filename 1
     * @param filename2 the filename 2
     * @return the boolean
     */
    public static boolean equalsOnSystem(String filename1, String filename2) {
        return equals(filename1, filename2, false, IOCase.SYSTEM);
    }

    /**
     * Equals normalized boolean.
     *
     * @param filename1 the filename 1
     * @param filename2 the filename 2
     * @return the boolean
     */
    public static boolean equalsNormalized(String filename1, String filename2) {
        return equals(filename1, filename2, true, IOCase.SENSITIVE);
    }

    /**
     * Equals normalized on system boolean.
     *
     * @param filename1 the filename 1
     * @param filename2 the filename 2
     * @return the boolean
     */
    public static boolean equalsNormalizedOnSystem(String filename1, String filename2) {
        return equals(filename1, filename2, true, IOCase.SYSTEM);
    }

    /**
     * Equals boolean.
     *
     * @param filename1       the filename 1
     * @param filename2       the filename 2
     * @param normalized      the normalized
     * @param caseSensitivity the case sensitivity
     * @return the boolean
     */
    public static boolean equals(String filename1, String filename2, boolean normalized, IOCase caseSensitivity) {
        if (filename1 != null && filename2 != null) {
            if (normalized) {
                filename1 = normalize(filename1);
                filename2 = normalize(filename2);
                if (filename1 == null || filename2 == null) {
                    throw new NullPointerException("Error normalizing one or both of the file names");
                }
            }

            if (caseSensitivity == null) {
                caseSensitivity = IOCase.SENSITIVE;
            }

            return caseSensitivity.checkEquals(filename1, filename2);
        } else {
            return filename1 == null && filename2 == null;
        }
    }

    /**
     * Is extension boolean.
     *
     * @param filename  the filename
     * @param extension the extension
     * @return the boolean
     */
    public static boolean isExtension(String filename, String extension) {
        if (filename == null) {
            return false;
        } else if (extension != null && extension.length() != 0) {
            String fileExt = getExtension(filename);
            return fileExt.equals(extension);
        } else {
            return indexOfExtension(filename) == -1;
        }
    }

    /**
     * Is extension boolean.
     *
     * @param filename   the filename
     * @param extensions the extensions
     * @return the boolean
     */
    public static boolean isExtension(String filename, String[] extensions) {
        if (filename == null) {
            return false;
        } else if (extensions != null && extensions.length != 0) {
            String fileExt = getExtension(filename);
            String[] arr$ = extensions;
            int len$ = extensions.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String extension = arr$[i$];
                if (fileExt.equals(extension)) {
                    return true;
                }
            }

            return false;
        } else {
            return indexOfExtension(filename) == -1;
        }
    }

    /**
     * Is extension boolean.
     *
     * @param filename   the filename
     * @param extensions the extensions
     * @return the boolean
     */
    public static boolean isExtension(String filename, Collection<String> extensions) {
        if (filename == null) {
            return false;
        } else if (extensions != null && !extensions.isEmpty()) {
            String fileExt = getExtension(filename);
            Iterator i$ = extensions.iterator();

            String extension;
            do {
                if (!i$.hasNext()) {
                    return false;
                }

                extension = (String)i$.next();
            } while(!fileExt.equals(extension));

            return true;
        } else {
            return indexOfExtension(filename) == -1;
        }
    }

    /**
     * Wildcard match boolean.
     *
     * @param filename        the filename
     * @param wildcardMatcher the wildcard matcher
     * @return the boolean
     */
    public static boolean wildcardMatch(String filename, String wildcardMatcher) {
        return wildcardMatch(filename, wildcardMatcher, IOCase.SENSITIVE);
    }

    /**
     * Wildcard match on system boolean.
     *
     * @param filename        the filename
     * @param wildcardMatcher the wildcard matcher
     * @return the boolean
     */
    public static boolean wildcardMatchOnSystem(String filename, String wildcardMatcher) {
        return wildcardMatch(filename, wildcardMatcher, IOCase.SYSTEM);
    }

    /**
     * Wildcard match boolean.
     *
     * @param filename        the filename
     * @param wildcardMatcher the wildcard matcher
     * @param caseSensitivity the case sensitivity
     * @return the boolean
     */
    public static boolean wildcardMatch(String filename, String wildcardMatcher, IOCase caseSensitivity) {
        if (filename == null && wildcardMatcher == null) {
            return true;
        } else if (filename != null && wildcardMatcher != null) {
            if (caseSensitivity == null) {
                caseSensitivity = IOCase.SENSITIVE;
            }

            String[] wcs = splitOnTokens(wildcardMatcher);
            boolean anyChars = false;
            int textIdx = 0;
            int wcsIdx = 0;
            Stack backtrack = new Stack();

            do {
                if (backtrack.size() > 0) {
                    int[] array = (int[])backtrack.pop();
                    wcsIdx = array[0];
                    textIdx = array[1];
                    anyChars = true;
                }

                for(; wcsIdx < wcs.length; ++wcsIdx) {
                    if (wcs[wcsIdx].equals("?")) {
                        ++textIdx;
                        if (textIdx > filename.length()) {
                            break;
                        }

                        anyChars = false;
                    } else if (wcs[wcsIdx].equals("*")) {
                        anyChars = true;
                        if (wcsIdx == wcs.length - 1) {
                            textIdx = filename.length();
                        }
                    } else {
                        if (anyChars) {
                            textIdx = caseSensitivity.checkIndexOf(filename, textIdx, wcs[wcsIdx]);
                            if (textIdx == -1) {
                                break;
                            }

                            int repeat = caseSensitivity.checkIndexOf(filename, textIdx + 1, wcs[wcsIdx]);
                            if (repeat >= 0) {
                                backtrack.push(new int[]{wcsIdx, repeat});
                            }
                        } else if (!caseSensitivity.checkRegionMatches(filename, textIdx, wcs[wcsIdx])) {
                            break;
                        }

                        textIdx += wcs[wcsIdx].length();
                        anyChars = false;
                    }
                }

                if (wcsIdx == wcs.length && textIdx == filename.length()) {
                    return true;
                }
            } while(backtrack.size() > 0);

            return false;
        } else {
            return false;
        }
    }

    /**
     * Split on tokens string [ ].
     *
     * @param text the text
     * @return the string [ ]
     */
    static String[] splitOnTokens(String text) {
        if (text.indexOf(63) == -1 && text.indexOf(42) == -1) {
            return new String[]{text};
        } else {
            char[] array = text.toCharArray();
            ArrayList<String> list = new ArrayList();
            StringBuilder buffer = new StringBuilder();

            for(int i = 0; i < array.length; ++i) {
                if (array[i] != '?' && array[i] != '*') {
                    buffer.append(array[i]);
                } else {
                    if (buffer.length() != 0) {
                        list.add(buffer.toString());
                        buffer.setLength(0);
                    }

                    if (array[i] == '?') {
                        list.add("?");
                    } else if (list.isEmpty() || i > 0 && !((String)list.get(list.size() - 1)).equals("*")) {
                        list.add("*");
                    }
                }
            }

            if (buffer.length() != 0) {
                list.add(buffer.toString());
            }

            return (String[])list.toArray(new String[list.size()]);
        }
    }

    static {
        SYSTEM_SEPARATOR = File.separatorChar;
        if (isSystemWindows()) {
            OTHER_SEPARATOR = '/';
        } else {
            OTHER_SEPARATOR = '\\';
        }

    }
}
