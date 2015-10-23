/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.inkwell.internet.slogan.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Slogan}.
 * </p>
 *
 * @author gildas
 * @see Slogan
 * @generated
 */
public class SloganWrapper implements Slogan, ModelWrapper<Slogan> {
	public SloganWrapper(Slogan slogan) {
		_slogan = slogan;
	}

	@Override
	public Class<?> getModelClass() {
		return Slogan.class;
	}

	@Override
	public String getModelClassName() {
		return Slogan.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("sloganId", getSloganId());
		attributes.put("sloganDate", getSloganDate());
		attributes.put("sloganText", getSloganText());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long sloganId = (Long)attributes.get("sloganId");

		if (sloganId != null) {
			setSloganId(sloganId);
		}

		Date sloganDate = (Date)attributes.get("sloganDate");

		if (sloganDate != null) {
			setSloganDate(sloganDate);
		}

		String sloganText = (String)attributes.get("sloganText");

		if (sloganText != null) {
			setSloganText(sloganText);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}
	}

	/**
	* Returns the primary key of this slogan.
	*
	* @return the primary key of this slogan
	*/
	@Override
	public long getPrimaryKey() {
		return _slogan.getPrimaryKey();
	}

	/**
	* Sets the primary key of this slogan.
	*
	* @param primaryKey the primary key of this slogan
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_slogan.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this slogan.
	*
	* @return the uuid of this slogan
	*/
	@Override
	public java.lang.String getUuid() {
		return _slogan.getUuid();
	}

	/**
	* Sets the uuid of this slogan.
	*
	* @param uuid the uuid of this slogan
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_slogan.setUuid(uuid);
	}

	/**
	* Returns the slogan ID of this slogan.
	*
	* @return the slogan ID of this slogan
	*/
	@Override
	public long getSloganId() {
		return _slogan.getSloganId();
	}

	/**
	* Sets the slogan ID of this slogan.
	*
	* @param sloganId the slogan ID of this slogan
	*/
	@Override
	public void setSloganId(long sloganId) {
		_slogan.setSloganId(sloganId);
	}

	/**
	* Returns the slogan date of this slogan.
	*
	* @return the slogan date of this slogan
	*/
	@Override
	public java.util.Date getSloganDate() {
		return _slogan.getSloganDate();
	}

	/**
	* Sets the slogan date of this slogan.
	*
	* @param sloganDate the slogan date of this slogan
	*/
	@Override
	public void setSloganDate(java.util.Date sloganDate) {
		_slogan.setSloganDate(sloganDate);
	}

	/**
	* Returns the slogan text of this slogan.
	*
	* @return the slogan text of this slogan
	*/
	@Override
	public java.lang.String getSloganText() {
		return _slogan.getSloganText();
	}

	/**
	* Sets the slogan text of this slogan.
	*
	* @param sloganText the slogan text of this slogan
	*/
	@Override
	public void setSloganText(java.lang.String sloganText) {
		_slogan.setSloganText(sloganText);
	}

	/**
	* Returns the status of this slogan.
	*
	* @return the status of this slogan
	*/
	@Override
	public int getStatus() {
		return _slogan.getStatus();
	}

	/**
	* Sets the status of this slogan.
	*
	* @param status the status of this slogan
	*/
	@Override
	public void setStatus(int status) {
		_slogan.setStatus(status);
	}

	/**
	* Returns the status by user ID of this slogan.
	*
	* @return the status by user ID of this slogan
	*/
	@Override
	public long getStatusByUserId() {
		return _slogan.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this slogan.
	*
	* @param statusByUserId the status by user ID of this slogan
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_slogan.setStatusByUserId(statusByUserId);
	}

	/**
	* Returns the status by user uuid of this slogan.
	*
	* @return the status by user uuid of this slogan
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _slogan.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this slogan.
	*
	* @param statusByUserUuid the status by user uuid of this slogan
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_slogan.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Returns the status by user name of this slogan.
	*
	* @return the status by user name of this slogan
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _slogan.getStatusByUserName();
	}

	/**
	* Sets the status by user name of this slogan.
	*
	* @param statusByUserName the status by user name of this slogan
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_slogan.setStatusByUserName(statusByUserName);
	}

	/**
	* Returns the status date of this slogan.
	*
	* @return the status date of this slogan
	*/
	@Override
	public java.util.Date getStatusDate() {
		return _slogan.getStatusDate();
	}

	/**
	* Sets the status date of this slogan.
	*
	* @param statusDate the status date of this slogan
	*/
	@Override
	public void setStatusDate(java.util.Date statusDate) {
		_slogan.setStatusDate(statusDate);
	}

