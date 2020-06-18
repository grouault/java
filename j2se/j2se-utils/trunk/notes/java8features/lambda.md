## Lambda

### Garder les expressions lambda courtes et explicites
* Si possible, utilisez des constructions d’une ligne au lieu d’un grand bloc de code.
* Rappelez-vous lambdas devrait être une expression, pas un récit. 
* Malgré sa syntaxe concise, les lambdas doivent exprimer précisément la fonctionnalité qu’ils fournissent.

### Éviter les blocs de code dans le corps de Lambda
Dans une situation idéale, les lambdas doivent être écrits en une seule ligne de code. Avec cette approche, la lambda est une construction auto-explicative, qui déclare quelle action doit être exécutée avec quelles données (dans le cas de lambdas avec des paramètres).

### Éviter de spécifier des types de paramètres
Un compilateur dans la plupart des cas est capable de résoudre le type de paramètres lambda à l’aide du type 'inference'. Par conséquent, l’ajout d’un type aux paramètres est facultatif et peut être omis.

### Éviter les parenthèses autour d’un seul paramètre
La syntaxe lambda nécessite des parenthèses seulement autour de plus d’un paramètre ou lorsqu’il n’y a aucun paramètre. C’est pourquoi il est sûr de rendre votre code un peu plus court et d’exclure les parenthèses quand il n’y a qu’un seul paramètre.

### Éviter les valeurs de retour et les accolades
Les éviter pour la clareté et la consistence

### Utiliser Java 8 feature: methode reference

### Utiliser des variables finals

### Protéger les variables objet contre les mutations
* Lambdas ne peut pas changer la valeur d'un objet du scope englobant
* Dans le cas d'objet mutable, l'état peut-être changer par le lambda.  