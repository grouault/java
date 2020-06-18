## urls
https://dzone.com/articles/functional-interface-and-lambda-expression-in-java
https://www.baeldung.com/java-8-lambda-expressions-tips
https://www.baeldung.com/java-8-functional-interfaces

## Functional Interface
* Une interface fonctionnelle est une interface avec une seule méthode abstraite.
* Cela signifie que l'implémentation de l'interface ne représentera qu'un seul comportement.
* Un point important à retenir est que l’interface fonctionnelle peut avoir un certain nombre de méthodes par défaut, mais une seule méthode abstraite.

## Implémentation par lambda
Exemple : Foo foo = parameter -> parameter + " from lambda";
Foo n'est rien d'autre qu'une fonction qui accepte un argument et renvoie un résultat.
Java 8 fournit déjà une interface Function<T,R> à partir du package java.util.function