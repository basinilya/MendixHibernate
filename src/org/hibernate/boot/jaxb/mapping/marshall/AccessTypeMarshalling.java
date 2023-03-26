package org.hibernate.boot.jaxb.mapping.marshall;

import jakarta.persistence.AccessType;

/**
 * fix for https://hibernate.atlassian.net/browse/HHH-16373
 */
public class AccessTypeMarshalling {

    public static AccessType fromXml(final String name) {
        return AccessType.valueOf(name);
    }

    public static String toXml(final AccessType accessType) {
        return accessType == null ? null : accessType.name();
    }
}
