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

package com.inkwell.internet.slogan.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SloganLocalService}.
 *
 * @author gildas
 * @see SloganLocalService
 * @generated
 */
public class SloganLocalServiceWrapper implements SloganLocalService,
	ServiceWrapper<SloganLocalService> {
	public SloganLocalServiceWrapper(SloganLocalService sloganLocalService) {
		_sloganLocalService = sloganLocalService;
	}

	/**
	* Adds the slogan to the database. Also notifies the appropriate model listeners.
	*
	* @param slogan the slogan
	* @return the slogan that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.inkwell.internet.slogan.model.Slogan addSlogan(
		com.inkwell.internet.slogan.model.Slogan slogan)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.addSlogan(slogan);
	}

	/**
	* Creates a new slogan with the primary key. Does not add the slogan to the database.
	*
	* @param sloganId the primary key for the new slogan
	* @return the new slogan
	*/
	@Override
	public com.inkwell.internet.slogan.model.Slogan createSlogan(long sloganId) {
		return _sloganLocalService.createSlogan(sloganId);
	}

	/**
	* Deletes the slogan with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param sloganId the primary key of the slogan
	* @return the slogan that was removed
	* @throws PortalException if a slogan with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.inkwell.internet.slogan.model.Slogan deleteSlogan(long sloganId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.deleteSlogan(sloganId);
	}

	/**
	* Deletes the slogan from the database. Also notifies the appropriate model listeners.
	*
	* @param slogan the slogan
	* @return the slogan that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.inkwell.internet.slogan.model.Slogan deleteSlogan(
		com.inkwell.internet.slogan.model.Slogan slogan)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.deleteSlogan(slogan);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _sloganLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.inkwell.internet.slogan.model.impl.SloganModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.inkwell.internet.slogan.model.impl.SloganModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.inkwell.internet.slogan.model.Slogan fetchSlogan(long sloganId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.fetchSlogan(sloganId);
	}

	/**
	* Returns the slogan with the matching UUID and company.
	*
	* @param uuid the slogan's UUID
	* @param companyId the primary key of the company
	* @return the matching slogan, or <code>null</code> if a matching slogan could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.inkwell.internet.slogan.model.Slogan fetchSloganByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.fetchSloganByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the slogan matching the UUID and group.
	*
	* @param uuid the slogan's UUID
	* @param groupId the primary key of the group
	* @return the matching slogan, or <code>null</code> if a matching slogan could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.inkwell.internet.slogan.model.Slogan fetchSloganByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.fetchSloganByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the slogan with the primary key.
	*
	* @param sloganId the primary key of the slogan
	* @return the slogan
	* @throws PortalException if a slogan with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.inkwell.internet.slogan.model.Slogan getSlogan(long sloganId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.getSlogan(sloganId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the slogan with the matching UUID and company.
	*
	* @param uuid the slogan's UUID
	* @param companyId the primary key of the company
	* @return the matching slogan
	* @throws PortalException if a matching slogan could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.inkwell.internet.slogan.model.Slogan getSloganByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.getSloganByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the slogan matching the UUID and group.
	*
	* @param uuid the slogan's UUID
	* @param groupId the primary key of the group
	* @return the matching slogan
	* @throws PortalException if a matching slogan could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.inkwell.internet.slogan.model.Slogan getSloganByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.getSloganByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the slogans.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.inkwell.internet.slogan.model.impl.SloganModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of slogans
	* @param end the upper bound of the range of slogans (not inclusive)
	* @return the range of slogans
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.inkwell.internet.slogan.model.Slogan> getSlogans(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.getSlogans(start, end);
	}

	/**
	* Returns the number of slogans.
	*
	* @return the number of slogans
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getSlogansCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.getSlogansCount();
	}

	/**
	* Updates the slogan in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param slogan the slogan
	* @return the slogan that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.inkwell.internet.slogan.model.Slogan updateSlogan(
		com.inkwell.internet.slogan.model.Slogan slogan)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.updateSlogan(slogan);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _sloganLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_sloganLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _sloganLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Gets all slogans by group ID.
	*
	* @param groupId
	* @return
	* @throws SystemException
	*/
	@Override
	public java.util.List<com.inkwell.internet.slogan.model.Slogan> getSlogans(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.getSlogans(groupId);
	}

	@Override
	public java.util.List<com.inkwell.internet.slogan.model.Slogan> getSlogans(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.getSlogans(groupId, status, start, end, obc);
	}

	@Override
	public java.util.List<com.inkwell.internet.slogan.model.Slogan> getSlogans(
		long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.getSlogans(groupId, status, start, end);
	}

	@Override
	public java.util.List<com.inkwell.internet.slogan.model.Slogan> getCompanySlogans(
		long companyId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.getCompanySlogans(companyId, status, start,
			end);
	}

	/**
	* Adds a new slogan to the database.
	*
	* @param newSlogan
	* @param userId
	* @return
	* @throws SystemException
	* @throws PortalException
	*/
	@Override
	public com.inkwell.internet.slogan.model.Slogan addSlogan(
		com.inkwell.internet.slogan.model.Slogan newSlogan, long userId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.addSlogan(newSlogan, userId, serviceContext);
	}

	@Override
	public com.inkwell.internet.slogan.model.Slogan updateStatus(long userId,
		long resourcePrimKey, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.updateStatus(userId, resourcePrimKey,
			status, serviceContext);
	}

	@Override
	public int getSlogansCount(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sloganLocalService.getSlogansCount(groupId, status);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SloganLocalService getWrappedSloganLocalService() {
		return _sloganLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSloganLocalService(
		SloganLocalService sloganLocalService) {
		_sloganLocalService = sloganLocalService;
	}

	@Override
	public SloganLocalService getWrappedService() {
		return _sloganLocalService;
	}

	@Override
	public void setWrappedService(SloganLocalService sloganLocalService) {
		_sloganLocalService = sloganLocalService;
	}

	private SloganLocalService _sloganLocalService;
}