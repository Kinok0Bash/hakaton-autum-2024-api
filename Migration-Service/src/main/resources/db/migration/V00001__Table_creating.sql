CREATE TABLE IF NOT EXISTS "users" (
    "id" uuid NOT NULL UNIQUE,
    "name" varchar(50) NOT NULL,
    "login" varchar(20) NOT NULL UNIQUE,
    "password" text NOT NULL,
    "role" varchar(5) NOT NULL,
    "position" varchar(20),
    "avatar" bytea,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "tasks" (
    "id" uuid NOT NULL UNIQUE,
    "name" text NOT NULL,
    "description" text NOT NULL,
    "priority" int NOT NULL,
    "employee" uuid,
    "create_date" timestamp NOT NULL,
    "statement" varchar(10) NOT NULL,
    PRIMARY KEY ("id"),
    CONSTRAINT "tasks_fk5" FOREIGN KEY ("employee") REFERENCES "users"("id")
);

CREATE TABLE IF NOT EXISTS "comments" (
    "id" uuid NOT NULL UNIQUE,
    "comment" text NOT NULL ,
    "user" uuid NOT NULL ,
    "task" uuid NOT NULL ,
    PRIMARY KEY ("id"),
    CONSTRAINT "comments_fk2" FOREIGN KEY ("user") REFERENCES "users"("id"),
    CONSTRAINT "comments_fk3" FOREIGN KEY ("task") REFERENCES "tasks"("id")
);

CREATE TABLE IF NOT EXISTS "refresh_tokens" (
    "id" uuid NOT NULL UNIQUE ,
    "user" uuid NOT NULL ,
    "token" text NOT NULL UNIQUE ,
    PRIMARY KEY ("id"),
    CONSTRAINT "refresh_tokens_fk1" FOREIGN KEY ("user") REFERENCES "users"("id")
)