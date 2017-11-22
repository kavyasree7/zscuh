/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.zscuh.sample.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.zscuh.sample.PolicyStt;
import com.zscuh.sample.PolicySttId;
import com.zscuh.sample.service.PolicySttService;


/**
 * Controller object for domain model class PolicyStt.
 * @see PolicyStt
 */
@RestController("SAMPLE.PolicySttController")
@Api(value = "PolicySttController", description = "Exposes APIs to work with PolicyStt resource.")
@RequestMapping("/SAMPLE/PolicyStt")
public class PolicySttController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PolicySttController.class);

    @Autowired
	@Qualifier("SAMPLE.PolicySttService")
	private PolicySttService policySttService;

	@ApiOperation(value = "Creates a new PolicyStt instance.")
@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
public PolicyStt createPolicyStt(@RequestBody PolicyStt policyStt) {
		LOGGER.debug("Create PolicyStt with information: {}" , policyStt);

		policyStt = policySttService.create(policyStt);
		LOGGER.debug("Created PolicyStt with information: {}" , policyStt);

	    return policyStt;
	}

@ApiOperation(value = "Returns the PolicyStt instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public PolicyStt getPolicyStt(@RequestParam("bank") String bank,@RequestParam("client") String client) throws EntityNotFoundException {

        PolicySttId policysttId = new PolicySttId();
        policysttId.setBank(bank);
        policysttId.setClient(client);

        LOGGER.debug("Getting PolicyStt with id: {}" , policysttId);
        PolicyStt policyStt = policySttService.getById(policysttId);
        LOGGER.debug("PolicyStt details with id: {}" , policyStt);

        return policyStt;
    }



    @ApiOperation(value = "Updates the PolicyStt instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public PolicyStt editPolicyStt(@RequestParam("bank") String bank,@RequestParam("client") String client, @RequestBody PolicyStt policyStt) throws EntityNotFoundException {

        policyStt.setBank(bank);
        policyStt.setClient(client);

        LOGGER.debug("PolicyStt details with id is updated with: {}" , policyStt);

        return policySttService.update(policyStt);
    }


    @ApiOperation(value = "Deletes the PolicyStt instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deletePolicyStt(@RequestParam("bank") String bank,@RequestParam("client") String client) throws EntityNotFoundException {

        PolicySttId policysttId = new PolicySttId();
        policysttId.setBank(bank);
        policysttId.setClient(client);

        LOGGER.debug("Deleting PolicyStt with id: {}" , policysttId);
        PolicyStt policyStt = policySttService.delete(policysttId);

        return policyStt != null;
    }


    /**
     * @deprecated Use {@link #findPolicyStts(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of PolicyStt instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<PolicyStt> searchPolicySttsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering PolicyStts list");
        return policySttService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of PolicyStt instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<PolicyStt> findPolicyStts(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering PolicyStts list");
        return policySttService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of PolicyStt instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<PolicyStt> filterPolicyStts(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering PolicyStts list");
        return policySttService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportPolicyStts(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return policySttService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of PolicyStt instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countPolicyStts( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting PolicyStts");
		return policySttService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getPolicySttAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return policySttService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service PolicySttService instance
	 */
	protected void setPolicySttService(PolicySttService service) {
		this.policySttService = service;
	}

}

