/**
 * Author:  m.briedl
 * Created: 07.08.2017
 */

INSERT INTO news_articles (date, category_id, product_version, active, cs_id) VALUES(?, (SELECT id FROM news_categories WHERE cs_match = ?), ?, ?, ?);
/*STATEMENT-SEPERATOR*/
INSERT INTO news_article_localizations (article_id, title, text) VALUES(LAST_INSERT_ID(), ?, ?)