# JSP-Webshell

## Status
JSP Webshell project in progress.

## Description
A JSP Webshell disguised as a fully customizable error page. In this project, the deployed WAR presents a 404 Error page with a hidden input that can be made visible through code inspection or hitting the tab button. Entering the correct password will lead to the webshell itself (by default is 1234). In order to change the pass, you must change the hash in "Check.java" class and replace with your own MD5-hash password (implementing new hashing algorithm comparison is pending). 
