package org.hibernate.boot.jaxb.mapping.marshall;

import jakarta.persistence.FetchType;

/**
 * JAXB marshalling for {@link FetchType}
 *
 * @author Steve Ebersole
 */
public class FetchTypeMarshalling {

    public static FetchType fromXml(final String name) {
        return FetchType.valueOf(name);
    }

    public static String toXml(final FetchType fetchType) {
        return fetchType == null ? null : fetchType.name();
    }
}
