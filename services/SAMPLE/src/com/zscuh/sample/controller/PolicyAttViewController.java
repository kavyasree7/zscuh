/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.zscuh.sample.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.sql.Timestamp;
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

import com.zscuh.sample.PolicyAttView;
import com.zscuh.sample.PolicyAttViewId;
import com.zscuh.sample.service.PolicyAttViewService;


/**
 * Controller object for domain model class PolicyAttView.
 * @see PolicyAttView
 */
@RestController("SAMPLE.PolicyAttViewController")
@Api(value = "PolicyAttViewController", description = "Exposes APIs to work with PolicyAttView resource.")
@RequestMapping("/SAMPLE/PolicyAttView")
public class PolicyAttViewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PolicyAttViewController.class);

    @Autowired
	@Qualifier("SAMPLE.PolicyAttViewService")
	private PolicyAttViewService policyAttViewService;

	@ApiOperation(value = "Creates a new PolicyAttView instance.")
@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
public PolicyAttView createPolicyAttView(@RequestBody PolicyAttView policyAttView) {
		LOGGER.debug("Create PolicyAttView with information: {}" , policyAttView);

		policyAttView = policyAttViewService.create(policyAttView);
		LOGGER.debug("Created PolicyAttView with information: {}" , policyAttView);

	    return policyAttView;
	}

@ApiOperation(value = "Returns the PolicyAttView instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public PolicyAttView getPolicyAttView(@RequestParam("bank") String bank,@RequestParam("busBegin") Timestamp busBegin,@RequestParam("busEnd") Timestamp busEnd,@RequestParam("sysBegin") Timestamp sysBegin,@RequestParam("sysEnd") Timestamp sysEnd,@RequestParam("transId") Timestamp transId,@RequestParam("client") String client,@RequestParam("type") String type,@RequestParam("pay") Integer pay) throws EntityNotFoundException {

        PolicyAttViewId policyattviewId = new PolicyAttViewId();
        policyattviewId.setBank(bank);
        policyattviewId.setBusBegin(busBegin);
        policyattviewId.setBusEnd(busEnd);
        policyattviewId.setSysBegin(sysBegin);
        policyattviewId.setSysEnd(sysEnd);
        policyattviewId.setTransId(transId);
        policyattviewId.setClient(client);
        policyattviewId.setType(type);
        policyattviewId.setPay(pay);

        LOGGER.debug("Getting PolicyAttView with id: {}" , policyattviewId);
        PolicyAttView policyAttView = policyAttViewService.getById(policyattviewId);
        LOGGER.debug("PolicyAttView details with id: {}" , policyAttView);

        return policyAttView;
    }



    @ApiOperation(value = "Updates the PolicyAttView instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public PolicyAttView editPolicyAttView(@RequestParam("bank") String bank,@RequestParam("busBegin") Timestamp busBegin,@RequestParam("busEnd") Timestamp busEnd,@RequestParam("sysBegin") Timestamp sysBegin,@RequestParam("sysEnd") Timestamp sysEnd,@RequestParam("transId") Timestamp transId,@RequestParam("client") String client,@RequestParam("type") String type,@RequestParam("pay") Integer pay, @RequestBody PolicyAttView policyAttView) throws EntityNotFoundException {

        policyAttView.setBank(bank);
        policyAttView.setBusBegin(busBegin);
        policyAttView.setBusEnd(busEnd);
        policyAttView.setSysBegin(sysBegin);
        policyAttView.setSysEnd(sysEnd);
        policyAttView.setTransId(transId);
        policyAttView.setClient(client);
        policyAttView.setType(type);
        policyAttView.setPay(pay);

        LOGGER.debug("PolicyAttView details with id is updated with: {}" , policyAttView);

        return policyAttViewService.update(policyAttView);
    }


    @ApiOperation(value = "Deletes the PolicyAttView instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deletePolicyAttView(@RequestParam("bank") String bank,@RequestParam("busBegin") Timestamp busBegin,@RequestParam("busEnd") Timestamp busEnd,@RequestParam("sysBegin") Timestamp sysBegin,@RequestParam("sysEnd") Timestamp sysEnd,@RequestParam("transId") Timestamp transId,@RequestParam("client") String client,@RequestParam("type") String type,@RequestParam("pay") Integer pay) throws EntityNotFoundException {

        PolicyAttViewId policyattviewId = new PolicyAttViewId();
        policyattviewId.setBank(bank);
        policyattviewId.setBusBegin(busBegin);
        policyattviewId.setBusEnd(busEnd);
        policyattviewId.setSysBegin(sysBegin);
        policyattviewId.setSysEnd(sysEnd);
        policyattviewId.setTransId(transId);
        policyattviewId.setClient(client);
        policyattviewId.setType(type);
        policyattviewId.setPay(pay);

        LOGGER.debug("Deleting PolicyAttView with id: {}" , policyattviewId);
        PolicyAttView policyAttView = policyAttViewService.delete(policyattviewId);

        return policyAttView != null;
    }


    /**
     * @deprecated Use {@link #findPolicyAttViews(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of PolicyAttView instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<PolicyAttView> searchPolicyAttViewsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering PolicyAttViews list");
        return policyAttViewService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of PolicyAttView instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<PolicyAttView> findPolicyAttViews(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering PolicyAttViews list");
        return policyAttViewService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of PolicyAttView instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<PolicyAttView> filterPolicyAttViews(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering PolicyAttViews list");
        return policyAttViewService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportPolicyAttViews(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return policyAttViewService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of PolicyAttView instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countPolicyAttViews( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting PolicyAttViews");
		return policyAttViewService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getPolicyAttViewAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return policyAttViewService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service PolicyAttViewService instance
	 */
	protected void setPolicyAttViewService(PolicyAttViewService service) {
		this.policyAttViewService = service;
	}

}

