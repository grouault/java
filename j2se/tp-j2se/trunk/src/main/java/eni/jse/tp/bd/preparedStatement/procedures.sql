-- ============================================================
--   Nom de la base   :  ENTREPISE 
--   Nom de SGBD      :  ANSI Niveau 2 
--   Date de cr�ation :  01/08/2009  12:00
--   Copyright       :  Gildas ROUAULT
-- ============================================================

-- ============================================================
--   Cr�ation de la proc�dure stock�e nombreEmploye
-- ============================================================
CREATE PROCEDURE nombreEmploye (IN nom VARCHAR(20))
BEGIN
  SELECT COUNT(*) FROM EMPLOYE WHERE entreprise=nom;
END;
-- sous mysql: taper la commande delimiter // pour pouvoir enregister la 
-- proc�dure stock�e.

-- ============================================================
--   Cr�ation de la proc�dure stock�e EmployesFamille
-- ============================================================
CREATE PROCEDURE employeFamille (IN n VARCHAR(20))
BEGIN
	SELECT * FROM EMPLOYE WHERE nom=n;
END 
