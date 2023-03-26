package org.hibernate.boot.jaxb.mapping.marshall;

import jakarta.persistence.FetchType;

/**
 * fix for https://hibernate.atlassian.net/browse/HHH-16373
 */
public class FetchTypeMarshalling {

    public static FetchType fromXml(final String name) {
        return FetchType.valueOf(name);
    }

    public static String toXml(final FetchType fetchType) {
        return fetchType == null ? null : fetchType.name();
    }
}
