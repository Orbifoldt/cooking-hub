# npm install -g @dbml/cli  # To install the DBML CLI
$dbmlFile = Join-Path $PSScriptRoot recipes-hub-schema.dbml
$outputFile = Join-Path $PSScriptRoot recipes-hub-schema.sql
dbml2sql $dbmlFile --out-file $outputFile