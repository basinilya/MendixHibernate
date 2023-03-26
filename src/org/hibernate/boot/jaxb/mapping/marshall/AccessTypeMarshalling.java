package org.hibernate.boot.jaxb.mapping.marshall;

import jakarta.persistence.AccessType;

/**
 * JAXB marshalling for JPA's {@link AccessType}
 *
 * @author Steve Ebersole
 */
public class AccessTypeMarshalling {

    public static AccessType fromXml(final String name) {
        return AccessType.valueOf(name);
    }

    public static String toXml(final AccessType accessType) {
        return accessType == null ? null : accessType.name();
    }
}
