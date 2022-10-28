package io.nicky.translator.protocol.protocol;

public enum ProtocolVersion {

    V_1_19_2(       "1.19.1",   760),
    V_1_19(         "1.19",     759),
    V_1_18(         "1.18",     757),
    V_1_17_1(       "1.17.1",   756),
    V_1_17(         "1.17",     755),
    V_1_16_5(       "1.16.5",   754),
    V_1_16(         "1.16",     735),
    V_1_15_2(       "1.15.2",   578),
    V_1_15_1(       "1.15.1",   575),
    V_1_15(         "1.15",     573),
    V_1_14_4(       "1.14.4",   498),
    V_1_14_3(       "1.14.3",   490),
    V_1_14_2(       "1.14.2",   485),
    V_1_14_1(       "1.14.1",   480),
    V_1_14(         "1.14",     477),
    V_1_13_2(       "1.13.2",   404),
    V_1_13_1(       "1.13.1",   401),
    V_1_13(         "1.13",     393),
    V_1_12_2(       "1.12.2",   340),
    V_1_12_1(       "1.12.1",   338),
    V_1_12(         "1.12",     335),
    V_1_11_2(       "1.11.2",   316),
    V_1_11(         "1.11",     315),
    V_1_10_2(       "1.10.2",   210),
    V_1_9_4(        "1.9.4",    110),
    V_1_9_1(        "1.9.1",    108),
    V_1_8_8_R1(     "1.8.9",    47),
    V_1_7_10(       "1.7.10",   4),

    NONE(           "None",     0),
    //@formatter:on
    ;

    private final String name;
    private final int protocolId;

    ProtocolVersion(String name, int protocolId) {
        this.name = name;
        this.protocolId = protocolId;
    }

    public String getName() {
        return name;
    }

    public int getProtocolId() {
        return protocolId;
    }
}