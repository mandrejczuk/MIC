package pz1.MIC.enums;

import lombok.Getter;

@Getter
public enum RaceEnum {
    ORC("orc_race"),
    HUMAN("human_race"),
    DWARF("dwarf_race"),
    ELF("elf_race"),
    DARK_ELF("dark_elf_race");

    private final String dbValue;

    RaceEnum(String dbValue) {
        this.dbValue = dbValue;
    }
}
