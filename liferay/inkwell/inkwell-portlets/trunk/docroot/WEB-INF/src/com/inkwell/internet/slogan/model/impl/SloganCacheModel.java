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

package com.inkwell.internet.slogan.model.impl;

import com.inkwell.internet.slogan.model.Slogan;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Slogan in entity cache.
 *
 * @author gildas
 * @see Slogan
 * @generated
 */
public class SloganCacheModel implements CacheModel<Slogan>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", sloganId=");
		sb.append(sloganId);
		sb.append(", sloganDate=");
		sb.append(sloganDate);
		sb.append(", sloganText=");
		sb.append(sloganText);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Slogan toEntityModel() {
		SloganImpl sloganImpl = new SloganImpl();

		if (uuid == null) {
			sloganImpl.setUuid(StringPool.BLANK);
		}
		else {
			sloganImpl.setUuid(uuid);
		}

		sloganImpl.setSloganId(sloganId);

		if (sloganDate == Long.MIN_VALUE) {
			sloganImpl.setSloganDate(null);
		}
		else {
			sloganImpl.setSloganDate(new Date(sloganDate));
		}

		if (sloganText == null) {
			sloganImpl.setSloganText(StringPool.BLANK);
		}
		else {
			sloganImpl.setSloganText(sloganText);
		}

		sloganImpl.setStatus(status);
		sloganImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			sloganImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			sloganImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			sloganImpl.setStatusDate(null);
		}
		else {
			sloganImpl.setStatusDate(new Date(statusDate));
		}

		sloganImpl.setCompanyId(companyId);
		sloganImpl.setGroupId(groupId);
		sloganImpl.setUserId(userId);

		sloganImpl.resetOriginalValues();

		return sloganImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		sloganId = objectInput.readLong();
		sloganDate = objectInput.readLong();
		sloganText = objectInput.readUTF();
		status = objectInput.readInt();
		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
		companyId = objectInput.readLong();
		groupId = objectInput.readLong();
		userId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(sloganId);
		objectOutput.writeLong(sloganDate);

		if (sloganText == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sloganText);
		}

		objectOutput.writeInt(status);
		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(userId);
	}

	public String uuid;
	public long sloganId;
	public long sloganDate;
	public String sloganText;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
	public long companyId;
	public long groupId;
	public long userId;
}