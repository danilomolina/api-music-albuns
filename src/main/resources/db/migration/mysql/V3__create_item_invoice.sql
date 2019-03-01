CREATE TABLE item_invoice (
    `id` bigint(20) NOT NULL,
    `id_invoice` bigint(20),
    `id_catalog_disk` bigint(20),
    `cashback` double,
    `quant` integer,
    `value` double
);
--
-- Indexes for table `invoice`
--
ALTER TABLE `item_invoice`
  ADD PRIMARY KEY (`id`);
  
ALTER TABLE `item_invoice`
  ADD FOREIGN KEY (`id_invoice`) REFERENCES invoice(`id`);
  
ALTER TABLE `item_invoice`
  ADD FOREIGN KEY (`id_catalog_disk`) REFERENCES catalog_disk(`id`);
  
ALTER TABLE `item_invoice` 
	CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT ;