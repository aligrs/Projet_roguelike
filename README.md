# Projet IN404
Réaisé par CHIKAR Soufiane, OUTEMZABET Zineb-Leila, HAMIDI Lylia et MOUDANI Ali
---

Dans notre Jeu , le joueur explore au tour par tour un ou plusieurs souterrains générés aléatoirement, dans un univers 2D décrit par des caractères ASCII, on utilisera une librairie externe nommé [AsciiPanel](https://github.com/trystan/AsciiPanel).

Ces caractères peuvent représenter le sol, les murs, portes et escaliers, ainsi que les objets et les êtres vivants. Cet univers doit être généré aléatoirement à chaque début de partie: la disposition des différentes pièces, ainsi que l'emplacement des objets et des ennemis est choisie au hasard.
Pour la génération aléatoire, on utilise un algorithme [procédural](www.gamasutra.com/blogs/AAdonaac/20150903/252889/Procedural_Dungeon_Generation_Algorithm.php) de génération de donjon


Ce type de jeu est parfois désigné sous le terme PMT pour Porte, Monstre, Trésor. Graduellement, le personnage affronte des monstres plus puissants, gagne de l'expérience et des objets de plus grande valeur, à la manière de certains jeux de rôle (Donjons et Dragons étant ici l'inspiration principale), que l'on peut dire dans le "style" américain de l'époque très axé sur les combats.


### Joueur:

<u>*Satistique du Joueur :*</u>

Le joueur posséde un **Inventaire** items max, un **nom** saisie par l'utilisateur et peut acquérir des **Effet/Enchantement** au cours d'une partie.

La **Defense** represente la reduction des degats subit.


### Effets et Enchantement:

Il existe plusieurs Effet et Enchantement sur les armes et armures, elles peuvent avoir soit un avantage soit un inconveniant.

### Execution du Programme

Le projet se compile en utilisant [gradle](https://gradle.org/).
Aucune installation préalable n'est nécessaire.

Sous Linux
```bash
$ ./gradlew build
```

Sous Windows
```
> gradlew.bat build
```
Pour lancer l'application, executer.

Sous Linux
```bash
$ ./gradlew run
```
Ainsi le jeu se lance, choisir une classe et donner un nom a votre personnage.

Le héros est symbolisé par un « @ », les autres personnages (monstres ennemis pour la plupart) sont représentés par des lettres de l'alphabet.

Le jeu implique deux types de personnages : un personnage joueur (PJ) et un autre non joueur (PNJ).
Le PJ possède des caractéristiques(*points de vie, de magie, ...*), un équipement (*monnaie, armes, ...*) et  évolue en mode tour par tour.
Les PNJ obéissent au mêmes règles que le PJ mais sont contrôlés par le jeu, les PNJ chassent le Joueur grace à [A* search algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm).


| ASCII                                                          | Description   |
| -------------------------------------------------------------- | ------------- |
| <span style="color:#FFD700;">@</span>                          | Joueur        |
| <span style="color:#808000;">b</span>                          | Chauve-Souris |
| <span style="color:#8B4513;">z</span>                          | Zombie        |
| <span style="color:#DC143C;">t</span>                          | Troll         |
| <span style="color:#00004f;">t</span>                          | Goblin        |

L'aventurier est contrôlé par de courtes commandes d'une ou plusieurs touches plutôt que par la souris. Son déplacement est donc réalisé par les flèches du clavier selon la direction qu’il souhaite prendre.

Le PJ peut également  interagir avec son environnement : (*ramasser ou utiliser un objet, discuter avec un PNJ, combattre un monstre*).
Pour voir les règles du jeu appuyer sur la touche ":" pendant une partie, cela vous donnera l'objectif et les touches disponibles.

### Taches restantes

Il manque à notre jeu uniquement le système de sauvegarde de partie.
