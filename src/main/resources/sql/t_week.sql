/*
 Navicat Premium Data Transfer

 Source Server         : 本地pg
 Source Server Type    : PostgreSQL
 Source Server Version : 100500
 Source Host           : localhost:5432
 Source Catalog        : switcher_pg
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 100500
 File Encoding         : 65001

 Date: 25/02/2019 16:49:38
*/


-- ----------------------------
-- Table structure for t_week
-- ----------------------------
DROP TABLE IF EXISTS "t_week";
CREATE TABLE "t_week" (
  "id" int4 NOT NULL DEFAULT NULL,
  "day" varchar(10) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "work" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL
)
;
ALTER TABLE "t_week" OWNER TO "postgres";

-- ----------------------------
-- Records of t_week
-- ----------------------------
BEGIN;
INSERT INTO "t_week" VALUES (2, 'Tuesday', '看电影');
INSERT INTO "t_week" VALUES (3, 'Wednesday', '篮球');
INSERT INTO "t_week" VALUES (1, 'Monday', '工作');
INSERT INTO "t_week" VALUES (4, 'Thursday', '写博客');
INSERT INTO "t_week" VALUES (5, 'Friday', '总结');
INSERT INTO "t_week" VALUES (6, 'Saturday', '跑步');
INSERT INTO "t_week" VALUES (7, 'Sunday', '游泳');
COMMIT;

-- ----------------------------
-- Primary Key structure for table t_week
-- ----------------------------
ALTER TABLE "t_week" ADD CONSTRAINT "t_week_pkey" PRIMARY KEY ("id");
