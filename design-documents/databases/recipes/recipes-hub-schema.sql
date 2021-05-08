-- SQL dump generated using DBML (dbml-lang.org)
-- Database: PostgreSQL
-- Generated at: 2021-05-08T17:37:06.674Z

CREATE TYPE "unit_type" AS ENUM (
  'MASS',
  'VOLUME',
  'OTHER'
);

CREATE TABLE "recipes" (
  "id" SERIAL,
  "name" varchar,
  "created_at" timestamp DEFAULT (now()),
  PRIMARY KEY ("id")
);

CREATE TABLE "ingredients" (
  "id" SERIAL,
  "name" varchar(255),
  "density" real DEFAULT null,
  PRIMARY KEY ("id")
);

CREATE TABLE "recipe_ingredients" (
  "recipe_id" int NOT NULL,
  "ordinal" int NOT NULL,
  "ingredient_id" int NOT NULL,
  "amount" real NOT NULL,
  "type" unit_type NOT NULL,
  "notes" varchar,
  PRIMARY KEY ("recipe_id", "ordinal")
);

CREATE TABLE "instructions" (
  "recipe_id" int NOT NULL,
  "ordinal" int NOT NULL,
  "instruction" varchar,
  PRIMARY KEY ("recipe_id", "ordinal")
);

ALTER TABLE "recipe_ingredients" ADD FOREIGN KEY ("recipe_id") REFERENCES "recipes" ("id");

ALTER TABLE "recipe_ingredients" ADD FOREIGN KEY ("ingredient_id") REFERENCES "ingredients" ("id");

ALTER TABLE "instructions" ADD FOREIGN KEY ("recipe_id") REFERENCES "recipes" ("id");

CREATE INDEX ON "recipes" ("name");

CREATE INDEX ON "recipes" ("created_at");

CREATE INDEX ON "ingredients" ("name");

CREATE INDEX ON "recipe_ingredients" ("recipe_id");

CREATE INDEX ON "recipe_ingredients" ("ingredient_id");

CREATE INDEX ON "instructions" ("recipe_id");

COMMENT ON TABLE "recipe_ingredients" IS 'Stores ingredient and amount pairs for a recipe';

COMMENT ON COLUMN "recipe_ingredients"."ordinal" IS 'n-th ingredient in recipe';

COMMENT ON COLUMN "instructions"."ordinal" IS 'n-th instruction in recipe';
