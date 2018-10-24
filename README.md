# RICM5 - IHM - TP2

> Théo Lévesque - Timothée Depriester
> Polytech Grenoble - 2018

Une version non bugué mais sans fusion entre Paint et notre menu existe (précédent commit):
git checkout 1cf16ca7c25ab921a54c90681f34db5dc75bdffa

Nous avons eu des soucis de superpositions de nos éléments dans swing.

Il y'a donc deux classes séparés Paint et Menu que vous pouvez éxecuter séparemment.
Notre menu supporte un nombre de label modulaire, le placement en cercle évoluera et se répartira proportionnelemnt (angle) en fonction de ce nombre. L'ajout des labels est jusqu'ici écrit en dur dans le main. Le nombre maximum de labels est de 8 avant d'être écrit en dessous.

Dans le paint, il est possible de séléctionner une couleur et une forme avant de pouvoir dessiner et laisser libre recours à ses talents d'artiste.

Concernant notre choix de design, nous avons préféré utiliser le clic droit pour activer ou désactiver le menu. Il n'est pas obligatoire de maintenir le clic droit appuyé, nous trouvions ca moins pratique pour nos utilisateurs.
Il suffit après l'apparition du menu de cliquer droit sur l'un des composants pour faire le choix.
