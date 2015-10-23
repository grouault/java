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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for Slogan. This utility wraps
 * {@link com.inkwell.internet.slogan.service.impl.SloganLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author gildas
 * @see SloganLocalService
 * @see com.inkwell.internet.slogan.service.base.SloganLocalServiceBaseImpl
 * @see com.inkwell.internet.slogan.service.impl.SloganLocalServiceImpl
 * @generated
 */
public class SloganLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.inkwell.internet.slogan.service.impl.SloganLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the slogan to the database. Also notifies the appropriate model listeners.
	*
	* @param slogan the slogan
	* @return the slogan that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.inkwell.internet.slogan.model.Slogan addSlogan(
		com.inkwell.internet.slogan.model.Slogan slogan)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addSlogan(slogan);
	}

	/**
	* Creates a new slogan with the primary key. Does not add the slogan to the database.
	*
	* @param sloganId the primary key for the new slogan
	* @return the new slogan
	*/
	public static com.inkwell.internet.slogan.model.Slogan createSlogan(
		long sloganId) {
		return getService().createSlogan(sloganId);
	}

	/**
	* Deletes the slogan with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param sloganId the primary key of the slogan
	* @return the slogan that was removed
	* @throws PortalException if a slogan with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.inkwell.internet.slogan.model.Slogan deleteSlogan(
		long sloganId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSlogan(sloganId);
	}

	/**
	* Deletes the slogan from the database. Also notifies the appropriate model listeners.
	*
	* @param slogan the slogan
	* @return the slogan that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.inkwell.internet.slogan.model.Slogan deleteSlogan(
		com.inkwell.internet.slogan.model.Slogan slogan)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSlogan(slogan);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
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
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.inkwell.internet.slogan.model.Slogan fetchSlogan(
		long sloganId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchSlogan(sloganId);
	}

	/**
	* Returns the slogan with the matching UUID and company.
	*
	* @param uuid the slogan's UUID
	* @param companyId the primary key of the company
	* @return the matching slogan, or <code>null</code> if a matching slogan could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.inkwell.internet.slogan.model.Slogan fetchSloganByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchSloganByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the slogan matching the UUID and group.
	*
	* @param uuid the slogan's UUID
	* @param groupId the primary key of the group
	* @return the matching slogan, or <code>null</code> if a matching slogan could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.inkwell.internet.slogan.model.Slogan fetchSloganByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchSloganByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the slogan with the primary key.
	*
	* @param sloganId the primary key of the slogan
	* @return the slogan
	* @throws PortalException if a slogan with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.inkwell.internet.slogan.model.Slogan getSlogan(
		long sloganId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSlogan(sloganId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static com.inkwell.internet.slogan.model.Slogan getSloganByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSloganByUuidAndCompanyId(uuid, companyId);
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
	public static com.inkwell.internet.slogan.model.Slogan getSloganByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSloganByUuidAndGroupId(uuid, groupId);
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
	public static java.util.List<com.inkwell.internet.slogan.model.Slogan> getSlogans(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSlogans(start, end);
	}

	/**
	* Returns the number of slogans.
	*
	* @return the number of slogans
	* @throws SystemException if a system exception occurred
	*/
	public static int getSlogansCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSlogansCount();
	}

	/**
	* Updates the slogan in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param slogan the slogan
	* @return the slogan that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.inkwell.internet.slogan.model.Slogan updateSlogan(
		com.inkwell.internet.slogan.model.Slogan slogan)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSlogan(slogan);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Gets all slogans by group ID.
	*
	* @param groupId
	* @return
	* @throws SystemException
	*/
	public static java.util.List<com.inkwell.internet.slogan.model.Slogan> getSlogans(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSlogans(groupId);
	}

	public static java.util.List<com.inkwell.internet.slogan.model.Slogan> getSlogans(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSlogans(groupId, status, start, end, obc);
	}

	public static java.util.List<com.inkwell.internet.slogan.model.Slogan> getSlogans(
		long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSlogans(groupId, status, start, end);
	}

	public static java.util.List<com.inkwell.internet.slogan.model.Slogan> getCompanySlogans(
		long companyId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCompanySlogans(companyId, status, start, end);
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
	public static com.inkwell.internet.slogan.model.Slogan addSlogan(
		com.inkwell.internet.slogan.model.Slogan newSlogan, long userId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addSlogan(newSlogan, userId, serviceContext);
	}

	public static com.inkwell.internet.slogan.model.Slogan updateStatus(
		long userId, long resourcePrimKey, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateStatus(userId, resourcePrimKey, status, serviceContext);
	}

	public static int getSlogansCount(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSlogansCount(groupId, status);
	}

	public static void clearService() {
		_service = null;
	}

	public static SloganLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SloganLocalService.class.getName());

			if (invokableLocalService instanceof SloganLocalService) {
				_service = (SloganLocalService)invokableLocalService;
			}
			else {
				_service = new SloganLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SloganLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(SloganLocalService service) {
	}

	private static SloganLocalService _service;
}