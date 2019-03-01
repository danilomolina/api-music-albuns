CREATE TABLE `cashback` (
  `id` bigint(20) NOT NULL,
  `genre` varchar(80) NOT NULL,
  `perc_sunday` integer NOT NULL,
  `perc_monday` integer NOT NULL,
  `perc_tuesday` integer NOT NULL,
  `perc_wednesday` integer NOT NULL,
  `perc_thursday` integer NOT NULL,
  `perc_friday` integer NOT NULL,
  `perc_saturday` integer NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--
-- Indexes for table `cashback`
--
ALTER TABLE `cashback`
  ADD PRIMARY KEY (`id`);
