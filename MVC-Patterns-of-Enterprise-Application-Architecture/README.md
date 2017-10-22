
## Summary
This MVC implementation respects the following :
* The model is an independent component, it represent the application business side.
* The view is responsible for querying and updating the model. It's also responsible for managing the application logic and handling user actions.

![alt text](./diagram/MVC-Patterns-of-Enterprise-Application-Architecture.png "MVC-Patterns-of-Enterprise-Application-Architecture")

## Advantages

* Very natural to follow.
* Less code, and more focus on the application and business logic.
* No complexity in building objects and starting the application.

## Limits

* The view will get complicated as the application grows.

## References

* [Martin Fowler - Patterns of Enterprise Application Architecture](https://www.martinfowler.com/books/eaa.html)
