CREATE TABLE `recipes` (
  `id` int AUTO_INCREMENT,
  `name` varchar(255),
  `created_at` timestamp DEFAULT (now()),
  PRIMARY KEY (`id`)
);

CREATE TABLE `ingredients` (
  `id` int AUTO_INCREMENT,
  `name` varchar(255),
  `density` real DEFAULT null,
  PRIMARY KEY (`id`)
);

CREATE TABLE `recipe_ingredients` (
  `recipe_id` int NOT NULL,
  `ordinal` int NOT NULL COMMENT 'n-th ingredient in recipe',
  `ingredient_id` int NOT NULL,
  `amount` real NOT NULL,
  `type` ENUM ('MASS', 'VOLUME', 'OTHER') NOT NULL,
  `notes` varchar(255),
  PRIMARY KEY (`recipe_id`, `ordinal`)
);

CREATE TABLE `instructions` (
  `recipe_id` int NOT NULL,
  `ordinal` int NOT NULL COMMENT 'n-th instruction in recipe',
  `instruction` varchar(255),
  PRIMARY KEY (`recipe_id`, `ordinal`)
);

ALTER TABLE `recipe_ingredients` ADD FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`);

ALTER TABLE `recipe_ingredients` ADD FOREIGN KEY (`ingredient_id`) REFERENCES `ingredients` (`id`);

ALTER TABLE `instructions` ADD FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`);

CREATE INDEX `recipes_index_0` ON `recipes` (`name`);

CREATE INDEX `recipes_index_1` ON `recipes` (`created_at`);

CREATE INDEX `ingredients_index_2` ON `ingredients` (`name`);

CREATE INDEX `recipe_ingredients_index_3` ON `recipe_ingredients` (`recipe_id`);

CREATE INDEX `recipe_ingredients_index_4` ON `recipe_ingredients` (`ingredient_id`);

CREATE INDEX `instructions_index_5` ON `instructions` (`recipe_id`);

