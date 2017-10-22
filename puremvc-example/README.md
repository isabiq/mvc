
## Summary
This MVC implementation respects the following :
* The model is an independent component, it represent the application business side.
* The proxy is responsible for executing the commands model and reusing its results.
* The command interact with proxies to execute actions coming from the Mediator, and notify it back for results.
* The Mediator is responsible for creating and managing views, and listen to notifications coming from the commands.


![alt text](./diagram/puremvc-example.png "puremvc-example")

## Advantages

* The use of proxy is very efficient for caching results and can be expanded to other uses.
* An easy mechanism for communication between Mediators, Commands and proxies.
* The design can scale as the application grows.
* The facade make it easier to manage all the tiers of the application.

## Limits

* The Mediator does seem to have more responsibility and it should have.
* The use of commands is very unclear and doesn't seem to have a big role in the hole application life cycle.
* The way the notification system is implemented can make the application logic harder to understand or even become uncontrollable.

## References

* [PureMVC/puremvc-java-demo-gwt-employeeadmin](https://github.com/PureMVC/puremvc-java-demo-gwt-employeeadmin)
* [PureMVC Framework Overview with UML](http://puremvc.org/docs/PureMVC_Framework_Overview_with_UML.pdf)
