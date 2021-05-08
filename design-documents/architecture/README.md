# Render the diagrams

1. Install [Structurizr CLI](https://github.com/structurizr/cli) (add to path for convenience)
2. Run the command `structurizr push -id 123456 -key 1a130d2b... -secret a9daaf3e... -workspace workspace.dsl` to push it to your workspace
3. Download [Structurizr Puppeteer](https://github.com/structurizr/puppeteer) in order to export to png/svg locally (Install puppeteer node module with `npm install puppeteer`)
4. Use the following to auto download the image files `node export-private-diagrams.js https://structurizr.com username password png 123456`

Example Powershell script:
```
$dslFileName = "recipes-hub.dsl"
$filepath = Join-Path $PSScriptRoot $dslFileName 
$id = 123456
$key = "abcd12ef-..."
$secret = "cdef98ab-..."
structurizr push -id $id -key $key -secret $secret -workspace $filepath

$user = "structurizr-email-login"
$password = "structurizr-password"
$puppeteerPath = Join-Path $PSScriptRoot "puppeteer\export-private-diagrams.js"
node $puppeteerPath https://structurizr.com $user $password png $id
```


[Structurizr DSL](https://github.com/structurizr/dsl)