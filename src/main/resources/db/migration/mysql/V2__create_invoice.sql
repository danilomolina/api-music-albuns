CREATE TABLE `invoice` (
  `id` bigint(20) NOT NULL,
  `date` varchar(30) NOT NULL,
  `value` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`id`);
  
ALTER TABLE `invoice` 
	CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT ;