package pz1.MIC.enums;

import lombok.Getter;

@Getter
public enum ClassEnum {
    WARRIOR("warrior_class"),
    ARCHER("archer_class"),
    MAGE("mage_class");

    private final String dbValue;

    ClassEnum(String dbValue) {
        this.dbValue = dbValue;
    }
}
