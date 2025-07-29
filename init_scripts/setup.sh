#!/bin/bash

echo "📦 Running setup.sh inside container..."

LOG_PATH="/tmp/setup.log"

echo "=== Shell setup started at $(date) ===" >> $LOG_PATH

echo "--- Oracle DB files ---" >> $LOG_PATH
ls -lh /opt/oracle/oradata >> $LOG_PATH

echo "--- Creating table via sqlplus ---" >> $LOG_PATH
sqlplus -s system/bp2@localhost/XEPDB1 <<EOF
WHENEVER SQLERROR EXIT SQL.SQLCODE
CREATE TABLE shell_users (
  id NUMBER GENERATED ALWAYS AS IDENTITY,
  username VARCHAR2(100),
  created_at DATE DEFAULT SYSDATE
);
INSERT INTO shell_users (username) VALUES ('andrea');
COMMIT;
EXIT;
EOF

echo "✅ setup.sh completed at $(date)" >> $LOG_PATH
