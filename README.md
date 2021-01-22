# Epreuve de développement ABSYS

Bienvenue dans l’épreuve de recrutement ABSYS. Cette épreuve va permettre d’évaluer votre mindset et vos compétences en programmation et résolution de problèmes.
Il s’agit d’un projet back et front qui contient des bugs à corriger et de nouvelles fonctionnalités à ajouter. Ce test doit se dérouler dans les mêmes conditions qu’un développeur professionnel en poste. 
Vous devez considérer le recruteur qui vous a envoyé ce test comme le « Client ». Vous avez le droit d’utiliser Internet et de consulter toute la documentation qui pourrait vous être utile. Attention à ne pas réutiliser de code que vous ne comprenez pas… 
Il n’est pas nécessaire de termine la totalité du sujet, l’important c’est que vous montrez ce dont vous êtes capable ! 

Sujet : Vous travaillez pour une filiale de l’aérospatial. Votre chef vient vous voir et vous demande de développer une application de gestion de passagers arrivant sur Mars.
Dans sa première version, l’application a été simplifiée car on est encore loin de poser le pied sur Mars.
Malheureusement, le client est très occupé dans le développement de sa fusée et n’a pas le temps de vous faire une super spécification. Voici le peu d’informations dont nous disposons :
## 1.	Description du besoin
La société doit ouvrir son premier astroport. Un logiciel pour gérer les personnes à leur arrivée sur Mars est nécessaire.
Ce logiciel doit permettre d’enregistrer les nouveaux arrivants, de leur remettre un numéro d’identification. Le numéro d’identification doit répondre à la norme MARS-51*2. Un administrateur doit pouvoir ensuite consulter et gérer cette liste.
L’application doit être en Anglais.
## 2.	Cahier des charges

### a.	Inscription utilisateur
L’application doit être accessible à partir d’une page web. L’utilisateur arrive sur un formulaire lui demandant nom, prénom, âge, profession et pays d’origine. L’utilisateur doit remplir obligatoirement tous les champs. Un bouton valider permet d’enregistrer l’utilisateur, à la fin de l’enregistrement, un message de confirmation s’affiche « Vous avez bien été enregistré »
### b.	Workflow
Il est important de ne pas laisser entrer n’importe sur Mars, une validation est nécessaire. Il faut que le profil soit validé dans un premier temps par l’administrateur de l’application, puis une vérification sur terre doit permettre de vérifier que la personne n’est pas un criminel recherché. Il faut ensuite s’assurer que les informations d’identification (nom, prénom, date de naissance) ne soient pas déjà connues dans la base.
Il faut prévoir un panel admin qui permet de consulter la liste des utilisateurs regroupé à la fois par Profession et par Pays (vous pouvez utiliser par exemple : https://primefaces.org/primeng/showcase/#/table/rowgroup)


## Déroulement de l’épreuve :
Vous avez reçu le package de base qui comprends une application Angular et une application Springboot. Ces 2 projets implémentent déjà la plupart des fonctionnalités attendues.
Cependant, des bugs ont été introduits, il faut tout d’abord fiabiliser et corriger les bugs puis développer toutes les fonctionnalités manquantes.
Le client a repéré des bugs dans cette version de l’application : 
-	La liste des utilisateurs du panneau d’administration est sensé se mettre à jour automatiquement dès qu’on enregistre un nouveau profil
Il y en a peut-être d’autres…

Aucun livrable n’est attendu en dehors du code corrigé et complété de l’application. Merci de bien veillé à ne pas nous envoyer de dossier « library » type « node_modules » dans votre rendu. Un projet github ou une archive ZIP seront acceptés.
Voici quelques exemples de bugs que le client a relevé pendants ses tests : 
L’application est un Mock, elle s’ouvre sur le même ordinateur et n’a pas de vraie base de données. Vous devez l’utiliser comme si vous étiez à la fois administrateur et nouvel utilisateur (un onglet Chrome pour l’utilisateur et un autre pour l’administrateur) Il n’est pas demandé de monter une vraie base de données.

## Critères d’évaluation : 
-	Qualité de la rédaction du code et de ses commentaires
-	Fiabilité du code
-	Bonnes pratiques POO (design pattern, …)
-	Faculté à comprendre et résoudre des problèmes


