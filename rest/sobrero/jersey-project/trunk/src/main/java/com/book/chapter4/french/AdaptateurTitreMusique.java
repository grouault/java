package com.book.chapter4.french;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class AdaptateurTitreMusique extends XmlAdapter<ReferenceTitreMusique, TitreMusique> {
	
	@Override
	public TitreMusique unmarshal(final ReferenceTitreMusique reference) throws Exception {
		if (reference == null) {
			return null;
		}
		return reference.getTitreMusique();
	}

	@Override
	public ReferenceTitreMusique marshal(final TitreMusique titreMusique) throws Exception {
		if (titreMusique == null) {
			return null;
		}
		return new ReferenceTitreMusique(titreMusique);
	}

}