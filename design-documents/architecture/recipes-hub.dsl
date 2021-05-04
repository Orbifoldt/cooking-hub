workspace {
    model {
        user = person "User"

        softwareSystem "Software System"  {
            wa = container "Web Application" "React"
            db = container "Database" "PostgreSQL"
            api = container "application service" "Spring Boot"
        }

        user -> wa "Views existing recipes and adds new ones"
        wa -> api "Uses for getting data"
        api -> db "Request data"

    }

}