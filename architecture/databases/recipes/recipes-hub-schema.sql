-- SQL dump generated using DBML (dbml-lang.org)
-- Database: PostgreSQL
-- Generated at: 2021-05-04T18:43:17.635Z

CREATE TYPE "unit_type" AS ENUM (
  'mass',
  'volume',
  'other'
);

CREATE TABLE "recipes" (
  "id" SERIAL UNIQUE PRIMARY KEY NOT NULL,
  "name" varchar,
  "created_at" timestamp DEFAULT (now())
);

CREATE TABLE "ingredient" (
  "id" SERIAL UNIQUE PRIMARY KEY,
  "name" varchar(255),
  "density" real DEFAULT null
);

CREATE TABLE "recipe_ingredient" (
  "recipe_id" int NOT NULL,
  "ingredient_id" int NOT NULL,
  "amount" real NOT NULL,
  "type" unit_type NOT NULL,
  "notes" varchar,
  PRIMARY KEY ("recipe_id")
);

CREATE TABLE "instructions" (
  "id" SERIAL UNIQUE PRIMARY KEY,
  "recipe_id" int NOT NULL,
  "ordinal" int NOT NULL,
  "instruction" varchar
);

ALTER TABLE "recipe_ingredient" ADD FOREIGN KEY ("recipe_id") REFERENCES "recipes" ("id");

ALTER TABLE "recipe_ingredient" ADD FOREIGN KEY ("ingredient_id") REFERENCES "ingredient" ("id");

ALTER TABLE "instructions" ADD FOREIGN KEY ("recipe_id") REFERENCES "recipes" ("id");

CREATE INDEX ON "recipes" ("id");

CREATE INDEX ON "recipes" ("name");

CREATE INDEX ON "recipes" ("created_at");

CREATE INDEX ON "ingredient" ("id");

CREATE INDEX ON "ingredient" ("name");

COMMENT ON TABLE "recipe_ingredient" IS 'Stores ingredient and amount pairs for a recipe';

COMMENT ON COLUMN "instructions"."ordinal" IS 'n-th instruction in recipe';
