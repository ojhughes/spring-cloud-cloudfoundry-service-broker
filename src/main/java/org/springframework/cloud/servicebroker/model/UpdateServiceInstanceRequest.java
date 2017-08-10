package org.springframework.cloud.servicebroker.model;

import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Details of a request to update a service instance.
 *
 * @author Scott Frederick
 */
@Getter
@ToString(callSuper = true, exclude = {"serviceDefinition"})
@EqualsAndHashCode(callSuper = true)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateServiceInstanceRequest extends AsyncParameterizedServiceInstanceRequest {

	/**
	 * The ID of the service to update, from the broker catalog.
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("service_id")
	private final String serviceDefinitionId;

	/**
	 * The ID of the plan to update within the service, from the broker catalog.
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("plan_id")
	private final String planId;

	/**
	 * Information about the service instance prior to the update request.
	 */
	@JsonSerialize
	@JsonProperty("previous_values")
	private final PreviousValues previousValues;

	/**
	 * Vendor specific fields for request. In future will subsume space_id and organization_id
	 */
	@JsonSerialize
	@JsonProperty("context")
	private final ServiceInstanceRequestContext context;

	/**
	 * The Cloud Controller GUID of the service instance to update.
	 */
	@JsonIgnore
	private transient String serviceInstanceId;

	/**
	 * The {@link ServiceDefinition} of the service to update. This is resolved from the
	 * <code>serviceDefinitionId</code> as a convenience to the broker.
	 */
	@JsonIgnore
	private transient ServiceDefinition serviceDefinition;

	public UpdateServiceInstanceRequest() {
		super(null);
		this.serviceDefinitionId = null;
		this.planId = null;
		this.previousValues = null;
		this.context = new ServiceInstanceRequestContext();
	}

	public UpdateServiceInstanceRequest(String serviceDefinitionId, String planId,
										Map<String, Object> parameters, PreviousValues previousValues) {
		super(parameters);
		this.serviceDefinitionId = serviceDefinitionId;
		this.planId = planId;
		this.previousValues = previousValues;
		this.context = new ServiceInstanceRequestContext();
	}

	public UpdateServiceInstanceRequest(Map<String, Object> parameters, String serviceDefinitionId, String planId,
										PreviousValues previousValues, ServiceInstanceRequestContext context) {
		super(parameters);
		this.serviceDefinitionId = serviceDefinitionId;
		this.planId = planId;
		this.previousValues = previousValues;
		this.context = context;
	}

	public UpdateServiceInstanceRequest(Map<String, Object> parameters, String serviceDefinitionId, String planId) {
		this(serviceDefinitionId, planId, parameters, null);
	}

	public UpdateServiceInstanceRequest(String serviceDefinitionId, String planId) {
		this(serviceDefinitionId, planId, new ServiceInstanceRequestContext());
	}

	public UpdateServiceInstanceRequest(String serviceDefinitionId, String planId, ServiceInstanceRequestContext context) {
		this(null, serviceDefinitionId, planId, null, context);
	}

	public UpdateServiceInstanceRequest(String serviceDefinitionId, String planId, PreviousValues previousValues,
										ServiceInstanceRequestContext context) {
		this(null, serviceDefinitionId, planId, previousValues, context);
	}

	public UpdateServiceInstanceRequest withServiceInstanceId(String serviceInstanceId) {
		this.serviceInstanceId = serviceInstanceId;
		return this;
	}

	public UpdateServiceInstanceRequest withServiceDefinition(ServiceDefinition serviceDefinition) {
		this.serviceDefinition = serviceDefinition;
		return this;
	}

	public UpdateServiceInstanceRequest withAsyncAccepted(boolean asyncAccepted) {
		this.asyncAccepted = asyncAccepted;
		return this;
	}

	public UpdateServiceInstanceRequest withCfInstanceId(String cfInstanceId) {
		this.cfInstanceId = cfInstanceId;
		return this;
	}

	public UpdateServiceInstanceRequest withApiInfoLocation(String apiInfoLocation) {
		this.apiInfoLocation = apiInfoLocation;
		return this;
	}

	/**
	 * Information about the service instance prior to the update request.
	 */
	@Getter
	@ToString
	@EqualsAndHashCode
	public static class PreviousValues {
		/**
		 * The ID of the service instance plan prior to the update request.
		 */
		@NotEmpty
		@JsonSerialize
		@JsonProperty("plan_id")
		private final String planId;

		public PreviousValues() {
			this.planId = null;
		}

		public PreviousValues(String planId) {
			this.planId = planId;
		}
	}
}
