-- ============================================================
--   Nom de la base   :  ENTREPISE 
--   Nom de SGBD      :  ANSI Niveau 2 
--   Date de création :  01/08/2009  12:00
--   Copyright       :  Gildas ROUAULT
-- ============================================================

-- ============================================================
--   Création de la procédure stockée nombreEmploye
-- ============================================================
CREATE PROCEDURE nombreEmploye (IN nom VARCHAR(20))
BEGIN
  SELECT COUNT(*) FROM EMPLOYE WHERE entreprise=nom;
END;
-- sous mysql: taper la commande delimiter // pour pouvoir enregister la 
-- procédure stockée.

-- ============================================================
--   Création de la procédure stockée EmployesFamille
-- ============================================================
CREATE PROCEDURE employeFamille (IN n VARCHAR(20))
BEGIN
	SELECT * FROM EMPLOYE WHERE nom=n;
END 
