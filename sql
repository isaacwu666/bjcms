ALTER TABLE `bjcms_dev`.`bj_article`
ADD COLUMN `keyWords` varchar(70) CHARACTER SET utf8mb4 NULL DEFAULT NULL AFTER `public_status`,
ADD COLUMN `description` varchar(240) CHARACTER SET utf8mb4 NULL DEFAULT NULL AFTER `keyWords`;