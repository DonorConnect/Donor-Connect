CREATE TABLE `Donation` (
  `id` bigint(20) NOT NULL,
  `amount` double DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK48E5EBB2856AF776` (`project_id`)
);
