package com.book.chapter4.french;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class AdaptateurAlbumMusical extends XmlAdapter<ReferenceAlbumMusical, AlbumMusical> {
	
	@Override
	public AlbumMusical unmarshal(final ReferenceAlbumMusical reference) throws Exception {
		if (reference == null) {
			return null;
		}
		return reference.getAlbumMusical();
	}

	@Override
	public ReferenceAlbumMusical marshal(final AlbumMusical albumMusical) throws Exception {
		if (albumMusical == null) {
			return null;
		}
		return new ReferenceAlbumMusical(albumMusical);
	}

}