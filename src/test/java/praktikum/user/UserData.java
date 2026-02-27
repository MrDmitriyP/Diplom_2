package praktikum.user;

import java.time.LocalDate;

public class UserData {
    public static final String EMAIL = "dapavlik" + LocalDate.now() + "@test.ru";
    public static final String NEW_EMAIL = "pavlikda" + LocalDate.now() + "PDA" + "@test.ru";
    public static final String PASSWORD = "GfhjkmPDA";
    public static final String NEW_PASSWORD = "Gfdhjkm" + System.currentTimeMillis();
    public static final String NAME = "Дмитрий";
    public static final String NEW_NAME = "Антон";
}
