package com.iiuc.config;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.DatabaseVersion;

/**
 * Fixed SQLiteDialect for Hibernate 6.2+.
 * Minimal implementation to prevent classpath and naming strategy conflicts.
 */
public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        // Initialize with the SQLite version you are using
        super(DatabaseVersion.make(3, 32));
    }

    // Hibernate 6.2+ handles naming, types, and functions via internal registries.
    // Do not add manual overrides here as they trigger NoClassDefFoundErrors
    // when mismatched with Spring Boot's internal naming strategies.
}