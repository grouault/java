## urls
https://dzone.com/articles/functional-interface-and-lambda-expression-in-java
https://www.baeldung.com/java-8-lambda-expressions-tips
https://www.baeldung.com/java-8-functional-interfaces

## Functional Interface
Une interface fonctionnelle (functional interface) est basiquement une interface dans laquelle une seule méthode abstraite est définie. Elle doit respecter certaines contraintes :
* Cela signifie que l'implémentation de l'interface ne représentera qu'un seul comportement.
* elle ne doit avoir qu'une seule méthode déclarée abstraite
* les méthodes définies dans la classe Object ne sont pas prises en compte comme étant des méthodes abstraites
* toutes les méthodes doivent être public
* elle peut avoir des méthodes par défaut et static

## Implémentation
* implémentation par une classe d'implémentation
* implémentation par une classe anonyme / interne

## Implémentation par lambda
Exemple : Foo foo = parameter -> parameter + " from lambda";
Foo n'est rien d'autre qu'une fonction qui accepte un argument et renvoie un résultat.
Java 8 fournit déjà une interface Function<T,R> à partir du package java.util.function

## Important: Lambda et Classe interne n'ont pas le même comportement
Les deux concepts sont différents pour un point important : le scope / la portée.
* Classe interne : 
- possibilité de cacher local-variable du scope englobant en instancient la nouvelle variable avec le même nom.
- this: référence l'instance de la classe interne
* Lambda:
- travaille avec le scope englobant
- impossibilié de cacher les variables locales (corps du lambda) du scope englobant
- this: référence le scope englobant