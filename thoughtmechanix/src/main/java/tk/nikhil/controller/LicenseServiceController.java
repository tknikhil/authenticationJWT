package tk.nikhil.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tk.nikhil.model.License;

@RestController
@RequestMapping(value = "/v1/organization/{organizationId}/licenses")
public class LicenseServiceController {

	@RequestMapping(value = "/{licenseId}", method = RequestMethod.GET)
	public License getLicense(@PathVariable("organizationId") String organizationId,
			@PathVariable("licenseId") String licenseId) {
		return new License().withId(licenseId).withId(licenseId).withLicenseType("Seat").withProductName("Teleco")
				.withOrganizationId("TestOrg");
	}

}
