package io.nicky.auth;

/**
 * The type Agent.
 */
public class Agent
{
    /**
     * The constant MINECRAFT.
     */
    public static final Agent MINECRAFT;
    /**
     * The constant SCROLLS.
     */
    public static final Agent SCROLLS;
    private final String name;
    private final int version;

    /**
     * Instantiates a new Agent.
     *
     * @param name    the name
     * @param version the version
     */
    public Agent(final String name, final int version) {
        super();
        this.name = name;
        this.version = version;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets version.
     *
     * @return the version
     */
    public int getVersion() {
        return this.version;
    }
    
    @Override
    public String toString() {
        return "Agent{name='" + this.name + '\'' + ", version=" + this.version + '}';
    }
    
    static {
        MINECRAFT = new Agent("Minecraft", 1);
        SCROLLS = new Agent("Scrolls", 1);
    }
}
