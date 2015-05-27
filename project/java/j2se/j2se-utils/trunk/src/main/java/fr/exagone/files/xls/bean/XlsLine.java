package fr.exagone.files.xls.bean;

public interface XlsLine {
	
    /**
     * retourne le numero de la ligne du fichier excel.
     * @return
     */
   Integer getLineNumber();
   
   /**
     * permet de mettre a jour le numero de la ligne du fichier.
     */
    void setLineNumber(Integer lineNumber);
    

}