	/**
	* Returns the company ID of this slogan.
	*
	* @return the company ID of this slogan
	*/
	@Override
	public long getCompanyId() {
		return _slogan.getCompanyId();
	}

	/**
	* Sets the company ID of this slogan.
	*
	* @param companyId the company ID of this slogan
	*/
	@Override
	public void setCompanyId(long companyId) {
		_slogan.setCompanyId(companyId);
	}

	/**
	* Returns the group ID of this slogan.
	*
	* @return the group ID of this slogan
	*/
	@Override
	public long getGroupId() {
		return _slogan.getGroupId();
	}

	/**
	* Sets the group ID of this slogan.
	*
	* @param groupId the group ID of this slogan
	*/
	@Override
	public void setGroupId(long groupId) {
		_slogan.setGroupId(groupId);
	}

	/**
	* Returns the user ID of this slogan.
	*
	* @return the user ID of this slogan
	*/
	@Override
	public long getUserId() {
		return _slogan.getUserId();
	}

	/**
	* Sets the user ID of this slogan.
	*
	* @param userId the user ID of this slogan
	*/
	@Override
	public void setUserId(long userId) {
		_slogan.setUserId(userId);
	}

	/**
	* Returns the user uuid of this slogan.
	*
	* @return the user uuid of this slogan
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _slogan.getUserUuid();
	}

	/**
	* Sets the user uuid of this slogan.
	*
	* @param userUuid the user uuid of this slogan
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_slogan.setUserUuid(userUuid);
	}

	/**
	* @deprecated As of 6.1.0, replaced by {@link #isApproved()}
	*/
	@Override
	public boolean getApproved() {
		return _slogan.getApproved();
	}

	/**
	* Returns <code>true</code> if this slogan is approved.
	*
	* @return <code>true</code> if this slogan is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _slogan.isApproved();
	}

	/**
	* Returns <code>true</code> if this slogan is denied.
	*
	* @return <code>true</code> if this slogan is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _slogan.isDenied();
	}

	/**
	* Returns <code>true</code> if this slogan is a draft.
	*
	* @return <code>true</code> if this slogan is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _slogan.isDraft();
	}

	/**
	* Returns <code>true</code> if this slogan is expired.
	*
	* @return <code>true</code> if this slogan is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _slogan.isExpired();
	}

	/**
	* Returns <code>true</code> if this slogan is inactive.
	*
	* @return <code>true</code> if this slogan is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _slogan.isInactive();
	}

	/**
	* Returns <code>true</code> if this slogan is incomplete.
	*
	* @return <code>true</code> if this slogan is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _slogan.isIncomplete();
	}

	/**
	* Returns <code>true</code> if this slogan is pending.
	*
	* @return <code>true</code> if this slogan is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _slogan.isPending();
	}

	/**
	* Returns <code>true</code> if this slogan is scheduled.
	*
	* @return <code>true</code> if this slogan is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _slogan.isScheduled();
	}

	@Override
	public boolean isNew() {
		return _slogan.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_slogan.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _slogan.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_slogan.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _slogan.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _slogan.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_slogan.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _slogan.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_slogan.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_slogan.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_slogan.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SloganWrapper((Slogan)_slogan.clone());
	}

	@Override
	public int compareTo(com.inkwell.internet.slogan.model.Slogan slogan) {
		return _slogan.compareTo(slogan);
	}

	@Override
	public int hashCode() {
		return _slogan.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.inkwell.internet.slogan.model.Slogan> toCacheModel() {
		return _slogan.toCacheModel();
	}

	@Override
	public com.inkwell.internet.slogan.model.Slogan toEscapedModel() {
		return new SloganWrapper(_slogan.toEscapedModel());
	}

	@Override
	public com.inkwell.internet.slogan.model.Slogan toUnescapedModel() {
		return new SloganWrapper(_slogan.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _slogan.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _slogan.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_slogan.persist();
	}

	@Override
	public java.lang.String getSloganIdString() {
		return _slogan.getSloganIdString();
	}

	@Override
	public void setSloganIdString(java.lang.String sloganIdString) {
		_slogan.setSloganIdString(sloganIdString);
	}

	@Override
	public double getAverageScore() {
		return _slogan.getAverageScore();
	}

	@Override
	public void setAverageScore(double averageScore) {
		_slogan.setAverageScore(averageScore);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SloganWrapper)) {
			return false;
		}

		SloganWrapper sloganWrapper = (SloganWrapper)obj;

		if (Validator.equals(_slogan, sloganWrapper._slogan)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Slogan getWrappedSlogan() {
		return _slogan;
	}

	@Override
	public Slogan getWrappedModel() {
		return _slogan;
	}

	@Override
	public void resetOriginalValues() {
		_slogan.resetOriginalValues();
	}

	private Slogan _slogan;
}