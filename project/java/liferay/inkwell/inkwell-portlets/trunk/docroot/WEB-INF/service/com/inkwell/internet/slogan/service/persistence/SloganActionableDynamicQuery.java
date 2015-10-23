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

package com.inkwell.internet.slogan.service.persistence;

import com.inkwell.internet.slogan.model.Slogan;
import com.inkwell.internet.slogan.service.SloganLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author gildas
 * @generated
 */
public abstract class SloganActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public SloganActionableDynamicQuery() throws SystemException {
		setBaseLocalService(SloganLocalServiceUtil.getService());
		setClass(Slogan.class);

		setClassLoader(com.inkwell.internet.slogan.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("sloganId");
	}
}