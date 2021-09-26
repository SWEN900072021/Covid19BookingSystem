## Local DB Setup ##
1. Run the [Postgres Setup SQL Script](scripts/postgres_setup.sql) on your local to set up the tables and types in your postgres local instance **(ONLY ONCE)**.
2. Create a new environment variable called "**DATABASE_URL**" in your machine with the following value (replace DB_USER, DB_PASSWORD and DB_NAME with the respective credentials of your local postgres instance):
    ```
    postgres://{DB_USER}:{DB_PASSWORD}@localhost:5432/{DB_NAME}
    ```

## Setting Up PlantUML ##
1. Open IntelliJ
2. Go to File -> Settings (or Preferences on macOS) -> Plugins
3. Download and install 'PlantUML integration'
4. Download and install [Graphviz](https://graphviz.org/download/) for sequence and activity diagrams
   
## Useful Links ## 

[Initial Setup](https://github.com/SWEN900072021/Resources/tree/main/setup_dev)
(Credit: [Luke Rosa](https://github.com/lukearosa))

[JSP & Servlets Tutorial](https://github.com/SWEN900072021/Resources/blob/main/how_to/2021/jsp_servlets.md)
(Credit: [Luke Rosa](https://github.com/lukearosa))

[Course Notes](https://github.com/SWEN900072021/Resources/blob/main/course_notes/course_notes.pdf)
(Credit: [Eduardo Oliveira](https://github.com/agogear) and [Maria Rodriguez Read](https://github.com/mariaars))

[Spring Security Code Sample](https://github.com/SWEN900072021/Resources/tree/main/code_sample/security/swen90007_security_spring)
(Credit: [Luke Rosa](https://github.com/lukearosa), [Eduardo Oliveira](https://github.com/agogear) and [Maria Rodriguez Read](https://github.com/mariaars))