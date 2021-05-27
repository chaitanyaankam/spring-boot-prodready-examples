# Dynamic Jasper Reports using Spring Boot

This project helps in generating the reports in a generic way using Jasper.\
The Heading and Fields can be sent in request.\
Velocity Template enginer is used to compile the existing JRXML to form the actual template.\
The compiled template is then passed to JapserCompiler to bind data or SQL with the template.\
The template is then exported with the necessary export parameters.
