# npm install -g @dbml/cli  # To install the DBML CLI
$dbmlFile = Join-Path $PSScriptRoot recipes-hub-schema.dbml
$outputFile = Join-Path $PSScriptRoot recipes-hub-schema.sql
dbml2sql $dbmlFile --out-file $outputFile
# $outputFileMySql = Join-Path $PSScriptRoot recipes-hub-schema-mysql.sql
# dbml2sql $dbmlFile --mysql --out-file $outputFileMySql 