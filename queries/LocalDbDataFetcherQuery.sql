/**
 * Author:  m.briedl
 * Created: 07.08.2017
 */

SELECT TOP 100 id, VNR, Prg, Beschreibung_Unfor, Ueberschrift_unfor, Datum, Oeffentlich FROM news
WHERE Prg = ? AND Beschreibung_Unfor IS NOT NULL AND (Oeffentlich <> 'O' OR Oeffentlich IS NULL) ORDER BY Datum DESC