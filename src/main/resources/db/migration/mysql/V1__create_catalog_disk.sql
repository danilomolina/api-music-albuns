CREATE TABLE `catalog_disk` (
  `id` bigint(20) NOT NULL,
  `name` varchar(80) NOT NULL,
  `genre` varchar(80) NOT NULL,
  `price` double NOT NULL,
  `artist` varchar(80) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--
-- Indexes for table `catalog_disk`
--
ALTER TABLE `catalog_disk`
  ADD PRIMARY KEY (`id`);
  
ALTER TABLE `catalog_disk` 
	CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT ;

  

