/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.zscuh.sample.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.zscuh.sample.PolicyAtt1;
import com.zscuh.sample.PolicyAtt1Id;


/**
 * ServiceImpl object for domain model class PolicyAtt1.
 *
 * @see PolicyAtt1
 */
@Service("SAMPLE.PolicyAtt1Service")
@Validated
public class PolicyAtt1ServiceImpl implements PolicyAtt1Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(PolicyAtt1ServiceImpl.class);


    @Autowired
    @Qualifier("SAMPLE.PolicyAtt1Dao")
    private WMGenericDao<PolicyAtt1, PolicyAtt1Id> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<PolicyAtt1, PolicyAtt1Id> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "SAMPLETransactionManager")
    @Override
	public PolicyAtt1 create(PolicyAtt1 policyAtt1) {
        LOGGER.debug("Creating a new PolicyAtt1 with information: {}", policyAtt1);
        PolicyAtt1 policyAtt1Created = this.wmGenericDao.create(policyAtt1);
        return policyAtt1Created;
    }

	@Transactional(readOnly = true, value = "SAMPLETransactionManager")
	@Override
	public PolicyAtt1 getById(PolicyAtt1Id policyatt1Id) throws EntityNotFoundException {
        LOGGER.debug("Finding PolicyAtt1 by id: {}", policyatt1Id);
        PolicyAtt1 policyAtt1 = this.wmGenericDao.findById(policyatt1Id);
        if (policyAtt1 == null){
            LOGGER.debug("No PolicyAtt1 found with id: {}", policyatt1Id);
            throw new EntityNotFoundException(String.valueOf(policyatt1Id));
        }
        return policyAtt1;
    }

    @Transactional(readOnly = true, value = "SAMPLETransactionManager")
	@Override
	public PolicyAtt1 findById(PolicyAtt1Id policyatt1Id) {
        LOGGER.debug("Finding PolicyAtt1 by id: {}", policyatt1Id);
        return this.wmGenericDao.findById(policyatt1Id);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "SAMPLETransactionManager")
	@Override
	public PolicyAtt1 update(PolicyAtt1 policyAtt1) throws EntityNotFoundException {
        LOGGER.debug("Updating PolicyAtt1 with information: {}", policyAtt1);
        this.wmGenericDao.update(policyAtt1);

        PolicyAtt1Id policyatt1Id = new PolicyAtt1Id();
        policyatt1Id.setBank(policyAtt1.getBank());
        policyatt1Id.setClient(policyAtt1.getClient());

        return this.wmGenericDao.findById(policyatt1Id);
    }

    @Transactional(value = "SAMPLETransactionManager")
	@Override
	public PolicyAtt1 delete(PolicyAtt1Id policyatt1Id) throws EntityNotFoundException {
        LOGGER.debug("Deleting PolicyAtt1 with id: {}", policyatt1Id);
        PolicyAtt1 deleted = this.wmGenericDao.findById(policyatt1Id);
        if (deleted == null) {
            LOGGER.debug("No PolicyAtt1 found with id: {}", policyatt1Id);
            throw new EntityNotFoundException(String.valueOf(policyatt1Id));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "SAMPLETransactionManager")
	@Override
	public Page<PolicyAtt1> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all PolicyAtt1s");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "SAMPLETransactionManager")
    @Override
    public Page<PolicyAtt1> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all PolicyAtt1s");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "SAMPLETransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service SAMPLE for table PolicyAtt1 to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "SAMPLETransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "SAMPLETransactionManager")
	@Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }



}

