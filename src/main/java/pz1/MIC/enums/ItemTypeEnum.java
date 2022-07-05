package pz1.MIC.enums;

import lombok.Getter;

@Getter
public enum ItemTypeEnum {
    SWORD("sword"),
    BOW("bow"),
    STAFF("staff"),
    ARMOUR("armour"),
    ARROWS("arrows"),
    SHIELD("shield"),
    MAGIC("magic"),
    HANDS("hands"),
    HELM("helm"),
    BOOTS("boots");


    private final String dbValue;

    ItemTypeEnum(String dbValue) {
        this.dbValue = dbValue;
    }
}