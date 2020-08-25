package com.petrovpavel.localservice.jpa;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

/**
 * NamingStrategy.
 *
 * @author Pavel_Petrov
 */
public class NamingStrategy extends SpringPhysicalNamingStrategy {

    public static final String PREFIX = "scb";

    private static Identifier prefixName(Identifier name) {
        return new Identifier(PREFIX + "_" + name.getText(), name.isQuoted());
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        name = prefixName(name);
        return super.toPhysicalTableName(name, jdbcEnvironment);
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        name = prefixName(name);
        return super.toPhysicalSequenceName(name, jdbcEnvironment);
    }
}
