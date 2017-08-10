package org.springframework.cloud.servicebroker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Container for vendor specific attributes
 */
@Getter
@ToString
@EqualsAndHashCode
public class ServiceInstanceRequestContext {

    /**
     * The PaaS platform eg. cloudfoundry
     */
    @JsonSerialize
    private final String platform;

    /**
     * The Cloud Controller GUID of the organization under which the service is to be provisioned.
     */
    @JsonSerialize
    @JsonProperty("organization_guid")
    private final String organizationGuid;

    /**
     * The Cloud Controller GUID of the space under which the service is to be provisioned.
     */
    @JsonSerialize
    @JsonProperty("space_guid")
    private final String spaceGuid;

    public ServiceInstanceRequestContext() {
        this.platform = null;
        this.organizationGuid = null;
        this.spaceGuid = null;
    }

    public ServiceInstanceRequestContext(String platform) {
        this.platform = platform;
        this.organizationGuid = null;
        this.spaceGuid = null;
    }

    public ServiceInstanceRequestContext(String platform, String organizationGuid, String spaceGuid) {
        this.platform = platform;
        this.organizationGuid = organizationGuid;
        this.spaceGuid = spaceGuid;
    }
}
